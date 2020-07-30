package wta.architecture.chapter2.dependencies;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleB {
    @Provides
    Integer provideInteger() {
        return 999;
    }
}
