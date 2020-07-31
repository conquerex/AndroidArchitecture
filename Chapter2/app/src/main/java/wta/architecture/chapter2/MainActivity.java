package wta.architecture.chapter2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends AppCompatActivity implements HasAndroidInjector {

//    @Inject
//    SharedPreferences sharedPreferences;
//
//    @Inject
//    String activityName;
//
//    MainActivityComponent component;

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Inject
    @Named("app")
    String appString;

    @Inject
    @Named("app")
    String activityString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // super.onCreate 메서드를 호출하기 전에 AndroidInjection.inject를 호출
        // AndroidInjection.inject를 호출하면 App으로부터 DispatchingAndroidInjector<Object>를 얻고
        // 이를 통해 MainActivity에 맞는 AndroidInjector.Factory를 클래스 이름을 통해 찾는다. --> @ClassKey(MainActivity.class)
        // 팩토리를 통해 생성된 MainActivitySubcomponent는 액티비티에서 호출한 inject()를 통해 의존성 주입이 완료된다.
        // AppModule에서 팩토리를 통해 MainActivitySubcomponent가 생성 --> 액티비티에서 호출한 inject()를 통해 의존성 주입
        AndroidInjection.inject(this);
        // 멤버 인젝션 이후, 의존성 주입이 잘 되었는지 확인
        Log.e("MainActivity", appString);
        Log.e("MainActivity", activityString);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 1. App
         * 애플리케이션으로부터 AppComponent 인스턴스를 가져와서
         * 2. MainActivity
         * MainActivityComponent.Builder를 제공받아 액티비티 모듈과 인스턴스를 바인딩하고
         * MainActivityComponent를 생성한 뒤 의존성을 주입하는 모습
         * 3.
         * 애플리케이션 컴포넌트로부터 SharedPreferences를 주입받고
         * 액티비티 커포넌트로부터 String 객체를 주입받았다.
         */
//        component = ((App)getApplication()).getAppComponent()
//                .mainBuilder() // MainActivityComponent 객체화
//                .setModule(new MainActivityModule()) // 모듈 생성
//                .setActivity(this) // 컴포넌트에 Activity 인스턴스를 넘겨 바인드
//                .build();
//        component.inject(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commit();
    }

//    public MainActivityComponent getComponent() {
//        return component;
//    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}