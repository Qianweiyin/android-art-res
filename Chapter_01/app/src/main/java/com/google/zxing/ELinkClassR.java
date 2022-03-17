package com.google.zxing;

import android.os.CountDownTimer;
import android.os.Handler;

public class ELinkClassR {
    private CountDownTimer dCB = null;
    private Handler mHandler;

    /* loaded from: kws_10.5.7_1290-enjarify.jar:com/kdweibo/android/util/aq$a.class */
    public interface a {
        void aei();

        void cI(long j);
    }

    public ELinkClassR() {
        this.mHandler = null;
        this.mHandler = new Handler();
    }

    public void a(final long j, final a aVar) {
        this.mHandler.post(new Runnable() { // from class: com.kdweibo.android.util.aq.1
            @Override // java.lang.Runnable
            public void run() {
                ELinkClassR.this.cancelTimer();
                ELinkClassR.this.dCB = new CountDownTimer(j, 1000L) { // from class: com.kdweibo.android.util.aq.1.1
                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        if (aVar != null) {
                            aVar.aei();
                        }
                    }

                    @Override // android.os.CountDownTimer
                    public void onTick(long j2) {
                        if (aVar != null) {
                            aVar.cI(j2 / 1000);
                        }
                    }
                };
                ELinkClassR.this.dCB.start();
            }
        });
    }

    public void cancelTimer() {
        CountDownTimer countDownTimer = this.dCB;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.dCB = null;
        }
    }
}