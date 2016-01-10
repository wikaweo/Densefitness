package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.Workout;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperViewHolder;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class WorkoutsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, ItemTouchHelperViewHolder {

    private Context context;

    public final TextView workoutTitle;
    public final TextView workoutDescription;
    public final TextView workoutDate;
    //public final ImageView  handleView;

    public WorkoutsViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        //itemView.setOnClickListener(this);
        //itemView.setOnLongClickListener(this);
        workoutTitle = (TextView) itemView.findViewById(R.id.workoutText);
        workoutDescription = (TextView) itemView.findViewById(R.id.workoutDescription);
        workoutDate = (TextView) itemView.findViewById(R.id.workoutDate);
        //icon = (ImageView) itemView.findViewById(R.id.workoutIcon);
        //handleView = (ImageView) itemView.findViewById(R.id.handle);
    }

    @Override
    public void onClick(View v) {
        context.startActivity(new Intent(context, Workout.class));
        Toast.makeText(workoutTitle.getContext(), " Item clicked at " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}
