package wta.architecture.chapter2.counter;

import dagger.Module;
import dagger.Provides;

@Module
public class CounterModule {
    int next = 999;

    @Provides
    Integer provideInteger() {
        System.out.println("computing....");
        return next++;
    }
}
