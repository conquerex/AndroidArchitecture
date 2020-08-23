package wta.architecture.mydummy.ui;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import wta.architecture.mydummy.R;
import wta.architecture.mydummy.databinding.ActivityMainBinding;
import wta.architecture.mydummy.di.ActivityContext;
import wta.architecture.mydummy.di.ActivityScope;
import wta.architecture.mydummy.di.FragmentScope;
import wta.architecture.mydummy.ui.post.PostFragment;
import wta.architecture.mydummy.ui.post.PostModule;

@Module
public abstract class MainModule {
    // MS : 의존성을 제공
    @Provides
    @ActivityScope
    static ActivityMainBinding provideBinding(MainActivity activity) {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }

    @Provides
    @ActivityContext
    static Context provideContext(MainActivity activity) {
        return activity;
    }

    /**
     * 서브 컴포넌트 정의
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = PostModule.class)
    abstract PostFragment getPostFragment();
}

