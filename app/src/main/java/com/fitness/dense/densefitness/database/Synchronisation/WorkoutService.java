package com.fitness.dense.densefitness.database.Synchronisation;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class WorkoutService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        new AsyncWorkouts(this).execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
