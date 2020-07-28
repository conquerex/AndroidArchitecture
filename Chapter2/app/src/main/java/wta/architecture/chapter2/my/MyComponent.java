package wta.architecture.chapter2.my;

import androidx.annotation.Nullable;

import dagger.Component;
import dagger.MembersInjector;

/**
 * @Component에 참조된 모듈 클래스(MyModule)로부터 의존성을 제공받는다.
 * Dagger는 컴파일 타임에 @Component를 구현한 클래스를 생성하는데,
 * 이때 클래스의 이름은 'Dagger'라는 접두어가 붙는다.
 * MyComponent --> Dagger에의해 생성된 클래스 : DaggerMyComponent
 */
@Component(modules = MyModule.class)
public interface MyComponent {
    // 해당 인터페이스 내에는 제공할 의존성들을 메서드로 정의해야 한다.
    String getString(); // 브로비전 메서드, 바인드된 모듈로부터 의존성을 제공

    @Nullable
    Integer getInteger();

    MembersInjector<MyClass> getInjector();

    // 멤버-인젝션 메서드
    // 하나의 매개변수, void 또는 매개변수 타입을 반환형으로
    void inject(MyClass myClass);

    // 프로비전 메서드
//    MyClass getMyClass();
}
