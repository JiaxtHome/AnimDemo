package com.xibeixue.animdemo.bitmapmesh;

import android.view.View;

import com.xibeixue.animdemo.SampleActivityBase;

public class BitmapMeshctivity extends SampleActivityBase {

    private BitmapMeshView mMeshView;

    @Override
    protected View getSampleView() {
        mMeshView = new BitmapMeshView(this);
        mMeshView.setText("这是一个测试BitmapMesh的文本翻转示例！");
        return mMeshView;
    }

}
