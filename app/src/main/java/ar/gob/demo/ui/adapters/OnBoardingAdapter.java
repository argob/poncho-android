package ar.gob.demo.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import ar.gob.demo.interfaces.InterfaceOnBoarding;
import ar.gob.demo.ui.fragments.OnBoardingFragment;

public class OnBoardingAdapter extends FragmentStatePagerAdapter {

    private boolean firstStart;
    private String[] titles;
    public InterfaceOnBoarding delegate;

    public OnBoardingAdapter(FragmentManager fm,  boolean firstStart, String[] titles) {
        super(fm);
        this.firstStart = firstStart;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        OnBoardingFragment ob1 = new OnBoardingFragment();
        ob1.setPosition(position);
        if (!firstStart) {
            ob1.setBtVisibility(true);
        }
        return ob1;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        this.delegate.pageChanged();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

}
