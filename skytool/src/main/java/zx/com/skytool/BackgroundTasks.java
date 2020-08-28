/**
 * @file
 * @brief 后台任务管理器
 * @author rjlee
 * @date 2014年12月6日
 * Modify List:
 * 2014年12月6日 rjlee create it.
 */
package zx.com.skytool;

import android.os.Handler;

/**
 *
 *@auther zx
 *@time 2019/6/13
 *@describe 后台task
 */
public class BackgroundTasks {

    private Handler mHandler = new Handler();


    public void runOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    public boolean postDelayed(Runnable r, long delayMillis) {
        return mHandler.postDelayed(r, delayMillis);
    }

    public Handler getHandler() {
        return mHandler;
    }

    private static BackgroundTasks instance;

    public static BackgroundTasks getInstance() {
        return instance;
    }


    // 需要在主线程中初始化
    public static void initInstance() {
        instance = new BackgroundTasks();
    }

}
