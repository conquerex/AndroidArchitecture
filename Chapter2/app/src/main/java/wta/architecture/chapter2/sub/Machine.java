package wta.architecture.chapter2.sub;

public class Machine {
    private MachineComponent component;

    public Machine(MachineComponent.Builder builder) {
        component = builder.setMachineModule(new MachineModule()).build();
        component.inject(this);
    }

    // extract : 추출, 추출물
    public Coffee extract() {
        return component.getCoffee();
    }
}
