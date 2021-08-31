package com.gts.saintfarmpractice.util;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class SaintfarmTextView extends AppCompatTextView {

    public SaintfarmTextView(Context context) {
        this(context, null);
        //FontsUtils.setFont(this,context,null);
    }

    public SaintfarmTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
        //FontsUtils.setFont(this,context,attrs);
    }

    public SaintfarmTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontsUtils.setFont(this, context, attrs);
    }
}
