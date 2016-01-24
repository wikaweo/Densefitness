package com.fitness.dense.densefitness.workouts.Benchmark;

import android.content.Context;
import android.content.Intent;
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
public class BenchmarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    public final TextView benchmarkTitle;
    public final TextView benchmarkDescription;

    public BenchmarkViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        benchmarkTitle = (TextView) itemView.findViewById(R.id.benchmarkTitle);
        benchmarkDescription = (TextView) itemView.findViewById(R.id.benchmarkDescription);

        //workoutTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, WorkoutDetails.class);
        //Bundle bundle = new Bundle();
        context.startActivity(intent);
    }
}
