package com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper;

/**
 * Created by Fredrik on 2015-10-17.
 */
public interface  ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
