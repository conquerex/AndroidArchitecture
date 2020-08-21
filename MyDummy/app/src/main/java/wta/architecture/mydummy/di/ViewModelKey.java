package wta.architecture.mydummy.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.MapKey;

/*
    커스텀 ViewModelProvider.Factorydhk ViewModel을 멀티 바인딩하고자...
      --> ViewModelKey, AppViewModelFactory, ViewModelModule
 */
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
