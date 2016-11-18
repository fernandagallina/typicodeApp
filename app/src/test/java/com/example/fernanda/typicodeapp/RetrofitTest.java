package com.example.fernanda.typicodeapp;

import com.example.fernanda.typicodeapp.model.User;
import com.example.fernanda.typicodeapp.model.UserService;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fernanda on 17/11/16.
 */

public class RetrofitTest {

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

    @Test
    public void test() throws Exception {

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(USER_1));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<User> call = service.userTest();
        assertTrue(call.execute() != null);

        mockWebServer.shutdown();
    }

}
