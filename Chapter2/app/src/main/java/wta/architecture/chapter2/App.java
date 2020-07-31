package wta.architecture.chapter2;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;

//public class App extends Application {
//public class App extends Application implements HasAndroidInjector {
public class App extends DaggerApplication {

//    private AppComponent appComponent;

//    @Inject
//    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
////        appComponent = DaggerAppComponent.factory()
////                .create(this, new AppModule());
//        DaggerAppComponent.factory().create(this).inject(this);
//    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }

//    public AppComponent getAppComponent() {
//        return appComponent;
//    }

//    @Override
//    public AndroidInjector<Object> androidInjector() {
//        return dispatchingAndroidInjector;
//    }
}
