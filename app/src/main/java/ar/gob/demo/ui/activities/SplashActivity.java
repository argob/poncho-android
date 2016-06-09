package ar.gob.demo.ui.activities;

import android.os.Bundle;
import android.os.Handler;

import ar.gob.demo.R;
import ar.gob.demo.helpers.Constants;
import ar.gob.demo.helpers.IntentHelper;
import ar.gob.demo.helpers.StorageHelper;

public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!StorageHelper.getInstance().getBoolPreferences(Constants.SP_FIRST)) {
                    IntentHelper.goToOnBoardingActivity(activity, true);
                } else {
                    IntentHelper.goToHomeActivity(activity);
                }
            }
        }, 3000);
    }

}
