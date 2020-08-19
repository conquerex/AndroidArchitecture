package wta.architecture.mydummy.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import wta.architecture.mydummy.ui.MainActivity;
import wta.architecture.mydummy.ui.MainModule;

@Module
public abstract class ActivityModule {
    /*
        MainActivity를 위한 서브 컴포넌트를 정의한다.
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
