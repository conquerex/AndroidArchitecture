package wta.architecture.chapter2;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * first
 * 애플리케이션 생명 주기와 Dagger 컴포넌트의 생명 주기를 같이하는 애플리케이션 컴포넌트
 * 액티비티 또는 서비스를 위한 Dagger 컴포넌트는 애플리케이션 컴포넌트의 서브 컴포넌트로 구성
 * AppComponent는 빌더 또는 팩토리를 통해 성성 - 여기서는 팩토리
 *
 * second
 * AndroidInjectionModule에는 안드로이드 프레임워크 관련 클래스에 의존성 주입을 위임할
 * AndroidInjector<?>의 팩토리를 멀티 바인딩으로 관리한다.
 * AndroidInjector는 멤버 인젝션을 위한 inject() 메서드가 포함
 */
@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {
//    MainActivityComponent.Builder mainBuilder();

//    void inject(App app);

    // 컴포넌트 객체화
    // AndroidInjector.Factory는 App 인스턴스를 그래프에 바인딩하고
    // Component를 반환하는 create() 메서드가 이미 포함되어 있ㅇ므로 별도 정의 필요 없다.
    @Component.Factory
    interface Factory extends AndroidInjector.Factory<App> {
//        AppComponent create(
//                // 애플리케이션 인스턴스는 시스템에 의해서만 생성될 수 있어
//                // 애플리케이션이 생성된 후 팩토리의 @BindsInstance 메서드를 통해 오브젝트 그래프에 바인딩한다.
//                @BindsInstance App app,
//                AppModule appModule
//        );
    }
}
