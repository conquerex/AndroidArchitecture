package wta.architecture.chapter2.dependencies;

import dagger.Component;

@Component(modules = ModuleA.class)
public interface ComponentA {
    String provideString(); // 프로비전 메서드
}
