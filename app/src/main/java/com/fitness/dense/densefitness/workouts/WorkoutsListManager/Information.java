package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class Information {
    public int iconId;
    public int workoutId;
    public boolean isChecked = false;
    public String title;
    public String description;
    public String date;

    public void setId(int id)
    {
        this.workoutId = id;
    }

    public int getId()
    {
        return workoutId;
    }

    public void setIsChecked(boolean checked)
    {
        this.isChecked = checked;
    }

    public boolean getIsChecked()
    {
        return isChecked;
    }

    public void setTitle(String workout)
    {
        this.title = workout;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
