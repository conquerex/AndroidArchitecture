package wta.architecture.chapter2.parent;

import java.util.Set;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public abstract class MultibindsModules {
    @Multibinds
    abstract Set<String> strings();
}
