package com.qingcong.guidepageinstructions;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenpengfei on 2016/5/4.
 */
public class InstructionsView extends View {

    /**
     *  圆点的半径
     */
    private int radius;

    /**
     * 圆点和圆点的间距
     */
    private int cricleSpacing;

    /**
     *  圆点的边框宽度
     */
    private int cricleStrokeWidth;

    /**
     *  静止圆点的颜色
     */
    private int staticCricleColor;

    /**
     *  移动圆点的颜色
     */
    private int moveCricleColor;

    /**
     *  圆点的数量
     */
    private int cricleCount;

    /**
     * 移动的距离
     */
    private int moveOffset;

    private Paint paint;


    public InstructionsView(Context context) {
        super(context);
    }

    public InstructionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.InstructionsView);
        radius = array.getDimensionPixelSize(R.styleable.InstructionsView_radius, 0);
        radius = radius == 0 ? 10 : radius;

        cricleSpacing = array.getDimensionPixelSize(R.styleable.InstructionsView_criclespacing, 0);
        cricleSpacing = cricleSpacing == 0 ? 10 : cricleSpacing;

        cricleStrokeWidth = array.getDimensionPixelSize(R.styleable.InstructionsView_criclestrokewidth, 0);
        cricleStrokeWidth = cricleStrokeWidth == 0 ? 10 : cricleStrokeWidth;

        staticCricleColor = array.getColor(R.styleable.InstructionsView_staticcriclecolor, 0);
        staticCricleColor = staticCricleColor == 0 ? Color.RED : staticCricleColor;

        moveCricleColor = array.getColor(R.styleable.InstructionsView_movecriclecolor, 0);
        moveCricleColor = moveCricleColor == 0 ? Color.BLACK : moveCricleColor;

        cricleCount = array.getInt(R.styleable.InstructionsView_criclecount, 0);
        cricleCount = cricleCount == 0 ? 1 : cricleCount;
        paint = new Paint();

    }

    public InstructionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InstructionsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = radius * 2 + cricleStrokeWidth * 2;
        int width = cricleCount * height + (cricleCount - 1) * cricleSpacing;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(staticCricleColor);
        //空心
        paint.setStyle(Paint.Style.STROKE);
        // 消除锯齿
        paint.setAntiAlias(true);
        //边框宽度
        paint.setStrokeWidth(cricleStrokeWidth);
        for(int i = 0; i < cricleCount; i++) {
            canvas.drawCircle(i * cricleSpacing + (radius +cricleStrokeWidth) * 2 * i + (radius + cricleStrokeWidth), radius + cricleStrokeWidth, radius, paint);
        }
        // 绘制移动的圆点
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(moveCricleColor);
        canvas.drawCircle((radius + cricleStrokeWidth) + moveOffset, radius + cricleStrokeWidth, radius + cricleStrokeWidth / 2, paint);
    }

    //设置移动圆点的移动offset
    public void setMoveOffset(int position, float positionOffset) {
        int signMoveOffset = (radius + cricleStrokeWidth) * 2 + cricleSpacing;
        moveOffset = position * signMoveOffset + (int) (signMoveOffset * positionOffset);
        invalidate();
    }
}
