package wta.architecture.chapter2.parent;

import java.util.Set;

import dagger.Component;

@Component(modules = ParentModule.class)
public interface ParentComponent {
    Set<String> strings();

    ChildComponent.Builder childCompBuilder();
}
