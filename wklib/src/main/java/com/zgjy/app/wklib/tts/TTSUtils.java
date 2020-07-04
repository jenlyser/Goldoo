package com.zgjy.app.wklib.tts;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

/**
 * TTS工具类
 * 如果是中文需要先设置语音引擎,推荐使用(科大讯飞语音引擎3.0),安装他们的apk即可,然后在系统设置里设置语音更改tts语音引擎为科大讯飞或其他下载好的
 * 有些系统自带了中文语音引擎就可以不设置了
 */
public final class TTSUtils {
    //日志标签
    private static final String TAG = TTSUtils.class.toString();
    //tts对象
    private static TextToSpeech mSpeech;
    //初始化状态
    private static int initStatus;
    /**
     * 临时文本
     */
    private static String mTempText = "";

    /**
     * 建议在 Application 的 onCreate() 方法中初始化，这样就可以在任何界面中直接调用{@link#onStartSpeech(String,int)}方法
     *
     * @param context 上下文
     */
    public static void initSpeech(Context context) {
        mSpeech = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                initStatus = status;
            }
        });

        //初始化成功
        if (initStatus == TextToSpeech.SUCCESS) {
            Log.i(TAG, "TTS初始化成功");
            Locale locale = Locale.SIMPLIFIED_CHINESE;
            int result = mSpeech.setLanguage(locale);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //不可尽信,有时候明明支持还是会进入这里.
                Log.e(TAG, "TTS设置语言：不支持" + locale.getDisplayLanguage());
            }
        } else {
            Log.e(TAG, "TTS初始化失败");
        }
    }

    /**
     * 播报语音，或打断之前的播放
     *
     * @param text 需要播报的字符串
     */
    private static void startSpeech(String text, int type) {
        if (null != mSpeech) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mSpeech.speak(text, type, null, "");
            } else {
                mSpeech.speak(text, type, null);
            }
        } else {
            Log.d(TAG, "TTS还未初始化");
        }
    }


    /**
     * 播放语音,会打断前面播放的语音.如果要是想列队播放,使用speechAdd方法
     *
     * @param str
     */
    public static void speech(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (isSpeeching() && mTempText.equals(str)) {
            return;
        }
        mTempText = str;
        startSpeech(mTempText, TextToSpeech.QUEUE_FLUSH);
    }

    /**
     * 播放语音,不会打断正在播放的语音,等前面的播放完成才继续播放.
     *
     * @param str
     */
    public static void speechAdd(String str) {
        startSpeech(str, TextToSpeech.QUEUE_ADD);
    }


    /**
     * 当前是否正在播放语音
     *
     * @return ture:正在播放  false：没有播放
     */
    public static boolean isSpeeching() {
        return mSpeech != null && mSpeech.isSpeaking();
    }

    /**
     * 停止当前语音播报
     */
    public static void stopSpeech() {
        if (null != mSpeech) {
            mSpeech.stop();
        }
    }

    /**
     * 释放资源操作
     * 调用此方法后，需要调用{@link #initSpeech(Context)}方法进行重新初始化
     */
    public static void onDestroy() {
        if (mSpeech != null) {
            mSpeech.stop();
            mSpeech.shutdown();
            mSpeech = null;
        }
    }
}
