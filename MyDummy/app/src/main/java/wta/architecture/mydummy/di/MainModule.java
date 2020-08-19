package wta.architecture.mydummy.di;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import wta.architecture.mydummy.R;
import wta.architecture.mydummy.databinding.ActivityMainBinding;
import wta.architecture.mydummy.ui.MainActivity;
import wta.architecture.mydummy.ui.post.PostFragment;
import wta.architecture.mydummy.ui.post.PostModule;

@Module
public abstract class MainModule {
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

//    @FragmentScope
//    @ContributesAndroidInjector(modules = PostModule.class)
//    abstract PostFragment getPostFragment();
}
