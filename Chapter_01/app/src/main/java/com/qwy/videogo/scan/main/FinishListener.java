package com.qwy.videogo.scan.main;

import android.app.Activity;
import android.content.DialogInterface;

public final class FinishListener implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener, Runnable {
    private final Activity activityToFinish;

    public FinishListener(Activity activity) {
        activityToFinish = activity;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        run();
    }

    @Override
    public void run() {
        activityToFinish.finish();
    }
}