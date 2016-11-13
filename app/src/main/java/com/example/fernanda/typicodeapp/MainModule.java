package com.example.fernanda.typicodeapp;

import com.example.fernanda.typicodeapp.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Fernanda on 12/11/2016.
 * Will provide the View to Presenter when it is injected
 * Presenter will use this view as reference to MainFragment
 */

@Module
public class MainModule {

    private final MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

//    @Provides
//    public MainPresenter providePresenter() {
//        return new MainPresenterImpl(view);
//    }

    @Provides
    @CustomScope
    MainView providesMainView() {
        return view;
    }
}
