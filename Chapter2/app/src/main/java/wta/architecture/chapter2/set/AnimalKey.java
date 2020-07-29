package wta.architecture.chapter2.set;

import dagger.MapKey;

@MapKey
public @interface AnimalKey {
    Animal value();
}
