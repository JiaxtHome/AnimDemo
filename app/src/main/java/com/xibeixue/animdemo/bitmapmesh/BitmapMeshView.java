package com.xibeixue.animdemo.bitmapmesh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class BitmapMeshView extends TextView {


    //横向、纵向划分格数：80*80
    private static final int WIDTH = 80;
    private static final int HEIGHT = 80;
    private Bitmap bitmap = null;
    //顶点数：81*81
    private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
    //顶点坐标数组
    private final float[] orig = new float[COUNT * 2];
    //转换后顶点坐标数组
    private final float[] verts = new float[COUNT * 2];
    float bitmapWidth;
    float unitWidth;
    float bitmapHeight;
    float halfHeight;
    private static final double HALF_PI = Math.PI / 2;

    public BitmapMeshView(Context context) {
        super(context);
        init(context);
    }

    public BitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startPlay();
        return super.onTouchEvent(event);
    }

    public void startPlay() {
        initBitmap();
        ValueAnimator va = ValueAnimator.ofFloat(0, 1.3f);  //因变形区域是0.3,所以最大1.3才能保证完全展开
        va.setDuration(1200);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                calcuVerts(value);
                invalidate();
            }
        });
        va.start();
    }

    private void initBitmap() {
        if (bitmap == null) {
            buildDrawingCache();  //获取View截图
            bitmap = getDrawingCache();
            bitmapWidth = bitmap.getWidth();
            unitWidth = bitmapWidth * 0.3f;  //变形区域长度
            bitmapHeight = bitmap.getHeight();
            halfHeight = bitmapHeight / 2;  //1/2高度

            /*算出顶点原始坐标*/
            int index = 0;
            for (int y = 0; y <= HEIGHT; y++) {
                float fy = bitmapHeight * y / HEIGHT;
                for (int x = 0; x <= WIDTH; x++) {
                    float fx = bitmapWidth * x / WIDTH;
                    orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                    orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                    index += 1;
                }
            }
        }
    }

    /**
     * 计算转换后的顶点坐标
     * @param input 已展开比例
     */
    private void calcuVerts(float input) {
        for (int j = 0; j <= HEIGHT; j++) {
            for (int i = 0; i <= WIDTH; i++) {
                float startX = input * bitmapWidth; //变形部分最右端x值
                float cx = i * 1.0f / WIDTH * bitmapWidth;  //当前顶点x坐标
                float cy = j * 1.0f / HEIGHT * bitmapHeight;  //当前顶点y坐标
                float toHalf = cy - halfHeight;  //距离垂直中线的距离
                if (cx >= startX) { //右侧未展开区域
                    verts[(j * (WIDTH + 1) + i) * 2 + 1] = halfHeight;  //计算y坐标
                    verts[(j * (WIDTH + 1) + i) * 2] = cx;  //计算x坐标
                } else if (cx <= startX - unitWidth) { //左侧完全展开区域
                    verts[(j * (WIDTH + 1) + i) * 2 + 1] = cy;
                    verts[(j * (WIDTH + 1) + i) * 2] = cx;
                } else { // 中间正在展开区域
                    float ratio = (startX - cx) / unitWidth;
                    verts[(j * (WIDTH + 1) + i) * 2 + 1] = (float) (halfHeight + toHalf * Math.sin(HALF_PI * ratio));
                    verts[(j * (WIDTH + 1) + i) * 2] = (float) (cx - toHalf * Math.cos(HALF_PI * ratio) * 1f);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
        } else {
            super.onDraw(canvas);
        }
    }

}