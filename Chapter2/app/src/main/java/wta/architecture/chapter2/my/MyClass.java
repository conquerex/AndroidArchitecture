package wta.architecture.chapter2.my;

import javax.inject.Inject;
import javax.inject.Named;

public class MyClass {
    @Inject
    @Hello
    String strHello;

    @Inject
    @Named("world")
    String strWorld;

    public String getStrHello() {
        return strHello;
    }

    public String getStrWorld() {
        return strWorld;
    }
}
