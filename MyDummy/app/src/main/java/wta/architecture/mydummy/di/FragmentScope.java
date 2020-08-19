package wta.architecture.mydummy.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

// 프래그먼트 범위 지정자
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
    //
}
