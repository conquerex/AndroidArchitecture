package wta.architecture.chapter2.dependencies;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleA {
    @Provides
    String provideString(){
       return "String from Aaaa";
    }
}
