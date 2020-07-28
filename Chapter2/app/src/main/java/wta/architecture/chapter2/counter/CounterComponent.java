package wta.architecture.chapter2.counter;

import dagger.Component;

@Component(modules = CounterModule.class)
public interface CounterComponent {
    void inject(Counter counter);
}
