package it.corelab.airbooks;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Roberto_Vecchio on 27/02/18.
 */

public class CustomTextInputLayout extends TextInputLayout {

    private float mainHintTextSize;
    private float editTextSize;

    public CustomTextInputLayout(Context context) {
        this(context, null);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CustomTextInputLayout);

        mainHintTextSize = a.getDimensionPixelSize(
                R.styleable.CustomTextInputLayout_mainHintTextSize, 0);

        a.recycle();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        final boolean b = child instanceof EditText && mainHintTextSize > 0;

        if (b) {
            final EditText e = (EditText) child;
            editTextSize = e.getTextSize();
            e.setTextSize(TypedValue.COMPLEX_UNIT_PX, mainHintTextSize);
        }

        super.addView(child, index, params);

        if (b) {
            getEditText().setTextSize(TypedValue.COMPLEX_UNIT_PX, editTextSize);
        }
    }

    // Units are pixels.

    public float getMainHintTextSize() {
        return mainHintTextSize;
    }

    // This optional method allows for dynamic instantiation of this class and
    // its EditText, but it cannot be used after the EditText has been added.
    // Units are scaled pixels.

    public void setMainHintTextSize(float size) {
        if (getEditText() != null) {
            throw new IllegalStateException(
                    "Hint text size must be set before EditText is added");
        }

        mainHintTextSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, size, getResources().getDisplayMetrics());
    }
}
