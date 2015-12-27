package com.fitness.dense.densefitness.exercises;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class MuscleCell {
    public int iconId;
    public String title;

    public void setTitle(String workout)
    {
        this.title = workout;
    }

    public void setImage(int exerciseImage)
    {
        this.iconId = exerciseImage;
    }
}
