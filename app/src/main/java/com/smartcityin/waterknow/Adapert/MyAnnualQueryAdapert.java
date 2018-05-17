package com.smartcityin.waterknow.Adapert;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyAnnualQueryAdapert extends FragmentPagerAdapter{
    private ArrayList<String> mTitleList;
    private ArrayList<Fragment> mFragmentList;

    public MyAnnualQueryAdapert(FragmentManager fm, ArrayList<String> mTitleList, ArrayList<Fragment> mFragmentList) {
        super(fm);
        this.mTitleList = mTitleList;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
