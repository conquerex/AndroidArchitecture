package wta.architecture.mydummy.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

// 액티비티 범위 지정자
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
    //
}
