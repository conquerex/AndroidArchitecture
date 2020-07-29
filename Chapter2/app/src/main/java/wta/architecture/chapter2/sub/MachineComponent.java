package wta.architecture.chapter2.sub;

import dagger.Subcomponent;

/**
 * CafeComponent의 Subcomponent
 * CafeComponent는 MachineComponent.Builder를 제외하고는 서브 컴포넌트가 가진 모듈의 의존성을 제공 받을 수 없다.
 * MachineComponent는 Water를 받을 수 있음
 */
@Subcomponent(modules = MachineModule.class)
public interface MachineComponent {
    Coffee getCoffee(); // 프로비전 메서드, MachineModule로부터 제공받거나 주입받은 Coffee 객체를 반환한다.
    void inject(Machine machine); // 멤버-인젝션 메서드

    @Subcomponent.Builder
    interface Builder {
        Builder setMachineModule(MachineModule module);
        MachineComponent build();
    }
}
