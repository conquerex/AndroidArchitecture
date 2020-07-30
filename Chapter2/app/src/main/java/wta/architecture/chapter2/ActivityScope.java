package wta.architecture.chapter2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.SOURCE)
public @interface ActivityScope {
    // 애플리케이션과 범위를 구분짓도록 사용자 정의 한정자인 @ActivityScope를 사용
    // 이로써 MainActivityModule 내의 @ActivityScope를 가진 바인딩 메서드들은
    // 액티비티 생명 주기 동안 동일한 인스턴스 제공을 보장받을 수 있다.
}
