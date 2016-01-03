package com.fitness.dense.densefitness.personalRecords.muscles;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class PersonalRecordsRow {
    public int iconId;
    public String title;

    public void setTitle(String exercise)
    {
        this.title = exercise;
    }

    public void setImage(int exerciseImage)
    {
        this.iconId = exerciseImage;
    }
}
