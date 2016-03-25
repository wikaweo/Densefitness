package com.fitness.dense.densefitness.personalRecords.personalRecords;

/**
 * Created by Fredrik on 2015-11-22.
 */
public class PersonalRecordsRow {
    public int iconId;
    public String exercise;
    public String date;
    public String recordResult;

    public void setExercise(String exercise)
    {
        this.exercise = exercise;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setRecordResult(String recordResult)
    {
        this.recordResult = recordResult;
    }

    public void setImage(int exerciseImage)
    {
        this.iconId = exerciseImage;
    }
}
