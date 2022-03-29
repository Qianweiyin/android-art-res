package com.google.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;

import com.qwy.chapter_01.R;

import java.io.Closeable;

public class ScanMediaPlayer implements MediaPlayer.OnErrorListener, Closeable {
    private static final String TAG = "ScanMediaPlayer";
    private final Activity activity;
    private MediaPlayer mediaPlayer = null;
    private boolean playBeep;
    private boolean caO;

    public ScanMediaPlayer(Activity activity) {
        this.activity = activity;
        ME();
    }

    private static boolean b(boolean playBeep, Context context) {
        if (playBeep
                &&
                ((AudioManager) context.getApplicationContext()
                        .getSystemService(Context.AUDIO_SERVICE)).getRingerMode()
                        != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        return playBeep;
    }

    private MediaPlayer bw(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(R.raw.scan);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setLooping(false);
            mediaPlayer.setVolume(0.1f, 0.1f);
            mediaPlayer.prepare();
            return mediaPlayer;
        } catch (Exception e) {
            Log.w(TAG, e);
            mediaPlayer.release();
            return null;
        }
    }

    public void ME() {
        synchronized (this) {
            playBeep = b(true, this.activity);
            this.caO = true;
            if (playBeep && mediaPlayer == null) {
                activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
                mediaPlayer = bw(this.activity);
            }
        }
    }

    @Override
    public void close() {
        synchronized (this) {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        synchronized (this) {
            if (i == 100) {
                this.activity.finish();
            } else {
                close();
                ME();
            }
        }
        return true;
    }

    public void playBeepSoundAndVibrate() {
        synchronized (this) {
            if (playBeep && mediaPlayer != null) {
                mediaPlayer.start();
            }
            if (this.caO) {
                ((Vibrator) this.activity.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(200L);
            }
        }
    }
}
