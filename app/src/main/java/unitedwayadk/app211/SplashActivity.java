package unitedwayadk.app211;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler mHandler = new Handler();

        final SplashActivity instance = this;

        mHandler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(instance, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

}
