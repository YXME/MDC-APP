package com.example.mangadigitalcollection;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mangadigitalcollection.ui.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginRegisterActivity extends AppCompatActivity {

    private ViewPager TabsContainer;
    private TabLayout TabSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        TabSelector = findViewById(R.id.TabSelector);
        TabsContainer = findViewById(R.id.TabsContainers);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), TabSelector.getTabCount(), "login");

        TabsContainer.setAdapter(pagerAdapter);

        TabsContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabSelector.selectTab(TabSelector.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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