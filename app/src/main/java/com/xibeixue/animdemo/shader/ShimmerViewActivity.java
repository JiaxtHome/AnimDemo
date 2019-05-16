package com.xibeixue.animdemo.shader;

import android.graphics.Color;
import android.view.View;

import com.xibeixue.animdemo.SampleActivityBase;

public class ShimmerViewActivity extends SampleActivityBase {

    @Override
    protected View getSampleView() {
        ShimmerView sv = new ShimmerView(this);
        sv.setTextSize(28);
        sv.setText("向右滑动解锁");
        return sv;
    }

    @Override
    protected int getBackGroundColor() {
        return Color.BLACK;
    }

}
