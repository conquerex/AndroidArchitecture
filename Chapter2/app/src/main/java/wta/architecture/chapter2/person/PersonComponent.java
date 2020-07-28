package wta.architecture.chapter2.person;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = PersonModule.class)
public interface PersonComponent {

    PersonA getPersonA(); // 프로비전 메서드

    void inject(PersonB personB); // 멤버-인젝션 메서드

    // 컴포넌트 객체 만들기 - 컴포넌트 빌더
    @Component.Builder
    interface Builder {
        /**
         * setter method
         * 빌드 메서드를 제외한 나머지
         * @Component 애노테이션에 modules, dependencies로 선언된 요소들은 세터 메서드로 선언
         * 반드시 하나의 매개변수만, 반환형으로는 void, 빌더 또는 빌더의 슈퍼 타입
         * @BindInstance를 붙이면, 해당 컴포넌트에 인스턴스를 넘겨 바인드시킨다.
         */
        Builder setPersonModule(PersonModule personModule);

        /**
         * build method
         * 매개변수를 갖지 않고, 컴포넌트 타입 또는 컴포넌트의 슈퍼 타입을 반환하는 추상메서드
         */
        PersonComponent build();
    }

    // 컴포넌트 객체 만들기 - 컴포넌트 팩토리
    @Component.Factory
    interface Factory {
        /**
         * 팩토리 메서드
         * 컴포넌트 타입 또는 컴포넌트의 슈퍼 타입을 반환하는 하나의 추상 메서드만 존재
         * @Component 애노테이션에 modules, dependencies로 지정된 속성들을 반드시 매개변수로 가져야
         * 메서드에 @BindInstance 애노테이션이 붙은 매개변수는 해당 컴포넌트에 인스턴스를 넘겨 바인드
         */
        PersonComponent newPersonComponent(
                PersonModule pm,
                @BindsInstance PersonA personA
        );
    }
}
