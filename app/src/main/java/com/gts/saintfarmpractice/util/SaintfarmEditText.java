package com.gts.saintfarmpractice.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class SaintfarmEditText extends AppCompatEditText {

    public SaintfarmEditText(Context context) {
        this(context, null);
        //FontsUtils.setFont(this,context,null);
    }

    public SaintfarmEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        //FontsUtils.setFont(this,context,attrs);
    }

    public SaintfarmEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontsUtils.setFont(this, context, attrs);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
}
