package com.kingpei.lameproject;

import android.os.Handler;
import android.os.Message;

import java.util.logging.LogRecord;

public class Mp3Encoder {
    static {
        System.loadLibrary("mp3encoder");
    }


    public static native int init(String pcmPath, int audioChannels, int bitRate, int sampleRate, String mp3Path);
    public static native void encode();
    public static native void destroy();
}
