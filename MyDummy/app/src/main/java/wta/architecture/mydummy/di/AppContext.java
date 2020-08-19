package wta.architecture.mydummy.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

// 애플리케이션 Context 한정자
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppContext {
    //
}
