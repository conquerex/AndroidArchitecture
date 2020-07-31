package wta.architecture.chapter2;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * MainFragment에 멤버 인젝션을 하기 위한 MainFragmentSubcomponent
 * MainActivitySubcomponent의 서브 컴포넌트
 */
//@FragmentScope
//@Subcomponent(modules = MainFragmentModule.class)
public interface MainFragmentSubcomponent extends AndroidInjector<MainFragment> {

//    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MainFragment> {
        //
    }
}
