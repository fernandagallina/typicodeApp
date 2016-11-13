package com.example.fernanda.typicodeapp.model.component;

import com.example.fernanda.typicodeapp.MainActivity;
import com.example.fernanda.typicodeapp.MainFragment;
import com.example.fernanda.typicodeapp.model.module.AppModule;
import com.example.fernanda.typicodeapp.model.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Fernanda on 12/11/2016.
 *
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();

    void inject(MainActivity activity);

//    void inject(MainFragment fragment);
}
