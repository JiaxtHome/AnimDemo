package com.xibeixue.animdemo.svg;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xibeixue.animdemo.R;
import com.xibeixue.animdemo.SampleActivityBase;

public class SvgActivity extends SampleActivityBase {

    private ImageView mImageView;
    private boolean isChecked = false;

    @Override
    protected View getSampleView() {
        mImageView = new ImageView(this);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*Drawable drawable = mImageView.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }*/
                isChecked = !isChecked;
                final int[] stateSet = {isChecked ? android.R.attr.state_checked : 0};
                mImageView.setImageState(stateSet, true);
            }
        });
        mImageView.setImageResource(R.drawable.animated_selector);
        return mImageView;
    }

    @Override
    protected int getBackGroundColor() {
        return Color.WHITE;
    }

    protected FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        lp.width = getResources().getDimensionPixelSize(R.dimen.svg_view_width);
        lp.height = getResources().getDimensionPixelSize(R.dimen.svg_view_height);
        return lp;
    }
}
