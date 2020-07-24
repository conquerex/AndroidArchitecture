package com.charlezz.javaapp.feature;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

public class MainPageAdapter extends FragmentStatePagerAdapter {
    private Context context;
    public MainPageAdapter(Context context, FragmentManager fm) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return MainTabType.values()[position].getFragment();
    }

    @Override
    public int getCount() {
        return MainTabType.values().length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return MainTabType.values()[position].getTitle(context);
    }
}
