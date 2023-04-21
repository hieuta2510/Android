package edu.ptit.de1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import edu.ptit.de1.adapter.ViewPagerAdapter;
import edu.ptit.de1.adapter.ViewPagerAdapter2;

public class MainActivity2 extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vPager;
    private ViewPagerAdapter2 pager;
    private FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tab = findViewById(R.id.tab22);
        vPager = findViewById(R.id.viewPager);
        pager = new ViewPagerAdapter2(getSupportFragmentManager(), 3);
        vPager.setAdapter(pager);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity2.this, AddActivity.class);
                startActivity(t);
            }
        });
        tab.setupWithViewPager(vPager);
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab t) {
                switch (t.getPosition()) {
                    case 0:
                        tab.setTabTextColors(Color.BLACK, getResources().getColor(R.color.teal_700));
                        break;
                    case 1:
                        tab.setTabTextColors(Color.BLACK, getResources().getColor(R.color.purple_700));
                        break;
                    case 2:
                        tab.setTabTextColors(Color.BLACK, getResources().getColor(R.color.purple_200));
                        break;
                }
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