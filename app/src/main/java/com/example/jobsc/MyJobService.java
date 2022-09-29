package com.example.jobsc;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends JobService {
    public static final String TAG = MyJobService.class.getName();
    private boolean jobCancelled;

    @Override
    public boolean onStartJob(JobParameters jobParameters){

        Log.e(TAG,"job started    ");
        doBackgroundWork(jobParameters);
        return true;
    }
    private void doBackgroundWork(JobParameters jobParameters){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<19;i++){
                    if(jobCancelled){
                        return;
                    }
                    Log.e(TAG,"run: "+ i);
                    try{
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.e(TAG,"job finished");
                jobFinished(jobParameters, false);
            }
        }).start();
    }
    @Override
    public boolean onStopJob(JobParameters jobParameters){
        Log.e(TAG,"job cancelled");
        jobCancelled= true;
        return true;
    }
}
