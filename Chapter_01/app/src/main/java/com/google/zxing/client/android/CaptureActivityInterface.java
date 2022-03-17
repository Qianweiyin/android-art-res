package com.google.zxing.client.android;

import android.os.Handler;

import com.google.zxing.TodoResult;
import com.google.zxing.client.android.camera.CameraManager;

/**
 * Created by wxkly.
 * Date: 20-8-25
 * Description:
 */

public interface CaptureActivityInterface {

    interface a {
        void animatorStart();

        void animatorEnd();

        CameraManager getCameraManager();

        void handleDecode(TodoResult result);

        void drawViewfinder();

        Handler getHandler();


//        void handleDecode(Result result, Bitmap barcode, float scaleFactor);
//
//        void setELinkResult(ELinkResult eLinkResult);
//        void onPreviewData(int width, int height, byte[] data);


    }

}
