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

    // DaggerApplication를 상속 --> applicationInjector 메서드를 구현
    // 이 때 애플리케이션 컴포넌트를 반환시키는 코드를 작성하기만 하면 기존의 모든 코드를 대체할 수 있다.
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent
                .factory().create(this);
    }

//    public AppComponent getAppComponent() {
//        return appComponent;
//    }

//    @Override
//    public AndroidInjector<Object> androidInjector() {
//        return dispatchingAndroidInjector;
//    }
}
