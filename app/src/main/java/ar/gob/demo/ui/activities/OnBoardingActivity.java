package ar.gob.demo.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ar.gob.demo.R;
import ar.gob.demo.helpers.Constants;
import ar.gob.demo.helpers.IntentHelper;
import ar.gob.demo.helpers.StorageHelper;
import ar.gob.demo.interfaces.InterfaceOnBoarding;
import ar.gob.demo.ui.adapters.OnBoardingAdapter;
import ar.gob.demo.ui.views.PageIndicator;

public class OnBoardingActivity extends BaseActivity implements InterfaceOnBoarding {

    private OnBoardingAdapter adapter;
    private PageIndicator mPageIndicator;
    private ViewPager mViewPager;
    private String[] titles;
    private Button btStart;
    private TextView tvSkip;
    private ImageView imObBack, imObNext;
    private boolean firstStart = false;
    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    decideAction();
                    break;

                case R.id.tv_skip:
                    decideAction();
                    break;

                case R.id.iv_preview:
                    if (mViewPager.getCurrentItem() != 0) {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
                    }
                    setVisibilityBtOb();
                    break;

                case R.id.iv_next:
                    if (mViewPager.getCurrentItem() == titles.length - 1) {
                        decideAction();
                    } else {
                        setVisibilityBtOb();
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPageIndicator = (PageIndicator) findViewById(R.id.indicator);
        btStart = (Button) findViewById(R.id.btn_start);
        tvSkip = (TextView) findViewById(R.id.tv_skip);
        imObBack = (ImageView) findViewById(R.id.iv_preview);
        imObNext = (ImageView) findViewById(R.id.iv_next);

        titles = getResources().getStringArray(R.array.string_array_title);

        if (!StorageHelper.getInstance().getBoolPreferences(Constants.SP_FIRST)) {
            StorageHelper.getInstance().putBoolPreferences(Constants.SP_FIRST, true);
            firstStart = true;
        } else {
            btStart.setVisibility(View.GONE);
        }

        adapter = new OnBoardingAdapter(getSupportFragmentManager(), firstStart, titles);
        adapter.delegate = this;

        mViewPager.setAdapter(adapter);
        mPageIndicator.setViewPager(mViewPager);
        mPageIndicator.onPageSelected(0);
        setVisibilityBtOb();

        btStart.setOnClickListener(btnOnClickListener);
        tvSkip.setOnClickListener(btnOnClickListener);
        imObBack.setOnClickListener(btnOnClickListener);
        imObNext.setOnClickListener(btnOnClickListener);
    }

    public void setVisibilityBtOb() {
        imObBack.setVisibility(mViewPager.getCurrentItem() == 0 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void pageChanged() {
        setVisibilityBtOb();
    }

    private void decideAction() {
        if (firstStart) {
            IntentHelper.goToHomeActivity(activity);
        } else {
            finish();
        }
    }

}
