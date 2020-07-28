package wta.architecture.chapter2;

import org.junit.Test;

import org.junit.Assert;

import dagger.MembersInjector;

public class ExampleUnitTest {
    // Dagger에 의해 생성된 클래스를 통해 의존성을 제공받아본다.
    @Test
    public void testHelloWorld() {
        MyComponent myComponent = DaggerMyComponent.create();
        System.out.println("result1 ::: " + myComponent.getString());
    }

    @Test
    public void testMemberInjection() {
        MyClass myClass = new MyClass();
        String str = myClass.getStr();
        MyComponent myComponent = DaggerMyComponent.create();
        myComponent.inject(myClass);
        str = myClass.getStr();
        Assert.assertNotNull("조회 결과 null ::: ", str); // null이 아님을 확인
        Assert.assertEquals("Hello World", str);
    }

    @Test
    public void testMemberInjection2() {
        MyClass myClass = new MyClass();
        String str = myClass.getStr();
        System.out.println("result2 ::: " + str);
        MyComponent myComponent = DaggerMyComponent.create();
        MembersInjector<MyClass> injector = myComponent.getInjector();
        // 반홤된 MembersInjector 객체의 injectMembers(T) 메서드를 호출하면 멤버-인젝션 메서드와 동일한 작업을 수행
        injector.injectMembers(myClass);
        str = myClass.getStr();
        Assert.assertNotNull("조회 결과 null ::: ", str); // null이 아님을 확인
        Assert.assertEquals("Hello World", str);
    }
}
