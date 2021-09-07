package com.gts.saintfarmpractice.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gts.saintfarmpractice.R;

import java.util.HashMap;

public class FontsUtils {

    public static final int bodoni = 0;
    public static final int inriaserif_bold = 1;
    public static final int inriaserif_bold_italic = 2;
    public static final int inriaserif_italic = 3;
    public static final int inriaserif_normal = 4;
    public static final int montserrat_regular = 5;
    public static final int myriadpro_normal = 6;
    public static final int nunitosans_bold = 7;
    public static final int nunitosans_normal = 8;
    public static final int st_normal = 9;

    private static HashMap<Integer, Typeface> fontCache = new HashMap<>();

    public static void setFont(TextView element, Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.text_style);
        int textStyle = attributeArray.getInt(R.styleable.text_style_textStyle, montserrat_regular);
        element.setTypeface(getTypeface(textStyle, context));
        attributeArray.recycle();
    }

    private static Typeface getTypeface(int fontStyle, Context context) {
        Typeface typeface = fontCache.get(fontStyle);
        if (typeface == null) {
            try {
                typeface = getFont(fontStyle, context);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontStyle, typeface);
        }
        return typeface;
    }

    private static Typeface getFont(int fontForStyle, Context context) {
        Typeface typeface = null;
        switch (fontForStyle) {
            case FontsUtils.bodoni:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/bodoni.ttf");
                break;
            case FontsUtils.inriaserif_bold:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/inriaserif_bold.ttf");
                break;
            case FontsUtils.inriaserif_bold_italic:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/inriaserif_bold_italic.ttf");
                break;
            case FontsUtils.inriaserif_italic:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/inriaserif_italic.ttf");
                break;
            case FontsUtils.inriaserif_normal:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/inriaserif_normal.ttf");
                break;

            case FontsUtils.montserrat_regular:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf");
                break;
            case FontsUtils.myriadpro_normal:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/myriadpro_normal.otf");
                break;
            case FontsUtils.nunitosans_bold:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/nunitosans_bold.ttf");
                break;
            case FontsUtils.nunitosans_normal:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/nunitosans_normal.ttf");
                break;
            case FontsUtils.st_normal:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/st_normal.otf");
                break;
        }
        return typeface;
    }
}
