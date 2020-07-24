package com.charlezz.javaapp.di;

import com.charlezz.javaapp.di.scope.ActivityScope;
import com.charlezz.javaapp.feature.MainActivity;
import com.charlezz.javaapp.feature.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModules {
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
