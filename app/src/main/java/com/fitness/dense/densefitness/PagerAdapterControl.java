package com.fitness.dense.densefitness;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Fredrik on 2015-12-30.
 */
public class PagerAdapterControl extends FragmentStatePagerAdapter {

    private MainActivity mainActivity;
    public static int pos = 0;

    private List<Fragment> myFragments;

    public PagerAdapterControl(MainActivity mainActivity, FragmentManager fm, List<Fragment> myFrags) {
        super(fm);
        this.mainActivity = mainActivity;
        myFragments = myFrags;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
        //return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        setPos(position);

        String PageTitle = "";

        switch(pos)
        {
            case 0:
                PageTitle = "Workouts";
                break;
            case 1:
                PageTitle = "Exercises";
                break;
            case 2:
                PageTitle = "Body mass";
                break;
            case 3:
                PageTitle = "Exercise";
                break;
        }
        return PageTitle;
    }

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        PagerAdapterControl.pos = pos;
    }
}
