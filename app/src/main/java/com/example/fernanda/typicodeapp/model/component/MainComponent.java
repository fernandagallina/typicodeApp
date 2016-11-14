package com.example.fernanda.typicodeapp.model.component;

import android.app.Activity;

import com.example.fernanda.typicodeapp.util.CustomScope;
import com.example.fernanda.typicodeapp.ui.MainFragment;
import com.example.fernanda.typicodeapp.model.module.MainModule;

import dagger.Component;

/**
 * Created by Fernanda on 12/11/2016.
 */

@CustomScope
@Component(dependencies = NetComponent.class, modules = MainModule.class) //dependencies = NetComponent.class,
public interface MainComponent {

    void inject(MainFragment fragment);

    void inject(Activity activity);
}
