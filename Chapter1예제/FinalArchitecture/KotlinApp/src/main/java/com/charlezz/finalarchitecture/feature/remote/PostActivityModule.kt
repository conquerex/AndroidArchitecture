package com.charlezz.finalarchitecture.feature.remote

import androidx.databinding.DataBindingUtil
import com.charlezz.finalarchitecture.R
import com.charlezz.finalarchitecture.databinding.ActivityPostBinding
import com.charlezz.finalarchitecture.di.ActivityScope
import com.charlezz.finalarchitecture.di.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class PostActivityModule{
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideActivityPostBinding(activity: PostActivity): ActivityPostBinding =
                DataBindingUtil.setContentView(activity, R.layout.activity_post)
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [(PostFragmentModule::class)])
    abstract fun getPostFragment(): PostFragment

}