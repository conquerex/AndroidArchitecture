package com.charlezz.reflection;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReflectionTest r = new ReflectionTest();

        // 간단한 리플렉션 예제
        /*
            getDeclaredMethods() 메서드를 호출하면 Method 배열을 얻을 수 있다.
            ...
            public static java.lang.String java.lang.String.copyValueOf(char[])
            public static java.lang.String java.lang.String.copyValueOf(char[],int,int)
            public static java.lang.String java.lang.String.format(java.util.Locale,java.lang.String,java.lang.Object[])
            static int java.lang.String.indexOf(java.lang.String,java.lang.String,int)
            그 외 여러개
         */
//        r.simpleExample();

        // 리플렉션을 사용하는 3가지 단계
        /*
            1. 조작하려는 클래스에 대한 java.lang.Class 객체를 얻는 것
            2. 클래스 멤버에 접근하는 메서드를 호출
            3. 리플렉션 API를 사용하여 정보를 다루는 것
         */
        try {
            r.settingUP();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // instanceOf 연산자 시뮬레이팅하기
        /*
            E/ReflectionTest: b1 false --> int
            E/ReflectionTest: b2 true  --> String
         */
        r.simulateInstanceOf();

        /*
            (예시)
            메소드 이름 = copyValueOf
            정의된 클래스이름 = class java.lang.String
            param #0 class [C
            param #1 int
            param #2 int
            return type = class java.lang.String
         */
        // 메서드 정보 가져오기
//        r.findoutMethods();

        /*
            (예시)
            생성자 이름 = java.lang.String
            정의된 클래스이름 = class java.lang.String
            param #0 int
            param #1 int
            param #2 class [C
         */
        // 생성자 정보 가져오기
        r.findConstructors();

        /*
            (예시)
            필드명 = count
            정의된클래스 = class java.lang.String
            필드타입 = int
            접근제어자 = private final
         */
        // 필드 정보 가져오기
        r.findoutFields();

        // 이름으로 메서드 호출하기
        /*
            length=11
            subStr=Hello
         */
        r.invoke();

        // 새로운 객체 생성하기
        /*
            Hello! My name is Charles and I'm 20 years old
         */
        r.creatingObject();

        // 필드정보 변경하기
        r.changeValuesOfFields();
        r.invokePrivate();
    }
}
