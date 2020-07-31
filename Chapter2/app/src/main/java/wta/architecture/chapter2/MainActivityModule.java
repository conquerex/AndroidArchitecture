package wta.architecture.chapter2;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

//@Module(subcomponents = MainFragmentComponent.class)
//@Module(subcomponents = MainFragmentSubcomponent.class)
@Module
public abstract class MainActivityModule {

    @Named("activity")
    @Provides
    @ActivityScope
    static String provideString() {
        return ".......String from MainActivityModule";
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainFragment mainFragment();

//    // MainFragment를 위한 인젝터 팩토리를 멀티 바인딩
//    @Binds
//    @IntoMap
//    @ClassKey(MainFragment.class)
//    abstract AndroidInjector.Factory<?> bindInjectorFactory(MainFragmentSubcomponent.Factory factory);

    /**
     * Module class가 abstract일 경우, @Provides 메서드를 static으로 선언하지 않은 경우 아래와 같은 에러가 나타난다.
     * error: wta.architecture.chapter2.MainActivityModule is abstract and has instance @Provides methods. Consider making the methods static or including a non-abstract subclass of the module instead.
     */
    @Provides
    @ActivityScope
    static String provideActivityName() {
        return MainActivity.class.getSimpleName();
    }
}
