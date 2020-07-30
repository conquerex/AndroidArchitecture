package wta.architecture.chapter2.dependencies;

import dagger.Component;

/**
 * 각각의 컴포넌트는 서로 다른 모듈에 의존하고, ComponentB는 ComponentA를 상속하는 모습.
 * ComponentA의 프로비전 메서드로 provideString을 선언하였으므로
 * ComponentB는 ComponentA로부터 String 타입의 의존성을 제공받을 수 있다.
 */
@Component(modules = ModuleB.class, dependencies = ComponentA.class)
public interface ComponentB {
    void inject(Foo4 foo);
}
