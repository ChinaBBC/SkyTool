package zx.com.skytool;

import android.support.annotation.Nullable;

import java.math.BigDecimal;

/**
 *
 *@auther zx
 *@time 2019/6/4
 *@describe 字符串工具
 */
public final class ZxStringUtil {
    //判断字符是否为空
    public static boolean isEmpty(@Nullable CharSequence str){
        return str == null || str.length() == 0 || str.equals("null");
    }
    public static boolean isNotEmpty(@Nullable CharSequence str){
        return !isEmpty(str);
    }
    //数字字符串的乘法运算
    public static String multiplication(String a,String b,int scale){
        if (scale<0)
            scale = 1;
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.multiply(b1).setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
    }

}
