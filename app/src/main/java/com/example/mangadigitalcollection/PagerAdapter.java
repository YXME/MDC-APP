package com.example.mangadigitalcollection;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mangadigitalcollection.ui.LoginFragment;
import com.example.mangadigitalcollection.ui.NewFragment;
import com.example.mangadigitalcollection.ui.RecommendFragment;
import com.example.mangadigitalcollection.ui.RegisterFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private String context;

    public PagerAdapter(FragmentManager fm, int numOfTabs, String context) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(this.context == "login")
        {
            switch(position) {
                case 0:
                    return new LoginFragment();
                case 1:
                    return new RegisterFragment();
                default:
                    return null;
            }
        }
        else if(this.context == "main"){
            switch(position) {
                case 0:
                    return new NewFragment();
                case 1:
                    return new RecommendFragment();
                default:
                    return null;
            }

        }

        else return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
