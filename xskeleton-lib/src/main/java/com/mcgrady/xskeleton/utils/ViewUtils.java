package com.mcgrady.xskeleton.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mcgrady on 2019-08-19.
 */
public class ViewUtils {

    public static void setTextColorPart(Context context, TextView textView, String flagStart, String part) {
        setTextColorPart(context, textView, flagStart, "", part);
    }

    public static void setTextColorPart(Context context, TextView textView, String flagStart, String flagEnd, String part) {
        setTextColorPart(context, textView, flagStart, flagEnd, part, context.getResources().getColor(android.R.color.black));
    }

    public static void setTextColorPart(Context context, TextView textView, String flagStart, String flagEnd, String part, int resId) {
        if (StringUtils.isEmpty(flagStart)) {
            flagStart = "";
        }
        if (StringUtils.isEmpty(flagEnd)) {
            flagEnd = "";
        }
        if (StringUtils.isEmpty(part)) {
            part = "";
        }
        String content = flagStart + part + flagEnd;

        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        ForegroundColorSpan redSpan = new ForegroundColorSpan(resId);
        builder.setSpan(redSpan, flagStart.length(), (flagStart + part).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        textView.setText(builder);
    }

    public static void setDrawableLft(Context context, TextView textView, int resId) {
        textView.setCompoundDrawables(getDrawable(context, resId), null, null, null);
    }

    public static void setDrawableRight(Context context, TextView textView, int resId) {
        textView.setCompoundDrawables(null, null, getDrawable(context, resId), null);
    }

    public static void setDrawableTop(Context context, TextView textView, int resId) {
        textView.setCompoundDrawables(null, getDrawable(context, resId), null, null);
    }
    public static void setDrawableBottom(Context context, TextView textView, int resId) {
        textView.setCompoundDrawables(null, null, null, getDrawable(context, resId));
    }

    public static void resetImage(TextView textView) {
        textView.setCompoundDrawables(null, null, null, null);
    }

    private static Drawable getDrawable(Context context, int resId) {
        if (resId == -1) {
            return null;
        }
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    public static void isShowBottomLine(TextView textView, boolean isShow) {
        if (isShow) {
            textView.setText(Html.fromHtml("<u>"+textView.getText()+"</u>"));
        } else {
            textView.setText(textView.getText());
        }

    }

    /**
     * 给特定字加特定颜色
     * @param color 关键字颜色
     * @param text  整体文本
     * @param keyword 关键字
     * @return
     */
    public static SpannableString matcherSearchText(int color, String text, String keyword){
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);
        SpannableString ss = new SpannableString(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }

    /**
     * 设置下划线
     */

    public static void setTvUnderLine(TextView textView) {
        //下划线
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        textView.getPaint().setAntiAlias(true);
    }

    /**
     * 取消下划线
     */
    public static void clearUnderLine(TextView textView){
        textView.getPaint().setFlags(0);
        //抗锯齿
        textView.getPaint().setAntiAlias(true);
    }
}
