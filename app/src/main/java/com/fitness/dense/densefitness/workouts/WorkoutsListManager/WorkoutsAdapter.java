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
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.ItemTouchHelperAdapter;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> implements ItemTouchHelperAdapter {

    private LayoutInflater inflater;
    private Context context;
    ArrayList<Information> data; //= Collections.emptyList()
    private SparseBooleanArray selectedItems;
    private final OnStartDragListener mDragStartListener;


    public WorkoutsAdapter(Context context, ArrayList<Information> data, OnStartDragListener dragStartListener){
        mDragStartListener = dragStartListener;
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
        //holder.icon.setImageResource(current.iconId);
        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(data, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Indicates if the item at position position is selected
     * @param position Position of the item to check
     * @return true if the item is selected, false otherwise
     */
    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    /**
     * Toggle the selection status of the item at a given position
     * @param position Position of the item to toggle the selection status for
     */
    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    /**
     * Clear the selection status for all items
     */
    public void clearSelection() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    /**
     * Count the selected items
     * @return Selected items count
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    /**
     * Indicates the list of selected items
     * @return List of selected items ids
     */
    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
}
