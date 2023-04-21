package edu.ptit.de4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.ptit.de4.adapter.ViewPagerAdapter;
import android.annotation.SuppressLint;
import android.graphics.Color;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vPager;
    private ViewPagerAdapter pager;
    private FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = findViewById(R.id.tab);
        vPager = findViewById(R.id.viewPager);
        pager = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        vPager.setAdapter(pager);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, AddActivity.class);
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