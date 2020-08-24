package wta.architecture.mydummy.ui.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import wta.architecture.mydummy.databinding.FragmentPostDetailBinding;
import wta.architecture.mydummy.di.AppContext;
import wta.architecture.mydummy.di.FragmentScope;

/**
 * Created by jongkook on 2020.08.25
 * .
 * PostDetail에서는 게시 글 상세 화면을 표현하기 위한 View를 표현하는 바인딩 클래스와
 * RecyclerView를 위한 LinearLayoutManager 그리고 화면 전환을 위한 NavController 객체를 제공한다.
 * PostDetailModule까지 정의했다면, PostDetailFragment에 멤버 인젝션을 위한 서브 컴포넌트 정의를 위해
 * MainModule에 getPostDetailFragment() 코드를 삽입하자
 */
@Module
public class PostDetailModule {

    @Provides
    @FragmentScope
    public FragmentPostDetailBinding provideBinding(@AppContext Context context) {
        return FragmentPostDetailBinding.inflate(LayoutInflater.from(context), null, false);
    }

    @Provides
    @FragmentScope
    public LinearLayoutManager provideLayoutManager(@AppContext Context context) {
        return new LinearLayoutManager(context) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            }
        };
    }

    @Provides
    @FragmentScope
    public NavController provideNavController(PostDetailFragment fragment) {
        return NavHostFragment.findNavController(fragment);
    }
}
