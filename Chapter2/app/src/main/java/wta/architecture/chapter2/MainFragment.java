package wta.architecture.chapter2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

public class MainFragment extends Fragment {
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    String activityName;

    @Inject
    Integer randomNum;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getComponent()
                    .mainFragmentBuilder()
                    .setModule(new MainFragmentModule())
                    .setFragment(this)
                    .build().inject(this);
        }

        Log.d("MainFragment", activityName);
        Log.d("MainFragment", "num1 :::: " + randomNum);
        Log.d("MainFragment", "num2 :::: " + randomNum);
    }
}
