package wta.architecture.chapter2.common;

import dagger.BindsOptionalOf;
import dagger.Module;

@Module
public abstract class CommonModule {
    @BindsOptionalOf
    abstract String BindsOptionalOfString();
}
