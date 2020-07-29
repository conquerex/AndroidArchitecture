package wta.architecture.chapter2.parent;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class ChildModule {

    @Provides
    @IntoSet
    String string3() {
        return "parent string 333333";
    }
}
