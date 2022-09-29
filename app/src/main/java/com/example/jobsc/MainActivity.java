package com.example.jobsc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStartScheduler = findViewById(R.id.btn_start_scheduler);
        Button btnCancelScheduler = findViewById(R.id.btn_cancel_scheduler);
        btnStartScheduler.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Job started",Toast.LENGTH_SHORT).show();

                onClickStartScheduler();
            }



        });
        btnCancelScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCancelScheduler();            }


        });
    }
    private void onClickStartScheduler(){
        ComponentName componentName=new ComponentName(this,MyJobService.class);
        JobInfo jobInfo= new JobInfo.Builder(JOB_ID,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }
    private void onClickCancelScheduler(){
        Toast.makeText(MainActivity.this, "Job cancelled",Toast.LENGTH_SHORT).show();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOB_ID);
    }
}