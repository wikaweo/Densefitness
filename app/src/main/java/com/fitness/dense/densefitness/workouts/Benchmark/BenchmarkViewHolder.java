package com.fitness.dense.densefitness.workouts.Benchmark;

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
public class BenchmarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    public final TextView benchmarkTitle;
    public final TextView benchmarkDescription;

    public BenchmarkViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        benchmarkTitle = (TextView) itemView.findViewById(R.id.benchmarkTitle);
        benchmarkDescription = (TextView) itemView.findViewById(R.id.benchmarkDescription);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String[] heroWod = new String[2];
        heroWod[0] = String.valueOf(benchmarkTitle.getText());
        heroWod[1] = String.valueOf(benchmarkDescription.getText());

        Intent intent = new Intent(context, WorkoutDetails.class);
        Bundle bundle = new Bundle();
        bundle.putStringArray("heroWod", heroWod);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
