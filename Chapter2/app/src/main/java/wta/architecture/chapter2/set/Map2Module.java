package wta.architecture.chapter2.set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class Map2Module {
    @IntoMap
    @AnimalKey(Animal.CAT)
    @Provides
    String provideCat() {
        return "Neowwwww";
    }

    @IntoMap
    @AnimalKey(Animal.DOG)
    @Provides
    String provideDog() {
        return "Bow-wowwww";
    }

    @IntoMap
    @NumberKey(Float.class)
    @Provides
    String provideFloatValue() {
        return "100f";
    }

    @IntoMap
    @NumberKey(Integer.class)
    @Provides
    String provideIntegerValue() {
        return "1";
    }
}
