package wta.architecture.chapter2.parent;

import java.util.Set;

import dagger.Component;

@Component(modules = MultibindsModules.class)
public interface MultibindsComopnent {
    Set<String> getStrings();
}
