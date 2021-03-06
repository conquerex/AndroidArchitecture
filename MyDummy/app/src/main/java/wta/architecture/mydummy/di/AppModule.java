package wta.architecture.mydummy.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wta.architecture.mydummy.App;
import wta.architecture.mydummy.util.SingleLiveEvent;

/*
    애플리케이션 범위 또는 액티비티 범위로 한정하여
    객체를 관리하고 의존성을 주입하도록
    AppModule과 ActivityModule을 정의한다.
    .
    (p431)
    ViewModelModule을 애플리케이션 범위로 관리하도록 AppModule에 포함한다.
 */
@Module(includes = {
        ViewModelModule.class,
        RetrofitModule.class})
public class AppModule {
    @Provides
    @Singleton
    Application provideApp(App app) {
        return app;
    }

    @Provides
    @Singleton
    @AppContext
    Context provideContext(App app) {
        return app;
    }

    // 앱의 오류 이벤트를 처리하는 SingleLiveEvent
    @Provides
    @Singleton
    @Named("errorEvent")
    SingleLiveEvent<Throwable> provideErrorEvent() {
        return new SingleLiveEvent<>();
    }

    /*
        AppModule에 @Singleton 스코프로 바인딩하게 되므로 앱 전역에서 동일한 Retrofit 객체를 주입하게 된다.
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
}
