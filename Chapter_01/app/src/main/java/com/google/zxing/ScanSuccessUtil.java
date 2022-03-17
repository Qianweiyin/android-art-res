package com.google.zxing;

import android.content.Context;
import android.media.MediaPlayer;

public class ScanSuccessUtil {
    private final MediaPlayer.OnCompletionListener beepListener;
    private MediaPlayer caM;
    private boolean caO;
    public Context context;
    public String frW;
    private boolean gHT;
    private ScanSuccessInterface gHU;

    public ScanSuccessUtil(Context context) {
        this.gHT = false;
        this.beepListener = new MediaPlayer.OnCompletionListener() { // from class: com.yunzhijia.scan.ScanSuccessUtil.5
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);
            }
        };
        this.context = context;
    }

    public ScanSuccessUtil(Context context, String str, ScanSuccessInterface bVar) {
        this.gHT = false;
        this.beepListener = new MediaPlayer.OnCompletionListener() { // from class: com.yunzhijia.scan.ScanSuccessUtil.5
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);
            }
        };
        this.context = context;
        this.frW = str;
        this.gHU = bVar;
        this.caO = true;
        initData();
    }






    /* JADX WARN: Type inference failed for: r0v10, types: [com.yunzhijia.common.util.c, java.lang.Exception] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(TodoResult r302) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yunzhijia.scan.ScanSuccessUtil.a(com.yunzhijia.qrcode.e):void");
    }


    /* JADX INFO: Access modifiers changed from: private */
    public void buG() {
        if (!this.gHT) {
            this.gHU.btC();
        }
    }


    private String getEncoding(String str) {
        try {
            return str.equals(new String(str.getBytes("GB2312"), "GB2312")) ? "GB2312" : str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1")) ? "ISO-8859-1" : str.equals(new String(str.getBytes("SJIS"), "SJIS")) ? "SJIS" : str.equals(new String(str.getBytes("GBK"), "GBK")) ? "GBK" : "unstoppable";
        } catch (Exception unused) {
            return "unstoppable";
        }
    }



    private void initData() {
        if (!ELinkClassI.mG(this.frW) && "is_from_scan_local_image".equals(this.frW)) {
            this.gHT = true;
        }
    }




    public void c(TodoResult eVar) {
        a(eVar);
    }
}