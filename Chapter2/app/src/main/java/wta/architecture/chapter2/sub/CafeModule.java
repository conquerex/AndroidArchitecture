package wta.architecture.chapter2.sub;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MachineComponent.class)
public class CafeModule {
    @Provides
    Water provideWater() {
        return new Water();
    }

    @Provides
    Machine provideMachine(MachineComponent.Builder builder) {
        return new Machine(builder);
    }
}
