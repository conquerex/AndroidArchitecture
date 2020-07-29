package wta.architecture.chapter2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import dagger.MembersInjector;
import wta.architecture.chapter2.common.Bar;
import wta.architecture.chapter2.common.BindsComponent;
import wta.architecture.chapter2.common.DaggerBindsComponent;
import wta.architecture.chapter2.common.DaggerNoStrComponent;
import wta.architecture.chapter2.common.DaggerStrComponent;
import wta.architecture.chapter2.common.Foo;
import wta.architecture.chapter2.counter.Counter;
import wta.architecture.chapter2.counter.CounterComponent;
import wta.architecture.chapter2.counter.DaggerCounterComponent;
import wta.architecture.chapter2.my.DaggerMyComponent;
import wta.architecture.chapter2.my.MyClass;
import wta.architecture.chapter2.my.MyComponent;
import wta.architecture.chapter2.parent.ChildComponent;
import wta.architecture.chapter2.parent.DaggerMultibindsComopnent;
import wta.architecture.chapter2.parent.DaggerParentComponent;
import wta.architecture.chapter2.parent.MultibindsComopnent;
import wta.architecture.chapter2.parent.ParentComponent;
import wta.architecture.chapter2.person.DaggerPersonComponent;
import wta.architecture.chapter2.person.PersonA;
import wta.architecture.chapter2.person.PersonB;
import wta.architecture.chapter2.person.PersonComponent;
import wta.architecture.chapter2.set.Animal;
import wta.architecture.chapter2.set.DaggerMapComponent;
import wta.architecture.chapter2.set.DaggerMapKeyComponent;
import wta.architecture.chapter2.set.DaggerSetComponent;
import wta.architecture.chapter2.set.Foo3;
import wta.architecture.chapter2.set.MapComponent;
import wta.architecture.chapter2.set.MapKeyComponent;
import wta.architecture.chapter2.sub.Cafe;

public class ExampleUnitTest {
    // Dagger에 의해 생성된 클래스를 통해 의존성을 제공받아본다.
    @Test
    public void testHelloWorld() {
        MyComponent myComponent = DaggerMyComponent.create();
//        System.out.println("result1 ::: " + myComponent.getString());
    }

    @Test
    public void testMemberInjection() {
        MyClass myClass = new MyClass();
//        String str = myClass.getStr();
//        MyComponent myComponent = DaggerMyComponent.create();
//        myComponent.inject(myClass);
//        str = myClass.getStr();
//        Assert.assertNotNull("조회 결과 null ::: ", str); // null이 아님을 확인
//        Assert.assertEquals("Hello World", str);

        DaggerMyComponent.create().inject(myClass);
        System.out.println("............");
        System.out.println(myClass.getStrHello());
        System.out.println(myClass.getStrWorld());
    }

    @Test
    public void testMemberInjection2() {
        MyClass myClass = new MyClass();
//        String str = myClass.getStr();
//        System.out.println("result2 ::: " + str);
//        MyComponent myComponent = DaggerMyComponent.create();
//        MembersInjector<MyClass> injector = myComponent.getInjector();
//        // 반홤된 MembersInjector 객체의 injectMembers(T) 메서드를 호출하면 멤버-인젝션 메서드와 동일한 작업을 수행
//        injector.injectMembers(myClass);
//        str = myClass.getStr();
//        Assert.assertNotNull("조회 결과 null ::: ", str); // null이 아님을 확인
//        Assert.assertEquals("Hello World", str);
    }

    @Test
    public void testInjection() {
        PersonComponent personComponent = DaggerPersonComponent.create();
        PersonA personA = personComponent.getPersonA();
        System.out.println(personA.getName() + " ::: " + personA.getAge());

        PersonB personB = new PersonB();
        DaggerPersonComponent.create().inject(personB);

        Assert.assertEquals("kook", personB.getName());
        Assert.assertEquals(99, personB.getAge());
    }

    @Test
    public void testLazy() {
        CounterComponent counterComponent = DaggerCounterComponent.create();
        Counter counter = new Counter();
        counterComponent.inject(counter);
        counter.printLazy();
    }

    @Test
    public void testProvider() {
        CounterComponent counterComponent = DaggerCounterComponent.create();
        Counter counter = new Counter();
        counterComponent.inject(counter);
        counter.printProvider();
    }

    @Test
    public void testObjectIdentity() {
        MyComponent myComponent = DaggerMyComponent.create();
        Object temp1 = myComponent.getObject();
        Object temp2 = myComponent.getObject();
        Assert.assertNotNull(temp1);
        Assert.assertNotNull(temp2);
        Assert.assertSame(temp1, temp2);
    }

    @Test
    public void testFoo() {
        Foo foo = new Foo();

        System.out.println("...........");

        DaggerStrComponent.create().inject(foo);
        System.out.println(foo.str.isPresent());
        System.out.println(foo.str.get());

        DaggerNoStrComponent.create().inject(foo);
        System.out.println(foo.str.isPresent());
//        System.out.println(foo.str.get()); // error
    }

    @Test
    public void testBindsInstance() {
        String hello = "Hello world....";
        Bar bar = new Bar();
        BindsComponent bindsComponent = DaggerBindsComponent.builder().setString(hello).build();
        bindsComponent.inject(bar);
        Assert.assertEquals("Hello world....", bar.str);
    }

    @Test
    public void testMultibindingSet() {
        Foo3 foo3 = new Foo3();
        DaggerSetComponent.create().inject(foo3);
        foo3.print();
    }

    @Test
    public void testbindingMap() {
        MapComponent component = DaggerMapComponent.create();
        long value = component.getLongsByString().get("foo");
        String str = component.getStringsByClass().get(Foo.class);

        System.out.println("...........");
        System.out.println(value);
        System.out.println(str);
    }


    @Test
    public void testCustomMapKey() {
        MapKeyComponent component = DaggerMapKeyComponent.create();
        String cat = component.getStringsByAnimal().get(Animal.CAT);
        String dog = component.getStringsByAnimal().get(Animal.DOG);
        String number1 = component.getStringsByNumber().get(Integer.class);
        String number2 = component.getStringsByNumber().get(Float.class);

        System.out.println("...........testCustomMapKey");
        System.out.println(dog);
        System.out.println(cat);
        System.out.println(number1);
        System.out.println(number2);
    }

    @Test
    public void testMultibindSubcomponent() {
        ParentComponent parentComponent = DaggerParentComponent.create();
        ChildComponent childComponent = parentComponent.childCompBuilder().build();

        System.out.println("...........Subcomponent parent");
        Iterator itr = parentComponent.strings().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        System.out.println("...........Subcomponent child");
        itr = childComponent.strings().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    @Test
    public void testMultibinds() {
        MultibindsComopnent comopnent = DaggerMultibindsComopnent.create();

        System.out.println("...........testMultibinds");
        for (String s : comopnent.getStrings()) {
            System.out.println(s);
        }
    }


    @Test
    public void testCafe() {
        Cafe cafe = new Cafe();
        System.out.println("...........cafe");
        System.out.println(cafe.orderCoffee());
        System.out.println(cafe.orderCoffee());
        System.out.println(cafe.orderCoffee());
    }
}
