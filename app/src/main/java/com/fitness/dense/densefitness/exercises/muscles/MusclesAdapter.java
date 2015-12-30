package com.fitness.dense.densefitness.exercises.muscles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperAdapter;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class MusclesAdapter extends RecyclerView.Adapter<MusclesViewHolder> implements ItemTouchHelperAdapter {

    private LayoutInflater inflater;
    private Context context;
    ArrayList<MuscleCell> muscleCells;

    public MusclesAdapter(Context context, ArrayList<MuscleCell> exerciseCells){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.muscleCells = exerciseCells;
    }

    @Override
    public MusclesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.muscles_cell, parent, false);
        MusclesViewHolder musclesViewHolder = new MusclesViewHolder(view, context);
        return musclesViewHolder;
    }

    @Override
    public void onBindViewHolder(MusclesViewHolder holder, int position) {
        MuscleCell current = muscleCells.get(position);
        holder.muscleTitle.setText(current.title);
        //holder.muscleImage.setImage(current.iconId);
    }

    @Override
    public int getItemCount() { return muscleCells.size(); }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        muscleCells.remove(position);
        notifyItemRemoved(position);
    }
}
