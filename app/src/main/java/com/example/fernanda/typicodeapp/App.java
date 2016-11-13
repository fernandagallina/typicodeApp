package com.example.fernanda.typicodeapp;

import android.app.Application;

import com.example.fernanda.typicodeapp.model.component.DaggerNetComponent;
import com.example.fernanda.typicodeapp.model.component.NetComponent;
import com.example.fernanda.typicodeapp.model.module.AppModule;
import com.example.fernanda.typicodeapp.model.module.NetModule;

/**
 * Created by Fernanda on 12/11/2016.
 */

public class App extends Application {
    private static final String API_BASE_URL = "http://jsonplaceholder.typicode.com/";
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(API_BASE_URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
