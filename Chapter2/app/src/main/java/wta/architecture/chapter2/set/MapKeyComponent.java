package wta.architecture.chapter2.set;

import java.util.Map;

import dagger.Component;

@Component(modules = Map2Module.class)
public interface MapKeyComponent {
    Map<Animal, String> getStringsByAnimal();

    Map<Class<? extends Number>, String> getStringsByNumber();
}
