package wta.architecture.chapter2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    String activityName;

    MainActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        component = ((App)getApplication()).getAppComponent()
                .mainBuilder() // MainActivityComponent 객체화
                .setModule(new MainActivityModule()) // 모듈 생성
                .setActivity(this) // 컴포넌트에 Activity 인스턴스를 넘겨 바인드
                .build();
        component.inject(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commit();
    }

    public MainActivityComponent getComponent() {
        return component;
    }
}