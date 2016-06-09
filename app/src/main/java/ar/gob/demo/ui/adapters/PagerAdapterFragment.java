package ar.gob.demo.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import ar.gob.demo.ui.fragments.Fragment1;
import ar.gob.demo.ui.fragments.Fragment2;
import ar.gob.demo.ui.fragments.Fragment3;

public class PagerAdapterFragment extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};

    public PagerAdapterFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return Fragment1.newInstance(position + 1);

            case 1: // Fragment # 0 - This will show FirstFragment different title
                return Fragment2.newInstance();

            case 2: // Fragment # 1 - This will show SecondFragment
                return Fragment3.newInstance();

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}