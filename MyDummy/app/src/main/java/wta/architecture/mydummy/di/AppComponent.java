package wta.architecture.mydummy.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import wta.architecture.mydummy.App;

/*
    Component는 모듈을 조합
    컴포넌트에 의존성을 주입할 장소를 선언 ---> Activity 등등
    분석을 해서 적절한 곳에 (@Inject가 있는 곳에) 적용
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, // dagger.android 사용을 위한 설정
        ActivityModule.class, // 액티비티 스코프 모듈
        AppModule.class}) // 애플리케이션 스코프 모듈
public interface AppComponent extends AndroidInjector<App> {
    // 안드로이드 애플리케이션 컴포넌트 팩토리 정의
    @Component.Factory
    abstract class Factory implements AndroidInjector.Factory<App> {
        //
    }
}
