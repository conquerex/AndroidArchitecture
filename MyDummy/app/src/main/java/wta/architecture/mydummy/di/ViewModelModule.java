package wta.architecture.mydummy.di;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

// ViewModel과 관련된 내용을 오브젝트 그래프로 관리
@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}
