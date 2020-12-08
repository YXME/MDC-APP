package com.example.mangadigitalcollection;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mangadigitalcollection.ui.LoginFragment;
import com.example.mangadigitalcollection.ui.NewFragment;
import com.example.mangadigitalcollection.ui.RecommendFragment;
import com.example.mangadigitalcollection.ui.RegisterFragment;

import org.jetbrains.annotations.NotNull;

public class PagerAdapter extends FragmentPagerAdapter {

    private final int numOfTabs;
    private final String context;

    public PagerAdapter(FragmentManager fm, int numOfTabs, String context) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.context = context;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        if(this.context.equals("login"))
        {
            switch(position) {
                case 0:
                    return new LoginFragment();
                case 1:
                    return new RegisterFragment();
            }
        }
        else if(this.context.equals("main")){
            switch(position) {
                case 0:
                    return new NewFragment();
                case 1:
                    return new RecommendFragment();
            }

        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
