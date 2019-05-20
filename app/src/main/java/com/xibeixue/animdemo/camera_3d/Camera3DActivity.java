package com.xibeixue.animdemo.camera_3d;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.xibeixue.animdemo.R;
import com.xibeixue.animdemo.SampleActivityBase;
import com.xibeixue.animdemo.shader.ShimmerView;

public class Camera3DActivity extends SampleActivityBase {

    private Camera3DView m3DView;
    private int mViewWidth;
    private int mViewHeight;

    @Override
    protected View getSampleView() {
        m3DView = new Camera3DView(this);
        m3DView.setImageResource(R.drawable.dog);
        return m3DView;
    }

    protected FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        lp.width = getResources().getDimensionPixelSize(R.dimen.camera3d_view_width);
        lp.height = getResources().getDimensionPixelSize(R.dimen.camera3d_view_height);
        mViewWidth = lp.width;
        mViewHeight = lp.height;
        return lp;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ooo", "getMeasuredWidth=" + m3DView.getMeasuredWidth());
        Rotate3dAnimation _3dAnim = new Rotate3dAnimation(this, 0, 360, mViewWidth / 2, mViewHeight / 2, 0, false);
        _3dAnim.setRepeatMode(ValueAnimator.RESTART);
        _3dAnim.setRepeatCount(10);
        _3dAnim.setDuration(3000);
        m3DView.startAnimation(_3dAnim);
    }
}
