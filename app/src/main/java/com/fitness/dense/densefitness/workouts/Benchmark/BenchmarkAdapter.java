package com.fitness.dense.densefitness.workouts.Benchmark;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.Information;

import java.util.ArrayList;

//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperAdapter;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.OnStartDragListener;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class BenchmarkAdapter extends RecyclerView.Adapter<BenchmarkViewHolder> implements View.OnClickListener{

    private LayoutInflater inflater;
    private Context context;

    ArrayList<Properties> properties;

    private SparseBooleanArray selectedItems;
    private BenchmarkViewHolder holder;


    public BenchmarkAdapter(Context context, ArrayList<Properties> properties){
        selectedItems = new SparseBooleanArray();
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.properties = properties;
    }

    @Override
    public BenchmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.benchmark_row, parent, false);
        holder = new BenchmarkViewHolder(view, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BenchmarkViewHolder holder, int position) {
        Properties current = properties.get(position);
        holder.benchmarkTitle.setText(current.workoutTitle);
        holder.benchmarkDescription.setText(current.workoutDescription);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    @Override
    public void onClick(View v) {

    }
}
