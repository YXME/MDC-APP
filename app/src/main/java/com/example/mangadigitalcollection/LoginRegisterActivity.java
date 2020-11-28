package com.example.mangadigitalcollection;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class LoginRegisterActivity extends AppCompatActivity {

    private ViewPager TabsContainer;
    private TabLayout TabSelector;
    private TabItem LoginTab;
    private TabItem RegisterTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        LoginTab = findViewById(R.id.LoginTab);
        RegisterTab = findViewById(R.id.RegisterTab);
        TabSelector = findViewById(R.id.TabSelector);
        TabsContainer = findViewById(R.id.TabsContainers);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), TabSelector.getTabCount());

        TabsContainer.setAdapter(pagerAdapter);

        TabSelector.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabsContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}