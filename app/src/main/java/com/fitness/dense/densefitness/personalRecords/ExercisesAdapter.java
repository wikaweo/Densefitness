package com.fitness.dense.densefitness.personalRecords;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.R;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-12-30.
 */
public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    ArrayList<ExercisesInformation> exercisesInformation;

    public ExercisesAdapter(Context context, ArrayList<ExercisesInformation> exercisesInformation){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.exercisesInformation = exercisesInformation;
    }

    @Override
    public ExercisesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.exercises_row, parent, false);
        ExercisesViewHolder exercisesViewHolder = new ExercisesViewHolder(view, context);
        return exercisesViewHolder;
    }

    @Override
    public void onBindViewHolder(ExercisesViewHolder holder, int position) {
        ExercisesInformation current = exercisesInformation.get(position);
        holder.exerciseTitle.setText(current.title);
    }

    @Override
    public int getItemCount() { return exercisesInformation.size(); }
}
