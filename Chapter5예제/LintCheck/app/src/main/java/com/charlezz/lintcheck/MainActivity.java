package com.charlezz.lintcheck;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/*
    p401
    <Android Lint>
    앱을 실행하거나 테스트 사례를 작성하지 않고도 코드의 구조적 문제를 식별하고 수정할 수 있다.
    탐지된 각 문제는 설명 메시지 및 심각도 수준과 함께 보고되므로 개선이 시급한 순서대로 신속히 우선순위를 결정할 수 있다.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView 메서드 사용 시 lint에 의한 에러 메시지 노출
        setContentView(R.layout.activity_main);

        DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}
