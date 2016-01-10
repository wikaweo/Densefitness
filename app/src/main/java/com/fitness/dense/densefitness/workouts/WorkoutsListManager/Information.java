package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

/**
 * Created by Fredrik on 2015-10-02.
 */
public class Information {
    public int iconId;
    public String title;
    public String description;
    public String date;

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
