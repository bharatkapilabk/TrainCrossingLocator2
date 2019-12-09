package com.example.traincrossinglocator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class splash extends AppCompatActivity {
    private static int Splash_Time_Out = 2000;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.hide();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, LoginOrCreate.class);
                startActivity(intent);
                finish();
            }
        }, Splash_Time_Out);
    }
}