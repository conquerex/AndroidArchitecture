package wta.architecture.chapter2.set;

import dagger.MapKey;

@MapKey
public @interface NumberKey {
    Class<? extends Number> value();
}
