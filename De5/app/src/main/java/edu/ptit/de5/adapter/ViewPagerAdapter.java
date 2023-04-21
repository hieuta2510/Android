package edu.ptit.de5.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import edu.ptit.de5.fragment.FragmentDanhSach;
import edu.ptit.de5.fragment.FragmentSearch;
import edu.ptit.de5.fragment.FragmentThongTin;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new FragmentDanhSach();
            case 1:return new FragmentThongTin();
            case 2:return new FragmentSearch();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}