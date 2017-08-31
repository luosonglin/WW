package com.winwin.android.Util;

import android.content.Context;
import android.util.TypedValue;

public class DisplayUtil {

    public static int dipToPix(Context context, float dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }

}
