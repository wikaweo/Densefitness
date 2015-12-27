package com.fitness.dense.densefitness;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fitness.dense.densefitness.bodymass.BodyMassFragment;
import com.fitness.dense.densefitness.exercises.MusclesFragment;
import com.fitness.dense.densefitness.tabs.SlidingTabLayout;
import com.fitness.dense.densefitness.workouts.WorkoutsFragment;

public class MainActivity extends AppCompatActivity {

    static final int NUM_ITEMS = 3;

    //private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterControl(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);

        //mTabs.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
        //mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.tabsScrollColor));

        mTabs.setViewPager(mPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class PagerAdapterControl extends FragmentStatePagerAdapter {

        String[] tabs;

        public PagerAdapterControl(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    WorkoutsFragment fragment = new WorkoutsFragment();
                    /*Bundle args = new Bundle();
                    // Our object is just an integer
                    args.putInt(com.fitness.dense.densefitness.fragments.WorkoutsFragment.ARG_OBJECT, position + 1);
                    fragment.setArguments(args);*/
                    return fragment;
                case 1:
                    return new MusclesFragment();
                case 2:
                    return new BodyMassFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
            //return "OBJECT " + (position + 1);
        }
    }


}


