package zx.com.skytool;

import android.content.Context;

/**
 *
 *@auther zx
 *@time 2019/6/4
 *@describe 天工
 */
public final class SkyTool {
    private static Context appContext;
    public static Context getInstance() {
        return appContext;
    }
    //天工初始化
    public static void init(Context context){
        appContext = context;
        BackgroundTasks.initInstance();
    }
}
