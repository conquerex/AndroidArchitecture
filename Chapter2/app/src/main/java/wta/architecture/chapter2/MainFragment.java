package wta.architecture.chapter2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

//public class MainFragment extends Fragment {
public class MainFragment extends DaggerFragment {

//    @Inject
//    SharedPreferences sharedPreferences;
//
//    @Inject
//    String activityName;
//
//    @Inject
//    Integer randomNum;

    @Inject
    @Named("app")
    String appString;

    @Inject
    @Named("activity")
    String activityString;

    @Inject
    @Named("fragment")
    String fragmentString;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        Log.e("MainFragment", appString);
        Log.e("MainFragment", activityString);
        Log.e("MainFragment", fragmentString);
        super.onAttach(context);
//        if (getActivity() instanceof MainActivity) {
//            ((MainActivity) getActivity()).getComponent()
//                    .mainFragmentBuilder()
//                    .setModule(new MainFragmentModule())
//                    .setFragment(this)
//                    .build().inject(this);
//        }

//        Log.d("MainFragment", activityName);
//        Log.d("MainFragment", "num1 :::: " + randomNum);
//        Log.d("MainFragment", "num2 :::: " + randomNum);
    }
}
