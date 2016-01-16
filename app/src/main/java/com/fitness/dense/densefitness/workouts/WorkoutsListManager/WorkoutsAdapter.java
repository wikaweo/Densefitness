package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.R;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperAdapter;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    ArrayList<Information> data; //= Collections.emptyList()
    private SparseBooleanArray selectedItems;


    public WorkoutsAdapter(Context context, ArrayList<Information> data){
        selectedItems = new SparseBooleanArray();
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public WorkoutsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.workouts_row, parent, false);
        WorkoutsViewHolder holder = new WorkoutsViewHolder(view, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(final WorkoutsViewHolder holder, int position) {
        Information current = data.get(position);
        holder.workoutTitle.setText(current.title);
        holder.workoutDescription.setText(current.description);
        holder.workoutDate.setText(current.date);
        //holder.icon.setImageResource(current.iconId);
        // Start a drag whenever the handle view it touched
        /*holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
