package wta.architecture.chapter2.common;

import dagger.Component;

@Component(modules = {CommonModule.class, HelloModule.class})
public interface StrComponent {
    void inject(Foo foo);
}
