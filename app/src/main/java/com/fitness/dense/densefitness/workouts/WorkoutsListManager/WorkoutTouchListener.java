package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Fredrik on 2015-10-16.
 */
public class WorkoutTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    public WorkoutTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
        Log.d("VIVZ", "constructor invoked ");
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e){
                Log.d("VIVZ", "onSingleTapUp " + e);
                return  true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null && clickListener != null)
                {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                }
                Log.d("VIVZ", "onLongPress " + e);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if(child != null && clickListener != null && gestureDetector.onTouchEvent(e))
        {
            clickListener.onClick(child, recyclerView.getChildAdapterPosition(child));
        }
        Log.d("VIVZ", "onInterceptTouchEvent " + gestureDetector.onTouchEvent(e) + "" + e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.d("VIVZ", "onTouchEvent " + e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }
}


