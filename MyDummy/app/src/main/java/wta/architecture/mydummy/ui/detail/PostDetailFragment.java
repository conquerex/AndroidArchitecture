package wta.architecture.mydummy.ui.detail;

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
import wta.architecture.mydummy.databinding.FragmentPostDetailBinding;
import wta.architecture.mydummy.di.AppViewModelFactory;
import wta.architecture.mydummy.ui.post.PostFragmentDirections;

/**
 * (p465, PostDetailFragment 구현하기)
 * 의존성을 주입할 PostDetailModule과 서버로부터 데이터를 불러와
 * PostDetailAdapter에 제공하는 PostDetailViewModel을 구현하여
 * PostDetailFragment를 완성한다.
 * .
 * (p466)
 * PostDetailFragment는 Post 객체가 있어야 초기화할 수 있는 프래그먼트다.
 * Navigation 컴포넌트는 목적지 간 이동시 Bundle을 안전하게 전달할 수 있는 Safe Args 플러그인을 제공한다.
 * 이를 이용하여 onCreate() 메서드에서 Post 객체를 전달받고 뷰 모델을 초기화할 수 있도록 한다.
 */
public class PostDetailFragment extends DaggerFragment {
    @Inject
    FragmentPostDetailBinding binding;
    @Inject
    PostDetailAdapter adapter;
    @Inject
    LinearLayoutManager manager;
    @Inject
    AppViewModelFactory viewModelFactory;
    @Inject
    Lazy<NavController> navController;

    PostDetailViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostDetailViewModel.class);
        if (savedInstanceState == null) {
            // Post 객체를 전달받는다.
            PostDetailFragmentArgs args = PostDetailFragmentArgs.fromBundle(getArguments());
            viewModel.load(args.getPost());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(manager);
        binding.setViewModel(viewModel);
        viewModel.getLiveItems().observe(getViewLifecycleOwner(), items -> adapter.setItems(items));
        // todo
//        viewModel.getUserClickEvent().observe(getViewLifecycleOwner(),
//                userId -> navController.get().navigate());
    }
}