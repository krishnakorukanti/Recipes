package com.crish.recipes.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.crish.recipes.Fragments.FragmentOne;
import com.crish.recipes.Fragments.FragmentTwo;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new FragmentOne();
            case 1 : return new FragmentTwo();
        }
        return new FragmentOne();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
