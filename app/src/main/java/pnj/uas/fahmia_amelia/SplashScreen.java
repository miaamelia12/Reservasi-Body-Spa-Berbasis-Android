package pnj.uas.fahmia_amelia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import pnj.uas.fahmia_amelia.activity.MainActivity;
import pnj.uas.fahmia_amelia.activity.SignInActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image);
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreen.this, SignInActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
