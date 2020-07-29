package wta.architecture.chapter2.sub;

import javax.inject.Inject;

public class Coffee {
    private final CoffeeBean coffeeBean;
    private final Water water;

    // @Inject : 의존성 주입을 받을 필드
    // CafeModule(water)과 MachineModule(coffeeBean)로부터 provideWater, provideCoffeeBean를 통해 주입받는다.
    @Inject
    public Coffee(CoffeeBean coffeeBean, Water water) {
        this.coffeeBean = coffeeBean;
        this.water = water;
    }
}
