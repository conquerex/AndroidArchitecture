package wta.architecture.mydummy.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import wta.architecture.mydummy.R;
import wta.architecture.mydummy.databinding.FragmentUserBinding;
import wta.architecture.mydummy.di.AppViewModelFactory;

/**
 * (p475, 사용자 정보 화면 구성하기)
 * onCreate에서는 UserViewModel을 초기화 하고, 사용자 아이디를 통해 사용자 정보를 불러온다.
 * onCreateView에서는 주입받은 FragmentUserBinding으로부터 rootView를 반환한다.
 * 마지막으로 onViewCreated에서는 바인딩 클래스에 LifecycleOwner와 UserViewModel을 제공한다.
 */
public class UserFragment extends DaggerFragment {

    @Inject
    AppViewModelFactory viewModelFactory;

    @Inject
    FragmentUserBinding binding;

    UserViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
        if (savedInstanceState == null) {
            UserFragmentArgs args = UserFragmentArgs.fromBundle(getArguments());
            viewModel.loadUser(args.getUserId());
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
        binding.setViewModel(viewModel);
    }
}