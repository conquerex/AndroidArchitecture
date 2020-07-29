package wta.architecture.chapter2.set;

import java.util.Map;

import dagger.Component;

@Component(modules = MapModule.class)
public interface MapComponent {
    Map<String, Long> getLongsByString();

    Map<Class<?>, String> getStringsByClass();
}
