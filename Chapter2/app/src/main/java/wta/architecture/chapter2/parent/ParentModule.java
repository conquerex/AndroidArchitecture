package wta.architecture.chapter2.parent;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module(subcomponents = ChildComponent.class)
public class ParentModule {
    @Provides
    @IntoSet
    String string1() {
        return "parent string 11111";
    }

    @Provides
    @IntoSet
    String string2() {
        return "parent string 222222";
    }
}
