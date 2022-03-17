package com.google.zxing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;

/**
 * Finishes an activity after a period of inactivity if the device is on battery power.
 */
public final class InactivityTimer {
    private static final String TAG = "InactivityTimer";
    private final Activity activity;
    private AsyncTask inactivityTask;
    private final BroadcastReceiver powerStatusReceiver = new PowerStatusReceiver();
    private boolean registered = false;
    private static final long INACTIVITY_DELAY_MS = 5 * 60 * 1000L;

    public final class InactivityAsyncTask extends AsyncTask {
        private InactivityAsyncTask() {
        }

        @Override
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(INACTIVITY_DELAY_MS);
                Log.i(TAG, "Finishing activity due to inactivity");
                activity.finish();
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }

    final class PowerStatusReceiver extends BroadcastReceiver {
        private PowerStatusReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                if (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) <= 0) {
                    onActivity();
                } else {
                    cancel();
                }
            }
        }
    }

    public InactivityTimer(Activity activity) {
        this.activity = activity;
        onActivity();
    }

    public void cancel() {
        synchronized (this) {
            AsyncTask asyncTask = this.inactivityTask;
            if (asyncTask != null) {
                asyncTask.cancel(true);
                this.inactivityTask = null;
            }
        }
    }

    public void onActivity() {
        synchronized (this) {
            cancel();
            this.inactivityTask = new InactivityAsyncTask();
            if (Build.VERSION.SDK_INT > 11) {
                inactivityTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
            } else {
                inactivityTask.execute(new Object[0]);
            }
        }
    }

    public void onPause() {
        synchronized (this) {
            cancel();
            if (registered) {
                activity.unregisterReceiver(powerStatusReceiver);
                registered = false;
            } else {
                Log.w(TAG, "PowerStatusReceiver was never registered?");
            }
        }
    }

    public void onResume() {
        synchronized (this) {
            if (registered) {
                Log.w(TAG, "PowerStatusReceiver was already registered?");
            } else {
                activity.registerReceiver(powerStatusReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
                registered = true;
            }
            onActivity();
        }
    }
}