package com.fitness.dense.densefitness.personalRecords.personalRecords;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness.dense.densefitness.MainActivity;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.personalRecords.ExercisesFragment;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class PersonalRecordsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    private Context context;
    private ExercisesFragment mExercisesFragment;
    private Bundle mBundle;

    public final TextView muscleTitle;
    public final ImageView muscleImage;

    public PersonalRecordsViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        muscleTitle = (TextView) itemView.findViewById(R.id.exercisePersonalRecordText);
        muscleImage = (ImageView) itemView.findViewById(R.id.personalRecordImg);
        muscleTitle.setOnClickListener(this);
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
