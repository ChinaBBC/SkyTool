package zx.com.skytool;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 *
 *@作者 zx
 *@创建日期 2019/5/9 10:32
 *@描述 获取验证码倒计时
 */
public final class ZxCountDownTimerUtil<T extends TextView> extends CountDownTimer {
    @NonNull
    private T beginBtn;

    private static long millisInFuture = 60000;//总时长默认60s
    private static long countDownInterval = 1000;//时间间隔默认1s
    private static String runningWord = "秒后重发";
    private static String commonWord = "获取验证码";
    private static int runningStyle = R.drawable.getcode_press;//倒计时中的背景
    private static int commonStyle = R.drawable.getcode_normal;//没有倒计时的背景
    private static int timeColor = Color.RED;//文字颜色


    public ZxCountDownTimerUtil setMillisInFuture(long millisInFuture) {
        this.millisInFuture = millisInFuture;
        return this;
    }

    public ZxCountDownTimerUtil setCountDownInterval(long countDownInterval) {
        this.countDownInterval = countDownInterval;
        return this;
    }

    public ZxCountDownTimerUtil setRunningWord(String runningWord) {
        this.runningWord = runningWord;
        return this;
    }

    public ZxCountDownTimerUtil setCommonWord(String commonWord) {
        this.commonWord = commonWord;
        return this;
    }

    public ZxCountDownTimerUtil setRunningStyle(int runningStyle) {
        this.runningStyle = runningStyle;
        return this;
    }

    public ZxCountDownTimerUtil setCommonStyle(int commonStyle) {
        this.commonStyle = commonStyle;
        return this;
    }

    public ZxCountDownTimerUtil setTimeColor(int timeColor) {
        this.timeColor = timeColor;
        return this;
    }


    public ZxCountDownTimerUtil(T beginBtn) {
        super(millisInFuture, countDownInterval);
        this.beginBtn = beginBtn;
    }
    public ZxCountDownTimerUtil(T beginBtn, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.beginBtn = beginBtn;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        isRunning = true;
        beginBtn.setClickable(false); //设置不可点击
        beginBtn.setText(millisUntilFinished / 1000 + runningWord);  //设置倒计时时间
        beginBtn.setBackgroundResource(runningStyle); //设置按钮为灰
        SpannableString spannableString = new SpannableString(beginBtn.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(timeColor);
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        beginBtn.setText(spannableString);

    }

    private boolean isRunning = true;
    public boolean getRunStatus() {
        return isRunning;
    }
    @Override
    public void onFinish() {
        isRunning = false;
        beginBtn.setText(commonWord);
        beginBtn.setClickable(true);//重新获得点击
        beginBtn.setBackgroundResource(commonStyle);

    }
}
