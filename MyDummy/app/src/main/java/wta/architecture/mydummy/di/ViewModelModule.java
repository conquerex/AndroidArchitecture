package wta.architecture.mydummy.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import wta.architecture.mydummy.ui.post.PostViewModel;

// ViewModel과 관련된 내용을 오브젝트 그래프로 관리
@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);

//    @Binds
//    @IntoMap
//    @ViewModelKey(PostViewModel.class)
//    abstract ViewModel bindPostViewModel(PostViewModel viewModel);
}
