package wta.architecture.mydummy.ui;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import wta.architecture.mydummy.R;
import wta.architecture.mydummy.databinding.ActivityMainBinding;

/*
    (p431, MainActivity 설정하기)
    MainActivity를 위한 서브 컴포넌트를 생성하는
    @ContributesAndroidInject를 사용한다.
    MainActivity 범위 내 객체 바인딩 담당을 MainModule이 한다.
    .
    (p432)
    멤버 인젝션을 하도록 DaggerAppCompatActivity를 상속한다
 */
public class MainActivity extends DaggerAppCompatActivity {

    /*
        바인딩 클래스 주입
        .
        (p432)
        MainActivity의 ActivityMainBinding에 대해 Lazy 인젝션을 하는 이유는
        액티비티 생성이 끝나기 전에 setContentView()가 호출되면 안되기 때문이다.
    */
    @Inject
    Lazy<ActivityMainBinding> binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // 이 액티비티를 lifecycleOwner로 설정하여,
        // 생명 주기에 안전하게 데이터 바인딩을 할 수 있도록 한다.
        binding.get().setLifecycleOwner(this);
    }
}