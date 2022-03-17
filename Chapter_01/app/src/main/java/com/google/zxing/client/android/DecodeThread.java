/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android;

import com.google.zxing.ELinkProcessType;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;

/**
 * This thread does all the heavy lifting of decoding the images.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
final class DecodeThread extends Thread {


    private final CaptureActivityInterface.a captureActivityInterface;
    private final ELinkProcessType processType;
    private Handler handler;
    private final CountDownLatch handlerInitLatch = new CountDownLatch(1);

    DecodeThread(CaptureActivityInterface.a captureActivityInterface,
                 ELinkProcessType processType) {
        this.captureActivityInterface = captureActivityInterface;
        this.processType = processType;
    }

    Handler getHandler() {
        //保证线程同步，防止handler为空。
        try {
            handlerInitLatch.await();
        } catch (InterruptedException ie) {
            // continue?
        }
        return handler;
    }

    @Override
    public void run() {
        Looper.prepare();
        handler = new DecodeHandler(captureActivityInterface, processType);
        handlerInitLatch.countDown();
        Looper.loop();
    }

}
