package wta.architecture.chapter2.common;

import dagger.Component;

@Component(modules = {CommonModule.class})
public interface NoStrComponent {
    void inject(Foo foo);
}
