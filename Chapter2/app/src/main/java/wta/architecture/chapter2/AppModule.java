package wta.architecture.chapter2;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MainActivityComponent.class)
public class AppModule {
    // 애플리케이션의 생명 주기 동안 싱글턴으로 취급할 SharedPreferences를 제공
    // 싱글턴이 아닌 매번 인스턴스를 생성하거나 시스템으로부터 가져오고 싶다면 @Singleton 애노테이션을 제거하면 된다.
    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(App app) {
        return app.getSharedPreferences(
                "default", Context.MODE_PRIVATE
        );
    }
}
