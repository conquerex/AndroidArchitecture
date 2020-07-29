package wta.architecture.chapter2.sub;

import dagger.Module;
import dagger.Provides;

@Module
public class MachineModule {
    @Provides
    CoffeeBean provideCoffeeBean() {
        return new CoffeeBean();
    }
}
