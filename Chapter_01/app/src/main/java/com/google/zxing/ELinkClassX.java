package com.google.zxing;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ELinkClassX {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast dCF = null;

    public static void D(Context context, int i) {
        k(context, i, 0);
    }

    public static void E(Context context, int i) {
        k(context, i, 0);
    }

    public static void a(Context context, CharSequence charSequence) {
        a(context, charSequence, 0);
    }

    public static void a(Context context, final CharSequence charSequence, final int i) {
        if (context != null) {
            handler.post(new Runnable() { // from class: com.kdweibo.android.util.as.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        try {
                            if (ELinkClassX.dCF == null) {
                                Toast unused = ELinkClassX.dCF = Toast.makeText(ELinkToast.getContext(), charSequence, i);
                            }
                            ELinkClassX.dCF.setText(charSequence);
                            ELinkClassX.dCF.setDuration(i);
                            ELinkClassX.dCF.show();
                        } catch (Exception unused2) {
                        }
                    }
                }
            });
        }
    }

    public static void b(Context context, CharSequence charSequence) {
        a(context, charSequence, 1);
    }

    public static void k(Context context, int i, int i2) {
        if (context != null) {
            a(context, context.getString(i), i2);
        }
    }
}
