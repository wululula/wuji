package com.example.wuji.util;

import android.text.TextUtils;

public class StringUtil {
    public static boolean isInvalid(String text){
        boolean isInvalid = false;
        if (TextUtils.isEmpty(text)||text.contains(" ")){
            isInvalid = true;
        }
        return isInvalid;
    }

}
