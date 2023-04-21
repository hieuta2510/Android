package edu.ptit.de4.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import edu.ptit.de4.fragment.FragmentDanhSach;
import edu.ptit.de4.fragment.FragmentSearch;
import edu.ptit.de4.fragment.FragmentThongTin;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private int numPage;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numPage = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new FragmentDanhSach();
            case 1: return new FragmentThongTin();
            case 2: return new FragmentSearch();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return "Danh sach";
            case 1 : return "Thong tin";
            case 2 : return "Tim kiem";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return numPage;
    }
}