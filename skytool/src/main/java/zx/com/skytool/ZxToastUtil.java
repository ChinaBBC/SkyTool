package zx.com.skytool;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 *
 *@作者 zx
 *@创建日期 2019/5/8 10:43
 *@描述 提示的工具类
 */
public final class ZxToastUtil {

    private static Toast mToast;
    /**
     * 普通的toast
     * @param msg
     */
    public final static void normalToast(final String msg){
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(SkyTool.getInstance(),msg,Toast.LENGTH_LONG);
                mToast.show();
            }
        });
    }

    /**
     * 居中的toast
     * @param msg
     */
    public static void centerToast(final String msg){
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(SkyTool.getInstance(),msg,Toast.LENGTH_LONG);
                mToast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                mToast.show();
            }
        });

    }

    /**
     * 带图片的toast
     * @param msg
     */
    public static void imageToast(final String msg, final int images){
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                Context instance = SkyTool.getInstance();
                mToast = Toast.makeText(instance,msg,Toast.LENGTH_LONG);
                LinearLayout linearLayout = (LinearLayout) mToast.getView();

                ImageView imageView = new ImageView(instance);
                imageView.setImageResource(images);
                linearLayout.addView(imageView);
                mToast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                mToast.show();
            }
        });
    }

    /**
     * 自定义toast
     * @param msg
     */
    public static void diyToast(final String msg, final int lay){
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                Context instance = SkyTool.getInstance();
                mToast = Toast.makeText(instance,msg,Toast.LENGTH_LONG);
                LinearLayout diyView = (LinearLayout) LayoutInflater.from(instance).inflate(lay, null);
                mToast.setView(diyView);
                mToast.show();
            }
        });
    }
}
