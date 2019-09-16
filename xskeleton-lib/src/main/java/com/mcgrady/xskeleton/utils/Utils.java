package com.mcgrady.xskeleton.utils;

import android.text.TextUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by mcgrady on 2019-08-22.
 */
public class Utils {

    public static String strJoiner(String separator, List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String string = iterator.next();
            if (!TextUtils.isEmpty(string)) {
                stringBuilder.append(string);
                if (iterator.hasNext()) {
                    stringBuilder.append(separator);
                }
            }
        }

        return stringBuilder.toString();
    }
}
