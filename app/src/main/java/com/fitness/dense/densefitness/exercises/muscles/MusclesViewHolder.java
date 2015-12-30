package com.fitness.dense.densefitness.exercises.muscles;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dense.densefitness.MainActivity;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.exercises.ExercisesFragment;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperViewHolder;

import java.util.List;
import java.util.Vector;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class MusclesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, ItemTouchHelperViewHolder {

    private Context context;
    private ExercisesFragment mExercisesFragment;
    private Bundle mBundle;

    public final TextView muscleTitle;
    public final ImageView muscleImage;

    public MusclesViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        muscleTitle = (TextView) itemView.findViewById(R.id.muscleText);
        muscleImage = (ImageView) itemView.findViewById(R.id.muscleImg);
        muscleTitle.setOnClickListener(this);
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }

    @Override
    public void onClick(View v) {
        //((MainActivity)context).switchToNewFragment();
        //fragmentJump(getAdapterPosition());
        //Toast.makeText(muscleTitle.getContext(), " Item clicked at " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        switchContent(getAdapterPosition());
    }

    public void switchContent(int position) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.switchContent(position);
        }

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
