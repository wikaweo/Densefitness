package com.fitness.dense.densefitness.exercises;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitness.dense.densefitness.R;

/**
 * Created by Fredrik on 2015-09-20.
 */
public class ExercisesFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.exercises_fragment, container, false);
        /*Bundle args = getArguments();
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                Integer.toString(args.getInt(WorkoutsFragment.ARG_OBJECT)));*/
        return rootView;

    }
}
