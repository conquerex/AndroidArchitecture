package wta.architecture.mydummy.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerFragment;
import wta.architecture.mydummy.databinding.FragmentPostBinding;
import wta.architecture.mydummy.di.AppViewModelFactory;

/*
    PostFragment는 DaggerFragment를 상속하여 멤버 인젝션을 구현하고,
    서버에 게시글 데이터를 요청하고 응답을 받아 RecyclerView로 글 목록을 표현한다.
    .
    (p438)
    게시 글 화면 구성하기
    멤버 인젝션을 위해 DaggerFragment 상속
 */
public class PostFragment extends DaggerFragment {
    /**
     * 오브젝트 그래프로부터 멤버 인젝션
     */
    @Inject
    FragmentPostBinding binding;

    @Inject
    AppViewModelFactory viewModelFactory;
    @Inject
    PostAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    Lazy<NavController> navController;

    PostViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewModel 객체를 요청
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostViewModel.class);
        if (savedInstanceState == null) {
            // 데이터 요청, 프래그먼트가 재생성되었을 때는 요청하지 않는다.
            viewModel.loadPosts();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Lifecycle Owner 등록
        binding.setLifecycleOwner(getViewLifecycleOwner());
        // RecyclerView Adapter 지정
        binding.recyclerView.setAdapter(adapter);
        // RecyclerView 레이아웃 매니저 지정
        binding.recyclerView.setLayoutManager(layoutManager);
        // 바인딩 클래스에 ViewModel 연결
        binding.setViewModel(viewModel);
        // ViewModel이 가진 게시 글 목록을 구독하여 Adapter에 반영
        viewModel.getLivePosts()
                .observe(getViewLifecycleOwner(), list -> adapter.setItems(list));
        // 게시글이 클릭되었을 때 게시 글 상세 화면 목적지로 이동
//        viewModel.getPostClickEvent()
//                .observe(getViewLifecycleOwner(), postItem -> navController.get().navigate(...));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }
}