package wta.architecture.chapter2;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/**
 * second
 * MainActivity의 인스턴스에 멤버 인젝션을 담당할 MainActivitySubcomponent를 서브 컴포넌트로 연결한다.
 */
//@Module(subcomponents = MainActivityComponent.class)
//@Module(subcomponents = MainActivitySubcomponent.class)
@Module
public abstract class AppModule {
    // 애플리케이션의 생명 주기 동안 싱글턴으로 취급할 SharedPreferences를 제공
    // 싱글턴이 아닌 매번 인스턴스를 생성하거나 시스템으로부터 가져오고 싶다면 @Singleton 애노테이션을 제거하면 된다.
//    @Provides
//    @Singleton
//    SharedPreferences provideSharedPreferences(App app) {
//        return app.getSharedPreferences(
//                "default", Context.MODE_PRIVATE
//        );
//    }

    // @Singleton 스코프에서 의존성을 주입하는지 확인하도록 문자열을 하나 반환하는 프로바이드 메서드를 정의
    @Named("app")
    @Provides
    @Singleton
    static String provideString() {
        return ".....String from AppModule";
    }

//    /**
//     * bindAndroidInjectorFactory 메서드는
//     * AndroidInjectionModule 내부에 있는 Map에 AndroidInjector.Factory를 멀티 바인딩한다.
//     * 이로써 서브 컴포넌트들이 편하게 멤버 인젝션을 할 수 있도록 인젝터 팩토리들을 멀티 바인딩으로 관리한다.
//     */
//    // @Binds : module 내, 추상 메서드에, 하나의 매개변수만, @Provide 대신 좀 더 효율적으로
//    // 메소드 내부에서 직접 인스턴스를 생성하지 않고 연결만 하는 경우
//    @Binds
//    // 멀티 바인딩 : Dagger의 멀티 바인딩을 사용하여
//    // 여러 모듈에 있는 같은 타입의 객체를 하나의 Set 또는 Map 형태로 관리할 수 있다.
//    @IntoMap
//    @ClassKey(MainActivity.class)
//    abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(MainActivitySubcomponent.Factory factory);

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
