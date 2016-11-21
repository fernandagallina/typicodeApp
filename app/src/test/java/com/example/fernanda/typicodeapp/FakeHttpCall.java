package com.example.fernanda.typicodeapp;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fernanda on 21/11/16.
 */

public class FakeHttpCall {

    public IOException exception;
    Response response;
    public static final String A_URL = "http://www.google.com";
    private MockWebServer mockWebServer;

    public void setResponse(String json, int httpCode) {
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), json);
        response = new Response.Builder()
                .request(new Request.Builder().url(A_URL).build())
                .protocol(Protocol.HTTP_1_1).code(httpCode).body(responseBody).build();
    }

    public OkHttpClient getClient() {
        OkHttpClient okHttpClient = new OkHttpClient() {
            @Override
            public Call newCall(Request request) {
                return new FakeCall();
            }
        };
        return okHttpClient;
    }

    public Retrofit getRetrofit(String json, MockWebServer mockWebServer) {
        setResponse(json, 200);
        OkHttpClient okHttpClient = getClient();

        return new Retrofit.Builder()
                .baseUrl(mockWebServer.url(A_URL).toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public class FakeCall implements Call {

        @Override
        public Request request() {
            return null;
        }

        @Override
        public Response execute() throws IOException {
            return response;
        }

        @Override
        public void enqueue(Callback responseCallback) {

            if (response != null) {
                try {
                    responseCallback.onResponse(this, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                responseCallback.onFailure(this, exception);
            }
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public boolean isCanceled() {
            return false;
        }
    }
}
