package wta.architecture.mydummy.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import wta.architecture.mydummy.ui.detail.PostDetailViewModel;
import wta.architecture.mydummy.ui.post.PostViewModel;

// ViewModel과 관련된 내용을 오브젝트 그래프로 관리
@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);

    /*
        PostViewModel 작성 후...
        PostViewModel까지 만들었다면 오브젝트 그래프에 멀티 바인딩하도록 ViewModelModule에 다음과 같은 내용을 추가한다.
     */
    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    abstract ViewModel bindsPostViewModel(PostViewModel viewModel);

    /*
        (p473)
        ViewModel의 서브 클래스인 PostDetailViewModel을 추가하여
        ViewModelModule에도 해당 클래스가 멀티 바인딩될 수 있도록 한다.
     */
    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel.class)
    abstract ViewModel bindsPostDetailViewModel(PostDetailViewModel viewModel);
}
