package ca.kitamura.simpleaddressbook.screens.main;

import android.util.Log;

import ca.kitamura.simpleaddressbook.models.randomuser.RandomUserResponse;
import ca.kitamura.simpleaddressbook.networking.NetworkingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Darren on 2016-12-14.
 */

public class MainPresenterImpl implements MainPresenter {
    MainView mainView;

    MainPresenterImpl(MainView view) {
        this.mainView = view;
    }

    @Override
    public void getContacts() {
        mainView.showProgressBar();
        Call<RandomUserResponse> randomUserResponseCall = NetworkingService.getRandomUserService().getRandomUsers();
        randomUserResponseCall.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                mainView.hideProgressBar();
                mainView.updateContactRecyclerView(response.body().getResults());
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                mainView.hideProgressBar();
                Log.e("Call Fail", t.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }
}
