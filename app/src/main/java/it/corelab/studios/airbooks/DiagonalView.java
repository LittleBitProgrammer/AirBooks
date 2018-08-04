package it.corelab.studios.airbooks;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import developer.shivam.library.R.styleable;

public class DiagonalView extends android.support.v7.widget.AppCompatImageView {
    Context mContext;
    int height = 0;
    float angle = 15.0F;
    int width = 0;
    Path mPath;
    Paint mPaint;
    int diagonalColor;
    int backgroundColor;
    public static String RIGHT = "right";
    public static String LEFT = "left";
    String diagonalGravity;

    public DiagonalView(Context context) {
        super(context);
        this.diagonalGravity = LEFT;
        this.init(context, (AttributeSet)null);
    }

    public DiagonalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.diagonalGravity = LEFT;
        this.init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mPath = new Path();
        this.mPaint = new Paint(1);
        TypedArray styledAttributes = this.getContext().obtainStyledAttributes(attrs, styleable.diagonal, 0, 0);
        this.angle = (float)styledAttributes.getInt(styleable.diagonal_angle, 0);
        this.diagonalColor = styledAttributes.getColor(styleable.diagonal_diagonalColor, -1);
        if (styledAttributes.hasValue(styleable.diagonal_diagonalGravity)) {
            this.diagonalGravity = styledAttributes.getString(styleable.diagonal_diagonalGravity);
        }

        this.backgroundColor = styledAttributes.getColor(styleable.diagonal_backgroundColor, 0);
        styledAttributes.recycle();
        this.mPaint.setColor(this.diagonalColor);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(this.backgroundColor);
        this.height = this.getMeasuredHeight();
        this.width = this.getMeasuredWidth();
        float perpendicularHeight = (float)((double)this.width * Math.tan(Math.toRadians((double)this.angle)));
        if (this.diagonalGravity.equals("right")) {
            this.mPath.moveTo((float)this.width, (float)this.height);
            this.mPath.lineTo(0.0F, (float)this.height - perpendicularHeight);
            this.mPath.lineTo(0.0F, (float)(this.height + 1));
        } else {
            this.mPath.moveTo(0.0F, (float)this.height);
            this.mPath.lineTo((float)this.width, (float)this.height - perpendicularHeight);
            this.mPath.lineTo((float)this.width, (float)(this.height + 1));
        }

        canvas.drawPath(this.mPath, this.mPaint);
    }

    public void setAngle(float angle) {
        this.mPath.reset();
        this.angle = angle;
        this.invalidate();
    }

    public float getAngle() {
        return angle;
    }

    public Drawable getDrawable(){
        return this.getBackground();
    }

    public void setDiagonalGravity(String gravity) {
        this.mPath.reset();
        this.diagonalGravity = gravity;
        this.invalidate();
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        this.invalidate();
    }

    public void setDiagonalColor(int color) {
        this.diagonalColor = color;
        this.invalidate();
    }
}
