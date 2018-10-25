package com.umeng.soexample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.umeng.soexample.R;

public class WaterView extends View {

    private Paint mPaintTop;
    private Paint mPaintBottom;
    private Path mPathTop;
    private Path mPathBottom;
    private float f;
    private int bottom;
    private int top;


    public WaterView(Context context) {
        super(context);
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.WaterView);
        bottom = ty.getColor(R.styleable.WaterView_bottom, Color.WHITE);
        top = ty.getColor(R.styleable.WaterView_top, Color.WHITE);
        ty.recycle();
        init(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaintTop = new Paint();
        mPaintBottom = new Paint();

        mPaintTop.setColor(top);
        mPaintTop.setAntiAlias(true);

        mPaintBottom.setColor(bottom);
        mPaintBottom.setAntiAlias(true);

        mPathTop = new Path();
        mPathBottom = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathTop.reset();
        mPathBottom.reset();
        f -= 0.1f;
        //起始的位置
        mPathBottom.moveTo(getLeft(), getBottom());
        mPathTop.moveTo(getLeft(), getBottom());

        //获取每个宽度的所占的度数
        double Mx = Math.PI * 2 / getWidth();

        for (int x = 0; x < getWidth(); x += 20) {
            float y = (float) (10 * Math.cos(Mx * x + f) + 10);
            mPathBottom.lineTo(x, y);
            mPathTop.lineTo(x, (float) (10 * Math.sin(Mx * x + f)));
            listener.getY(y);
        }
        //终止的位置
        mPathTop.lineTo(getRight(), getBottom());
        mPathBottom.lineTo(getRight(), getBottom());

        canvas.drawPath(mPathTop, mPaintTop);
        canvas.drawPath(mPathBottom, mPaintBottom);
        postInvalidateDelayed(20);
    }

    private AnnListener listener;
    public void getAnn(AnnListener listener){
        this.listener=listener;
    }



    public interface AnnListener{
        void getY(float y);
    }



}
