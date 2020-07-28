package wta.architecture.chapter2;

import androidx.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

// @Module : 의존성을 제공하는 클래스에 붙인다
@Module
public class MyModule {

    // @Provides : 의존성을 제공하는 메서드에 붙인다
    @Provides
    String provideHelloWorld() {
        return "Hello World";
    }

    @Provides
    @Nullable
    int provideAge() { //나이 제공
        return 100;
    }
}