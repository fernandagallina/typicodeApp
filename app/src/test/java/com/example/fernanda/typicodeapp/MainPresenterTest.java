package com.example.fernanda.typicodeapp;

import com.example.fernanda.typicodeapp.model.User;
import com.example.fernanda.typicodeapp.model.UserService;
import com.example.fernanda.typicodeapp.model.module.MainModule;
import com.example.fernanda.typicodeapp.presenter.MainPresenter;
import com.example.fernanda.typicodeapp.presenter.MainPresenterImpl;
import com.example.fernanda.typicodeapp.ui.MainView;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fernanda on 17/11/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainPresenterTest {

    private String USER_1 = "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"Leanne Graham\",\n" +
            "  \"username\": \"Bret\",\n" +
            "  \"email\": \"Sincere@april.biz\",\n" +
            "  \"address\": {\n" +
            "    \"street\": \"Kulas Light\",\n" +
            "    \"suite\": \"Apt. 556\",\n" +
            "    \"city\": \"Gwenborough\",\n" +
            "    \"zipcode\": \"92998-3874\",\n" +
            "    \"geo\": {\n" +
            "      \"lat\": \"-37.3159\",\n" +
            "      \"lng\": \"81.1496\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"phone\": \"1-770-736-8031 x56442\",\n" +
            "  \"website\": \"hildegard.org\",\n" +
            "  \"company\": {\n" +
            "    \"name\": \"Romaguera-Crona\",\n" +
            "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "    \"bs\": \"harness real-time e-markets\"\n" +
            "  }\n" +
            "}";

    private Retrofit retrofit;
    private MockWebServer mockWebServer;
    private BehaviorDelegate<UserService> delegate;

    private MainPresenterImpl mainPresenter;

    @Mock private MainView view;

    @Before
    public void setup() throws Exception {
        configRetrofit();
        mainPresenter = new MainPresenterImpl(retrofit, view);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSuccessWithLoadUsers() {

        List<User> users = new ArrayList<>();
        User user = new User("Leanne Graham", "Bret", "Sincere@april.biz", "1-770-736-8031 x56442", "hildegard.org", 1);
        users.add(user);
//        doNothing().when(mainPresenter).loadUsers();
        mainPresenter.loadUsers();
        verify(view).showUsers(users); // AQUI nao funciona
    }


    public void configRetrofit() throws IOException {
        OkHttpClient.Builder client = new OkHttpClient.Builder();


        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(USER_1).setResponseCode(HttpURLConnection.HTTP_OK));
        mockWebServer.start();

        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();


    }

    @After
    public void teardown() throws Exception{
        mockWebServer.shutdown();
    }


}
