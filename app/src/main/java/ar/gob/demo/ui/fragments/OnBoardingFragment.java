package ar.gob.demo.ui.fragments;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ar.gob.demo.R;

public class OnBoardingFragment extends Fragment {

    private View view;
    private int pos;
    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvSubTitle;
    private boolean notFirstStart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle s) {
        view = inflater.inflate(R.layout.layout_onboarding, container, false);

        String[] titles = getResources().getStringArray(R.array.string_array_title);
        String[] subTitles = getResources().getStringArray(R.array.string_array_subtitle);
        TypedArray images = getResources().obtainTypedArray(R.array.drawable_array_image);

        ivImage = (ImageView) view.findViewById(R.id.iv_ob_background);
        tvTitle = (TextView) view.findViewById(R.id.tv_ob_title);
        tvSubTitle = (TextView) view.findViewById(R.id.tv_ob_sub_title);

        Drawable drawable = images.getDrawable(pos);
        ivImage.setImageDrawable(drawable);
        tvTitle.setText(titles[pos]);
        tvSubTitle.setText(subTitles[pos]);

        return view;
    }

    public void setPosition(int pos) {
        this.pos = pos;
    }

    public void setBtVisibility(boolean bool) {
        this.notFirstStart = bool;
    }

}