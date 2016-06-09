package ar.gob.demo.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import ar.gob.demo.R;

public class PageIndicator extends View implements ViewPager.OnPageChangeListener {

    public final static int UNSELECTED_COLOR = Color.parseColor("#D8D8D8");
    public final static int SELECTED_COLOR = Color.parseColor("#4A4A4A");
    private int mCount = 0;
    private int viewWidth = 0;
    private int actualWidth = 0;
    private int selectedPos = 0;
    private Paint selectedPaint;
    private Paint unselectedPaint;
    private int dotMargin = dip2px(getContext(), 16);
    private int radius = dip2px(getContext(), 3);
    private int paddingTop = dip2px(getContext(), 2);
    private int paddingBottom = dip2px(getContext(), 2);

    public PageIndicator(Context context) {
        this(context, null);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.PageIndicator, 0, 0);

        selectedPaint = new Paint();
        selectedPaint.setAntiAlias(true);
        selectedPaint.setColor(a.getColor(R.styleable.PageIndicator_selectedColor, SELECTED_COLOR));
        selectedPaint.setStyle(Paint.Style.FILL);

        unselectedPaint = new Paint(selectedPaint);
        unselectedPaint.setColor(a.getColor(R.styleable.PageIndicator_unselectedColor, UNSELECTED_COLOR));
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setViewPager(ViewPager view) {
        view.setOnPageChangeListener(this);
        mCount = view.getAdapter().getCount();
        actualWidth = dotMargin * (mCount - 1) + radius * 2 * mCount;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDots(canvas);
    }

    private void drawDots(Canvas canvas) {
        int x = (viewWidth - actualWidth) / 2 + radius;
        int y = radius;
        for (int i = 0; i < mCount; i++) {
            boolean selected = selectedPos == i;
            canvas.drawCircle(x, y, radius, selected ? selectedPaint : unselectedPaint);
            x += (radius * 2 + dotMargin);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mViewHeight = measureHeight(heightMeasureSpec);
        viewWidth = measureWidth(widthMeasureSpec);
        setMeasuredDimension(viewWidth, mViewHeight);
    }

    private int measureWidth(int measureSpec) {
        int preferred = actualWidth;
//        if (preferred<0) preferred = 0;
        return getMeasurement(measureSpec, preferred);
    }

    private int measureHeight(int measureSpec) {
        int preferred = radius * 2 + paddingBottom + paddingTop;
        return getMeasurement(measureSpec, preferred);
    }

    private int getMeasurement(int measureSpec, int preferred) {
        int specSize = View.MeasureSpec.getSize(measureSpec);
        int measurement = 0;

        switch (View.MeasureSpec.getMode(measureSpec)) {
            case View.MeasureSpec.EXACTLY:
                measurement = specSize;
                break;
            case View.MeasureSpec.AT_MOST:
                measurement = Math.min(preferred, specSize);
                break;
            default:
                measurement = preferred;
                break;
        }
        return measurement;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        selectedPos = i;
        postInvalidate();
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

}
