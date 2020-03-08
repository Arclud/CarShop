package com.example.yourcar.ui.activity.splashScreen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yourcar.R;
import com.example.yourcar.ui.activity.login.LoginActivity;
import com.example.yourcar.ui.activity.main.MainActivity;
import com.example.yourcar.util.SharedPreferencesManager;

/**
 * This if first activity who seen user
 */
public class SplashScreen  extends AppCompatActivity {

    private  ActivityOptions options;

    // library for animation
    private LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lottieAnimationView = findViewById(R.id.lottie);


        // this func is used for animation

        if(Build.VERSION.SDK_INT>20) {
             options =
                    ActivityOptions.makeSceneTransitionAnimation(this);
        }
        runAnimation();
    }

    public void runAnimation(){


        //timer for animation
        new Handler().postDelayed(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {



                // stop animation
                lottieAnimationView.cancelAnimation();


                // check user is logged or not
                if (!(SharedPreferencesManager.getInstance(getApplicationContext()).checkLogin())){
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    // open new activity
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent,options.toBundle());
                }

                finish();
            }
        },1300);
    }
}
