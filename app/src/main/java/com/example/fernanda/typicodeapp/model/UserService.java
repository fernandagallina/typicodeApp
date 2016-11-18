package com.example.fernanda.typicodeapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Fernanda on 12/11/2016.
 */

public interface UserService {

    @GET("/users")
    Call<List<User>> users();

    // To test retrofit
    @GET("/users/1")
    Call<User> userTest();
}
