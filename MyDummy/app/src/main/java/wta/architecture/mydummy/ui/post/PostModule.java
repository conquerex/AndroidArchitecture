package wta.architecture.mydummy.ui.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import wta.architecture.mydummy.databinding.FragmentPostBinding;
import wta.architecture.mydummy.di.AppContext;
import wta.architecture.mydummy.di.FragmentScope;

/**
 * Created by jongkook on 2020.08.20
 */
@Module
public class PostModule {
    // 데이터 바인딩 클래스 제공
    @Provides
    @FragmentScope
    FragmentPostBinding provideBinding(@AppContext Context context) {
        return FragmentPostBinding.inflate(LayoutInflater.from(context), null, false);
    }

    // RecyclerView용 레이아웃 매니저
    @Provides
    @FragmentScope
    LinearLayoutManager provideLinearLayoutManager(@AppContext Context context) {
        return new LinearLayoutManager(context) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }
}
