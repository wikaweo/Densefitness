package com.fitness.dense.densefitness.personalRecords;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dense.densefitness.R;

/**
 * Created by Fredrik on 2015-12-30.
 */
public class ExercisesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private Context context;

    public final TextView exerciseTitle;
    public final ImageView exerciseImage;

    public ExercisesViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        exerciseTitle = (TextView) itemView.findViewById(R.id.exerciseText);
        exerciseImage = (ImageView) itemView.findViewById(R.id.exerciseImg);
        exerciseTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(exerciseTitle.getContext(), " Item clicked at " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        String exercise = String.valueOf(exerciseTitle.getText());

        Intent intent = new Intent(context, PersonalRecordsDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("exercise", exercise);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
