package ar.gob.demo.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ar.gob.demo.R;
import ar.gob.demo.helpers.Constants;
import ar.gob.demo.helpers.DeviceInfoHelper;
import ar.gob.demo.helpers.IntentHelper;

public class AboutActivity extends BaseActivity {

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_how_to:
                    IntentHelper.goToOnBoardingActivity(activity, false);
                    break;

                case R.id.btn_download:
                    IntentHelper.shareURL(AboutActivity.this, Constants.PLAY_STORE);
                    break;

            }
        }
    };
    private Toolbar toolbar;
    private TextView tvVersion;
    private Button btnHowTo;
    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        init();
        setContent();
    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvVersion = (TextView) findViewById(R.id.tv_base_version);
        btnHowTo = (Button) findViewById(R.id.btn_how_to);
        btnDownload = (Button) findViewById(R.id.btn_download);
    }

    public void setContent() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setTitle(getResources().getString(R.string.title_about_full));
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvVersion.setText(getResources().getString(R.string.version_base_app, DeviceInfoHelper.getVersionInfo()));
        btnHowTo.setOnClickListener(buttonClickListener);
        btnDownload.setOnClickListener(buttonClickListener);
    }
}
