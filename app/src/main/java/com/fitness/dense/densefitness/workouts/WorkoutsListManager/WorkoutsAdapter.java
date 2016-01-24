package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fitness.dense.densefitness.Interfaces.WorkoutListener;
import com.fitness.dense.densefitness.R;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperAdapter;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.OnStartDragListener;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> implements View.OnClickListener{

    private LayoutInflater inflater;
    private Context context;

    ArrayList<Information> data; //= Collections.emptyList()
    ArrayList<String> checkedItems;

    private SparseBooleanArray selectedItems;
    private WorkoutListener workoutListener;
    private WorkoutsViewHolder holder;
    private CheckBox cbWorkout;


    public WorkoutsAdapter(Context context, ArrayList<Information> data, WorkoutListener workoutListenerActivity){
        workoutListener = workoutListenerActivity;
        selectedItems = new SparseBooleanArray();
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        checkedItems = new ArrayList<>();
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public WorkoutsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.workouts_row, parent, false);
        cbWorkout = (CheckBox) view.findViewById(R.id.cbWorkout);
        holder = new WorkoutsViewHolder(view, context, workoutListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(final WorkoutsViewHolder holder, int position) {
        Information current = data.get(position);
        holder.workoutTitle.setText(current.title);
        holder.workoutDescription.setText(current.description);
        holder.workoutDate.setText(current.date);
        holder.cbWorkout.setTag(current.workoutId);

        cbWorkout.setOnClickListener(this);
    }

    public ArrayList<String> getCheckedItems() {
        return checkedItems;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        CheckBox checkBox = (CheckBox)view;
        //checkBox.setChecked(checkBox.isChecked());
        if(checkBox.isChecked()) {
            checkedItems.add(checkBox.getTag().toString());
            workoutListener.onCheckedChanged();
        }
        else {
            checkedItems.remove(checkBox.getTag().toString());
        }

        if(checkedItems.isEmpty())
            workoutListener.removeTrashCan();
    }
}
