package com.example.fernanda.typicodeapp;

import com.example.fernanda.typicodeapp.model.User;

import java.util.List;

/**
 * Created by Fernanda on 12/11/2016.
 */

public interface MainView {

    void showUsers(List<User> users);

    void showError(String message);
}
