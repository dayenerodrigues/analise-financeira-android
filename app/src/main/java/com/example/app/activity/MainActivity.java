package com.example.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.app.R;
import com.example.app.adapter.ViewPagerAdapter;
import com.example.app.db.DB;
import com.example.app.fragment.fragment_form;
import com.example.app.fragment.fragment_list;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new fragment_form(), "CADASTRO");
        adapter.addFragment(new fragment_list(), "LISTA");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        DB.INSTANCE.setContext(getBaseContext());
    }

}