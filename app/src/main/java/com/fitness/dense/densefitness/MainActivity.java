package com.fitness.dense.densefitness;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fitness.dense.densefitness.Interfaces.NewWorkoutListener;
import com.fitness.dense.densefitness.Interfaces.PersonalRecordListener;
import com.fitness.dense.densefitness.bodymass.BodyMassFragment;
import com.fitness.dense.densefitness.personalRecords.personalRecords.PersonalRecords;
import com.fitness.dense.densefitness.tabs.SlidingTabLayout;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.WorkoutsFragment;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final int NUM_ITEMS = 3;

    private static final String TAG_NEW_WORKOUT = "newWorkout";
    private static final String TAG_EXISTING_WORKOUT = "existingWorkout";
    private static final String TAG_HERO_WORKOUT = "heroWorkout";

    //private Toolbar toolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private PagerAdapterControl mPagerAdapterControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new Vector<Fragment>();

        //for each fragment you want to add to the pager
        fragments.add(Fragment.instantiate(this, WorkoutsFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, PersonalRecords.class.getName()));
        fragments.add(Fragment.instantiate(this, BodyMassFragment.class.getName()));

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapterControl = new PagerAdapterControl(this, getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapterControl);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);

        //mTabs.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
        //mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.tabsScrollColor));

        mTabs.setViewPager(mPager);

        ImageView imageview = new ImageView(this);
        imageview.setImageResource(R.drawable.ic_plus);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageview)
                .build();

        ImageView iconHeroWorkouts = new ImageView(this);
        iconHeroWorkouts.setImageResource(R.drawable.ic_star);

        ImageView iconExistingWorkout = new ImageView(this);
        iconExistingWorkout.setImageResource(R.drawable.ic_plus_box);

        ImageView iconNewWorkout = new ImageView(this);
        iconNewWorkout.setImageResource(R.drawable.ic_plus_circle);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        SubActionButton btnHeroWorkouts = itemBuilder.setContentView(iconHeroWorkouts).build();
        SubActionButton btnExistingWorkout = itemBuilder.setContentView(iconExistingWorkout).build();
        SubActionButton btnNewWorkout = itemBuilder.setContentView(iconNewWorkout).build();

        btnHeroWorkouts.setTag(TAG_HERO_WORKOUT);
        btnExistingWorkout.setTag(TAG_EXISTING_WORKOUT);
        btnNewWorkout.setTag(TAG_NEW_WORKOUT);

        btnHeroWorkouts.setOnClickListener(this);
        btnExistingWorkout.setOnClickListener(this);
        btnNewWorkout.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btnHeroWorkouts)
                .addSubActionView(btnExistingWorkout)
                .addSubActionView(btnNewWorkout)
                .attachTo(actionButton)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                Fragment fragment = (Fragment) mPagerAdapterControl.instantiateItem(mPager, mPager.getCurrentItem());

                if(fragment instanceof PersonalRecordListener)
                    ((PersonalRecordListener) fragment).onNewPersonalRecordClick();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {

            }
        });
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

    @Override
    public void onClick(View v) {
        if(v.getTag().equals(TAG_HERO_WORKOUT)){
            Fragment fragment = (Fragment) mPagerAdapterControl.instantiateItem(mPager, mPager.getCurrentItem());

            if(fragment instanceof NewWorkoutListener)
                ((NewWorkoutListener) fragment).onHeroWorkoutClick();
        }
        if(v.getTag().equals(TAG_EXISTING_WORKOUT)){
            Toast.makeText(this, "existing workout",
                    Toast.LENGTH_SHORT).show();
        }
        if(v.getTag().equals(TAG_NEW_WORKOUT)){
            Fragment fragment = (Fragment) mPagerAdapterControl.instantiateItem(mPager, mPager.getCurrentItem());

            if(fragment instanceof NewWorkoutListener)
                ((NewWorkoutListener) fragment).onNewWorkoutClick();
        }
    }
}


