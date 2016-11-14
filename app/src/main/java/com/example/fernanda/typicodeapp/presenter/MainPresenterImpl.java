package com.example.fernanda.typicodeapp.presenter;

import com.example.fernanda.typicodeapp.ui.MainView;
import com.example.fernanda.typicodeapp.model.User;
import com.example.fernanda.typicodeapp.model.UserService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 13/11/2016.
 */

public class MainPresenterImpl implements MainPresenter {

    MainView view;
    Retrofit retrofit;

    @Inject
    public MainPresenterImpl(Retrofit retrofit, MainView view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void loadUsers() {
        Call<List<User>> callUsers;
        final UserService service = retrofit.create(UserService.class);
        callUsers = service.users();

        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                view.showUsers(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                view.showError("Fail");
            }
        });
    }

    public MainView getView() {
        return view;
    }
}
