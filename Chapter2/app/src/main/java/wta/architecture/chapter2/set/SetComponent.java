package wta.architecture.chapter2.set;

import dagger.Component;

@Component(modules = SetModule.class)
public interface SetComponent {
    void inject(Foo3 foo3);
}
