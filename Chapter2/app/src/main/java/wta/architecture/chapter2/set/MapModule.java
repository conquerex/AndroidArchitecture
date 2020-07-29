package wta.architecture.chapter2.set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import wta.architecture.chapter2.common.Foo;

@Module
public class MapModule {
    @Provides
    @IntoMap
    @StringKey("foo")
    static Long provideFooValue() {
        return 100L;
    }

    @Provides
    @IntoMap
    @ClassKey(Foo.class)
    static String provideFooStr() {
        return "Foooo";
    }
}
