package ca.kitamura.simpleaddressbook.screens.main;

import java.util.List;

import ca.kitamura.simpleaddressbook.models.randomuser.Result;

/**
 * Created by Darren on 2016-12-14.
 */

public interface MainView {
    void showProgressBar();
    void hideProgressBar();
    void showNetworkError();
    void updateContactRecyclerView(List<Result> newContacts);
}
