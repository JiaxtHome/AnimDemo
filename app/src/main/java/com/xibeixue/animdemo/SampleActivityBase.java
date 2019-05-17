package com.xibeixue.animdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public abstract class SampleActivityBase extends Activity {
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sample_base_layout);
        mContainer = (FrameLayout) findViewById(R.id.base_container);
        mContainer.setBackgroundColor(getBackGroundColor());
        addSampleView(getSampleView());
    }

    private void addSampleView(View v) {
        mContainer.addView(v, getLayoutParams());
    }

    protected FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        return lp;
    }

    abstract protected View getSampleView();

    protected int getBackGroundColor() {
        return Color.WHITE;
    }
}
