package com.fitness.dense.densefitness.exercises;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperViewHolder;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class MusclesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, ItemTouchHelperViewHolder {

    private Context context;

    public final TextView muscleTitle;
    public final ImageView muscleImage;

    public MusclesViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        muscleTitle = (TextView) itemView.findViewById(R.id.exerciseText);
        muscleImage = (ImageView) itemView.findViewById(R.id.exerciseImg);
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
        Toast.makeText(muscleTitle.getContext(), " Item clicked at " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
