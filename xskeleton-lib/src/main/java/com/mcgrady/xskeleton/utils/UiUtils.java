package com.mcgrady.xskeleton.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mcgrady on 2019-08-19.
 */
public class UiUtils {

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

    /**
     * 汉字 转换为对应的 UTF-8编码
     * @param s
     * @return
     */
    public static String convertStringToUTF8(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;
                    b = Character.toString(c).getBytes("utf-8");
                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        //转换为unsigned integer  无符号integer
                    /*if (k < 0)
                        k += 256;*/
                        k = k < 0? k+256:k;
                        //返回整数参数的字符串表示形式 作为十六进制（base16）中的无符号整数
                        //该值以十六进制（base16）转换为ASCII数字的字符串
                        sb.append(Integer.toHexString(k).toUpperCase());

                        // url转置形式
                        // sb.append("%" +Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * URL 解码
     *
     * @return String
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 编码
     *
     * @return String
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param str
     * @param len 字节长度
     * @param use_ellipsis
     * @return
     */
    public static String substrUTF8(String str, int len, boolean use_ellipsis) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return substrUTF8(str.getBytes(), len, use_ellipsis);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return str;
    }
    public static String substrUTF8(byte[] bytes, int len, boolean use_ellipsis)
            throws UnsupportedEncodingException {
        if (bytes == null || bytes.length == 0 || len <= 0) {
            return "";
        }
        if (len >= bytes.length) {
            return new String(bytes, "UTF-8");
        }
        int index = 0;
        while (index < len) {
            final byte b = bytes[index];
            // is ascii
            if ((b & 0x80/*0b10000000*/) == 0) {
                ++index;
            } else {
                int count = 1;
                byte t = 0x40/*0b01000000*/;
                for (int i = 1; i < 8; ++i) {
                    if ((b & t) != 0) {
                        ++count;
                        t >>>= 1;
                    } else {
                        break;
                    }
                }
                final int sum = index + count;
                if (sum <= len) {
                    index = sum;
                } else {
                    break;
                }
            }
        }

        String s = new String(bytes, 0, index, "UTF-8");
        return use_ellipsis ? s + "..." : s;
    }

    private static String byteToBinary(byte b) {
        final char[] chars = new char[8];
        byte t = 0x1/*0b10000000*/;
        for (int i = 7; i >= 0; --i) {
            if ((b & t) == 0) {
                chars[i] = '0';
            } else {
                chars[i] = '1';
            }
            t <<= 1;
        }
        return String.valueOf(chars);
    }

}
