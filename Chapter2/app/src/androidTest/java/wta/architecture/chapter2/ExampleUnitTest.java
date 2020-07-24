package wta.architecture.chapter2;

import org.junit.Test;

public class ExampleUnitTest {
    // Dagger에 의해 생성된 클래스를 통해 의존성을 제공받아본다.
    @Test
    public void testHelloWorld() {
        MyComponent myComponent = DaggerMyComponent.create();
        System.out.println("result ::: " + myComponent.getString());
    }
}
