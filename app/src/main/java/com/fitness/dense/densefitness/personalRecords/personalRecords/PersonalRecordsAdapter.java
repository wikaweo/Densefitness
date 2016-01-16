package com.fitness.dense.densefitness.personalRecords.personalRecords;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.R;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class PersonalRecordsAdapter extends RecyclerView.Adapter<PersonalRecordsViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    ArrayList<PersonalRecordsRow> personalRecordsRows;

    public PersonalRecordsAdapter(Context context, ArrayList<PersonalRecordsRow> exerciseCells){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.personalRecordsRows = exerciseCells;
    }

    @Override
    public PersonalRecordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.personal_records_row, parent, false);
        PersonalRecordsViewHolder personalRecordsViewHolder = new PersonalRecordsViewHolder(view, context);
        return personalRecordsViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonalRecordsViewHolder holder, int position) {
        PersonalRecordsRow current = personalRecordsRows.get(position);
        holder.muscleTitle.setText(current.title);
        //holder.muscleImage.setImage(current.iconId);
    }

    @Override
    public int getItemCount() { return personalRecordsRows.size(); }
}
