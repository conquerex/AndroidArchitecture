package wta.architecture.chapter2;

import dagger.BindsInstance;
import dagger.Subcomponent;

//@FragmentScope
//@Subcomponent(modules = MainFragmentModule.class)
public interface MainFragmentComponent {

    void inject(MainFragment fragment);

//    @Subcomponent.Builder
    interface Builder {
        Builder setModule(MainFragmentModule module);

//        @BindsInstance
        Builder setFragment(MainFragment fragment);

        MainFragmentComponent build();
    }
}
