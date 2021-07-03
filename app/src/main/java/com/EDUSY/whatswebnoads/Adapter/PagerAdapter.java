package com.EDUSY.whatswebnoads.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.EDUSY.whatswebnoads.Fragment.ImageFragment;
import com.EDUSY.whatswebnoads.Fragment.VideoFragment;
public class PagerAdapter extends FragmentPagerAdapter {
    private ImageFragment imageFragment;
    private VideoFragment videofragment;
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        imageFragment=new ImageFragment();
        videofragment=new VideoFragment();
    }
    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {
            return imageFragment;
        }else {
            return videofragment; } }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0)
        {
            return "Images";
        }else {
            return "Videos";
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
}
