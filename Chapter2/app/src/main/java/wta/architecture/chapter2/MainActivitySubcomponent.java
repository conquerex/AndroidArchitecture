package wta.architecture.chapter2;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

//@ActivityScope
//@Subcomponent(modules = MainActivityModule.class)
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
//    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MainActivity> {
        //
    }
}
