package com.vutuanchien.android_chap21_subactivities;

import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class translateAnimation extends TranslateAnimation {
    public translateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue) {
        super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
    }

    long time = 0;

    public long getTime() {
        return time;

    }

    @Override
    public boolean getTransformation(long currentTime, Transformation outTransformation) {

        time = currentTime - getStartTime();
        return super.getTransformation(currentTime, outTransformation);
    }
}
