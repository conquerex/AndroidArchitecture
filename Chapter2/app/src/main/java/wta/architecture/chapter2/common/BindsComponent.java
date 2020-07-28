package wta.architecture.chapter2.common;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @BindsInstance
 * 아래와 같이 modules 정보가 필요없다.
 * @Component(modules = {CommonModule.class})
 */
@Component
public interface BindsComponent {
    void inject(Bar bar);

    @Component.Builder
    interface Builder {
        @BindsInstance
        /**
         * 세터메서드이지만 modules 정보가 없으므로, 원하는 객체(String)가 바인드 될 수 있다.
         * 이 세터 메서드에 외부로부터 생성한 String 객체를 바인드 할 것이다.
         */
        Builder setString(String str);
        BindsComponent build();
    }
}
