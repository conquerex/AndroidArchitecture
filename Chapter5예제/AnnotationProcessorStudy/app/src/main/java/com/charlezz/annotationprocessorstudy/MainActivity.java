package com.charlezz.annotationprocessorstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charlezz.annotation.CharlesIntent;

@CharlesIntent
public class MainActivity extends AppCompatActivity {

    /*
        <애노테이션>
        자바 소스 코드에 추가할 수 있는 메타 데이터의 한 형태
        런타임 또는 컴파일 타임에 애노테이션을 어떻게 해석하느냐에 따라 생산성과 협업에 많은 영향을 끼친다.
        .
        특정 소스 코드라인에 삽압하여 코드의 가독성을 증가
        애노테이션을 사용하여 보일러 플레이트 코드를 자동으로 생성
        특정 데이터들에 대한 유효성 검사
        생산성 증대
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(Charles.intentForSecondActivity(this));
    }
}
