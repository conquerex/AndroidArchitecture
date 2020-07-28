package wta.architecture.chapter2.person;

import javax.inject.Inject;

public class PersonB {
    @Inject
    String name; // 필드 주입

    private int age;

    @Inject
    public void setAge(int age) { // 메서드 주입
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
