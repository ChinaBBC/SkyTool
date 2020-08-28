package zx.com.skytool;

import android.util.Log;

/**
 *
 *@作者 zx
 *@创建日期 2019/5/8 15:02
 *@描述 控制台输出
 */
public final class ZxLogUtil {
    private final static String TAG = "<<ZXSkyTool>>";

    /**
     * 只是个信息
     * @param msg
     */
    public static void logNormal(String msg){
        if (SkyTool.isEnableDebug()) Log.i(TAG,msg);
    }

    /**
     * 只是个错误
     * @param msg
     */
    public static void logError(String msg){
        if (SkyTool.isEnableDebug()) Log.e(TAG,msg);
    }

    /**
     * 只是个调试
     * @param msg
     */
    public static void logDebug(String msg){
        if (SkyTool.isEnableDebug()) Log.d(TAG,msg);
    }

    /**
     * 只是个警告
     * @param msg
     */
    public static void logWarning(String msg){
        if (SkyTool.isEnableDebug()) Log.w(TAG,msg);
    }

    /**
     * 只是不知道啥
     * @param msg
     */
    public static void logVerbose(String msg){
        if (SkyTool.isEnableDebug()) Log.v(TAG,msg);
    }

    /**
     * 是个what terrible fuck
     * @param msg
     */
    public static void logBad(String msg){
        if (SkyTool.isEnableDebug()) Log.wtf(TAG,msg);
    }
}
