package wta.architecture.chapter2.common;

import dagger.Module;
import dagger.Provides;

@Module
public class HelloModule {
    @Provides
    String providesString() {
        return "hello";
    }
}
