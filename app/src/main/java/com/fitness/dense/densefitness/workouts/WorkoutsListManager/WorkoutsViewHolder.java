package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fitness.dense.densefitness.Interfaces.WorkoutListener;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.workouts.WorkoutDetails;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class WorkoutsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    public final TextView workoutTitle;
    public final TextView workoutDescription;
    public final TextView workoutDate;
    public CheckBox cbWorkout;
    private WorkoutListener workoutListener;
    //public final ImageView  handleView;

    public WorkoutsViewHolder(View itemView, Context context, WorkoutListener workoutListenerActivity) {
        super(itemView);
        this.context = context;
        workoutListener = workoutListenerActivity;
        workoutTitle = (TextView) itemView.findViewById(R.id.workoutText);
        workoutDescription = (TextView) itemView.findViewById(R.id.workoutDescription);
        workoutDate = (TextView) itemView.findViewById(R.id.workoutDate);
        cbWorkout = (CheckBox) itemView.findViewById(R.id.cbWorkout);

        //workoutTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, WorkoutDetails.class);
        //Bundle bundle = new Bundle();
        context.startActivity(intent);
    }
}
