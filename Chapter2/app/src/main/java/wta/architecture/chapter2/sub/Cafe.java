package wta.architecture.chapter2.sub;

import javax.inject.Inject;

public class Cafe {
    // @Inject : 의존성 주입을 받을 필드
    // CafeModule로부터 provideMachine을 통해 주입받는다.
    @Inject
    Machine coffeeMachine;

    public Cafe() {
        DaggerCafeComponent.create().inject(this);
    }

    public Coffee orderCoffee() {
        return coffeeMachine.extract();
    }
}
