package ca.kitamura.simpleaddressbook.screens.main;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ca.kitamura.simpleaddressbook.screens.contactdisplay.ContactDisplayActivity;
import ca.kitamura.simpleaddressbook.R;
import ca.kitamura.simpleaddressbook.adapters.AddressBookAdapter;
import ca.kitamura.simpleaddressbook.adapters.RandomUserClickListener;
import ca.kitamura.simpleaddressbook.models.randomuser.Result;

public class MainActivity extends AppCompatActivity implements MainView, RandomUserClickListener{

    CoordinatorLayout parentLayout;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    List<Result> userList;
    AddressBookAdapter addressBookAdapter;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bind to UI
        parentLayout = (CoordinatorLayout)findViewById(R.id.main_coordinator_layout);
        recyclerView = (RecyclerView)findViewById(R.id.main_recyclerview);
        progressBar = (ProgressBar)findViewById(R.id.main_progressbar);


        //Toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.app_name);

        //Initialize Variables
        userList = new ArrayList<>();

        //RecyclerView
        addressBookAdapter = new AddressBookAdapter(userList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(addressBookAdapter);

        presenter = new MainPresenterImpl(this);
        presenter.getContacts();

    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void randomUserClicked(Result result) {
        Intent viewContactIntent = new Intent(this, ContactDisplayActivity.class);
        viewContactIntent.putExtra("contactData", result);
        startActivity(viewContactIntent);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNetworkError() {
        Snackbar.make(parentLayout, "There seems to be a problem with your connection, please check it then retry.", Snackbar.LENGTH_INDEFINITE).setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getContacts();
            }
        }).show();
    }

    @Override
    public void updateContactRecyclerView(List<Result> newContacts) {
        addressBookAdapter.setContactList(newContacts);
        addressBookAdapter.notifyDataSetChanged();
    }
}
