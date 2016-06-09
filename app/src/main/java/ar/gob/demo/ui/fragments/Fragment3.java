package ar.gob.demo.ui.fragments;

import android.os.Bundle;
import android.view.View;

import ar.gob.demo.R;

public class Fragment3 extends BaseFragment {

    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setContent();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragmento_tres;
    }

    public void init(View view) {
    }

    public void setContent() {
    }


}