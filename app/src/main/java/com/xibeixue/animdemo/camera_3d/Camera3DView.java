package com.xibeixue.animdemo.camera_3d;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class Camera3DView extends ImageView {


    public Camera3DView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public Camera3DView(Context context) {
        super(context);
        init(context);
    }

    public Camera3DView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

    }

}