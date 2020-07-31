package wta.architecture.chapter2;

import dagger.BindsInstance;
import dagger.Subcomponent;

//@Subcomponent(modules = MainActivityModule.class)
//@ActivityScope
public interface MainActivityComponent {

    MainFragmentComponent.Builder mainFragmentBuilder();

    void inject(MainActivity activity);

//    @Subcomponent.Builder
    interface Builder {
        // @Component 애노테이션에 modules, dependencies로 선언된 요소들은 세터 메서드로 선언해야 한다.
        Builder setModule(MainActivityModule module);

        // 세터 메서드
        // 애플리케이션과 마찬가지로 액티비티 인스턴스 또한 시스템에 의해서 생성
        // 액티비티의 생명 주기 콜백 내에서 서브 컴포넌트 빌드 시 바인딩할 수 있도록
        // @BindsInstance 세터 메서드를 통해 액티비티 인스턴스를 바인딩한다
//        @BindsInstance
        Builder setActivity(MainActivity activity);
        // 빌드 메서드
        MainActivityComponent build();
    }
}
