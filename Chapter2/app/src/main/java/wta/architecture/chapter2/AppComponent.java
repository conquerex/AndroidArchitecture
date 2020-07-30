package wta.architecture.chapter2;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * 애플리케이션 생명 주기와 Dagger 컴포넌트의 생명 주기를 같이하는 애플리케이션 컴포넌트
 * 액티비티 또는 서비스를 위한 Dagger 컴포넌트는 애플리케이션 컴포넌트의 서브 컴포넌트로 구성
 * AppComponent는 빌더 또는 팩토리를 통해 성성 - 여기서는 팩토리
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    MainActivityComponent.Builder mainBuilder();

    void inject(App app);

    // 컴포넌트 객체화
    @Component.Factory
    interface Factory {
        AppComponent create(
                // 애플리케이션 인스턴스는 시스템에 의해서만 생성될 수 있어
                // 애플리케이션이 생성된 후 팩토리의 @BindsInstance 메서드를 통해 오브젝트 그래프에 바인딩한다.
                @BindsInstance App app,
                AppModule appModule
        );
    }
}
