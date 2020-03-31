package com.example.chatbotforcorona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;
    int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_layout);

        progressBar = findViewById(R.id.progress_bar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    doWork();
                    startApp();
            }
        });
        thread.start();
    }

    public void doWork(){

        for (p=20;p<=100;p=p+20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(p);
            }
            catch (Exception e){

            }
        }
    }

    public void startApp(){
        Intent intent = new Intent(SplashScreen.this,loginAndSignup.class);
        startActivity(intent);
        finish();
    }
}
