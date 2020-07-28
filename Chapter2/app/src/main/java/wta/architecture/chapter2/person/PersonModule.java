package wta.architecture.chapter2.person;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonModule {
    @Provides
    String provideName() {
        return "kook";
    }

    @Provides
    int provideAge() {
        return 99;
    }
}
