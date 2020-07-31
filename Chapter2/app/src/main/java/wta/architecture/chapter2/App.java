package wta.architecture.chapter2;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

//public class App extends Application {
public class App extends Application implements HasAndroidInjector {

//    private AppComponent appComponent;
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
//        appComponent = DaggerAppComponent.factory()
//                .create(this, new AppModule());
        DaggerAppComponent.factory().create(this).inject(this);
    }

//    public AppComponent getAppComponent() {
//        return appComponent;
//    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
