package com.fitness.dense.densefitness.database.Synchronisation;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;

public class AsyncWorkouts extends AsyncTask<JobParameters, Void, JobParameters> {

    WorkoutService workoutService;

    public AsyncWorkouts(WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JobParameters doInBackground(JobParameters... params) {
        return params[0];
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPostExecute(JobParameters jobParameters) {
        workoutService.jobFinished(jobParameters, false);
    }
}

