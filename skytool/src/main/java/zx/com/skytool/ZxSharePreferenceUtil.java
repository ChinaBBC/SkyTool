package zx.com.skytool;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 *@auther zx
 *@time 2019/6/4
 *@describe 用于share preference 的存取
 */
public final class ZxSharePreferenceUtil {
    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    private Object object;
    public static ZxSharePreferenceUtil preferencesUtil;

    public static ZxSharePreferenceUtil getInstance() {
        if (preferencesUtil == null) {
            synchronized (ZxSharePreferenceUtil.class) {
                if (preferencesUtil == null) {
                    // 使用双重同步锁
                    preferencesUtil = new ZxSharePreferenceUtil();
                }
            }
        }
        return preferencesUtil;
    }

    //使用application context 避免内存溢出
    public ZxSharePreferenceUtil init(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context
                .getApplicationContext());
        return preferencesUtil;
    }


    private ZxSharePreferenceUtil() {

    }

    /**
     * 保存数据 , 所有的类型都适用
     *
     * @param key
     * @param object
     */
    public synchronized void saveParam(String key, Object object) {
        if (editor == null)
            editor = preferences.edit();
            // 得到object的类型
        String type = object.getClass().getSimpleName();
        if ("String".equals(type)) {
            // 保存String 类型
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            // 保存integer 类型
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            // 保存 boolean 类型
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            // 保存float类型
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            // 保存long类型
            editor.putLong(key, (Long) object);
        } else {
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(object.getClass().getName() + " 请将数据序列化，实现serialized!");
            }

            // 不是基本类型则是保存对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                String productBase64 = Base64.encodeToString(
                        baos.toByteArray(), Base64.DEFAULT);
                editor.putString(key, productBase64);
                ZxLogUtil.logDebug("share preference save object success");
            } catch (IOException e) {
                e.printStackTrace();
                ZxLogUtil.logError("share preference save object error");
            }
        }
        editor.commit();
    }

    /**
     * 移除信息
     */
    public synchronized void remove(String key) {
        if (editor == null)
            editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     * 得到保存数据的方法，所有类型都适用
     * @param key
     * @param defaultObject
     * @return
     */
    public Object getParam(String key, Object defaultObject) {
        if (defaultObject == null) {
            return getObject(key);
        }

        String type = defaultObject.getClass().getSimpleName();

        if ("String".equals(type)) {
            return preferences.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return preferences.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return preferences.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return preferences.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return preferences.getLong(key, (Long) defaultObject);
        }
        return getObject(key);
    }

    /**
     * 是否第一次
     *
     * @return
     */
    public boolean isFirst() {
        return (Boolean) getParam("isFirst", true);
    }

    /**
     * 设置是否第一次
     *
     * @return
     */
    public void setFirst(Boolean isFirst) {
        saveParam("isFirst", isFirst);
    }

    /**
     * 是否首次登陆
     *
     * @return
     */
    public boolean isLogin() {
        return (Boolean) getParam("isLogin", false);
    }

    /**
     * 设置是否首次登陆
     * @return
     */
    public void setLogin(Boolean isLogin) {
        saveParam("isLogin", isLogin);
    }

    public Object getObject(String key) {
        String wordBase64 = preferences.getString(key, "");
        byte[] base64 = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            ObjectInputStream bis = new ObjectInputStream(bais);
            object =  bis.readObject();
            Log.d(this.getClass().getSimpleName(), "Get object success");
            return object;
        } catch (Exception e) {

        }
        Log.e(this.getClass().getSimpleName(), "Get object is error");
        return null;
    }
}
