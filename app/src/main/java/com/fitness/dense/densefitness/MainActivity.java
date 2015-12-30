package com.fitness.dense.densefitness;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fitness.dense.densefitness.bodymass.BodyMassFragment;
import com.fitness.dense.densefitness.exercises.ExercisesFragment;
import com.fitness.dense.densefitness.exercises.muscles.MusclesFragment;
import com.fitness.dense.densefitness.tabs.SlidingTabLayout;
import com.fitness.dense.densefitness.workouts.WorkoutsFragment;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity{

    static final int NUM_ITEMS = 3;

    //private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new Vector<Fragment>();

        //for each fragment you want to add to the pager
        fragments.add(Fragment.instantiate(this, WorkoutsFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, MusclesFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, BodyMassFragment.class.getName()));

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterControl(this, getSupportFragmentManager(), fragments));
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

    public void switchContent(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);

        ExercisesFragment exercisesFragment = new ExercisesFragment();
        exercisesFragment.setArguments(args);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, exercisesFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*try
        {
            List<Fragment> fragments = new Vector<Fragment>();

            fragments.add(Fragment.instantiate(this, ExercisesFragment.class.getName()));

            //mPager = (ViewPager) findViewById(R.id.pager);
            mPager.setAdapter(new PagerAdapterControl(this, getSupportFragmentManager(), fragments));
        }
        catch (Exception ex)
        {

        }*/
    }

}


