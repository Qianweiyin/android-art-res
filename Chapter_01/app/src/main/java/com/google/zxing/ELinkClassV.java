package com.google.zxing;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ELinkClassV {


    private static String a(String str, Collection collection, String... strArr) {
        Log.i("CameraConfiguration", "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i("CameraConfiguration", "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i("CameraConfiguration", "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i("CameraConfiguration", "No supported values match");
        return null;
    }

    public static void a(Camera.Parameters parameters, boolean z) {
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        String a2 = z ? a("flash mode", supportedFlashModes, "torch", "on") : a("flash mode", supportedFlashModes, "off");
        if (a2 == null) {
            return;
        }
        if (a2.equals(parameters.getFlashMode())) {
            Log.i("CameraConfiguration", "Flash mode already set to " + a2);
            return;
        }
        Log.i("CameraConfiguration", "Setting flash mode to " + a2);
        parameters.setFlashMode(a2);
    }

    public static void a(Camera.Parameters parameters, boolean z, boolean z2, boolean z3) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        String a2 = z ? (z3 || z2) ? a("focus mode", supportedFocusModes, "auto") : a("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", "auto") : null;
        if (!z3 && a2 == null) {
            a2 = a("focus mode", supportedFocusModes, "macro", "edof");
        }
        if (a2 == null) {
            return;
        }
        if (a2.equals(parameters.getFocusMode())) {
            Log.i("CameraConfiguration", "Focus mode already set to " + a2);
            return;
        }
        parameters.setFocusMode(a2);
    }

    public static Point b(Camera.Parameters parameters, Point point) {
        double d;
        int i;

        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize != null) {
                return new Point(previewSize.width, previewSize.height);
            }
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(supportedPreviewSizes);

        if (point.x < point.y) {
            d = point.y;
            i = point.x;
        } else {
            d = point.x;
            i = point.y;
        }
        double d2 = d / i;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Camera.Size size2 = (Camera.Size) it.next();
            int i2 = size2.width;
            int i3 = size2.height;
            if (i2 * i3 >= 153600) {
                boolean z = i2 < i3;
                int i4 = z ? i3 : i2;
                int i5 = z ? i2 : i3;
                if (Math.abs((i4 / i5) - d2) <= 0.15d) {
                    if (i4 == point.x && i5 == point.y) {
                        Point point2 = new Point(i2, i3);
                        Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + point2);
                        return point2;
                    }
                }
            }
            it.remove();
        }
        if (!arrayList.isEmpty()) {
            Camera.Size size3 = (Camera.Size) arrayList.get(0);
            Point point3 = new Point(size3.width, size3.height);
            Log.i("CameraConfiguration", "Using largest suitable preview size: " + point3);
            return point3;
        }
        Camera.Size previewSize2 = parameters.getPreviewSize();
        if (previewSize2 != null) {
            Point point4 = new Point(previewSize2.width, previewSize2.height);
            Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + point4);
            return point4;
        }
        throw new IllegalStateException("Parameters contained no preview size!");
    }

    public static void b(Camera.Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            Log.i("CameraConfiguration", "Old focus areas: " + g(parameters.getFocusAreas()));
            List<Camera.Area> im = im(400);
            Log.i("CameraConfiguration", "Setting focus area to : " + g(im));
            parameters.setFocusAreas(im);
            return;
        }
        Log.i("CameraConfiguration", "Device does not support focus areas");
    }

    private static Point c(Camera.Parameters parameters, Point point) {
        int i;
        double d;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize != null) {
                return new Point(previewSize.width, previewSize.height);
            }
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        if (point.x < point.y) {
            d = point.x;
            i = point.y;
        } else {
            d = point.y;
            i = point.x;
        }
        double d2 = d / i;
        Camera.Size size = null;
        int i2 = 0;
        for (Camera.Size size2 : supportedPreviewSizes) {
            int i3 = size2.width;
            int i4 = size2.height;
            int i5 = i3 * i4;
            if (i5 < 153600) {
                i2 = i2;
            } else {
                boolean z = i3 < i4;
                int i6 = z ? i3 : i4;
                int i7 = z ? i4 : i3;
                i2 = i2;
                if (Math.abs((i6 / i7) - d2) <= 0.15d) {
                    if (i6 == point.x && i7 == point.y) {
                        return new Point(i3, i4);
                    }
                    if (i5 > i2) {
                        size = size2;
                        i2 = i5;
                    } else {
                        i2 = i2;
                    }
                }
            }
        }
        if (size != null) {
            return new Point(size.width, size.height);
        }
        Camera.Size previewSize2 = parameters.getPreviewSize();
        if (previewSize2 != null) {
            return new Point(previewSize2.width, previewSize2.height);
        }
        throw new IllegalStateException("Parameters contained no preview size!");
    }

    public static void c(Camera.Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            Log.i("CameraConfiguration", "Old metering areas: " + parameters.getMeteringAreas());
            List<Camera.Area> im = im(400);
            Log.i("CameraConfiguration", "Setting metering area to : " + g(im));
            parameters.setMeteringAreas(im);
            return;
        }
        Log.i("CameraConfiguration", "Device does not support metering areas");
    }

    public static void d(Camera.Parameters parameters) {
        String str;
        if (!parameters.isVideoStabilizationSupported()) {
            str = "This device does not support video stabilization";
        } else if (parameters.getVideoStabilization()) {
            str = "Video stabilization already enabled";
        } else {
            Log.i("CameraConfiguration", "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
            return;
        }
        Log.i("CameraConfiguration", str);
    }

    private static String g(Iterable iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Camera.Area area = (Camera.Area) it.next();
            sb.append(area.rect);
            sb.append(':');
            sb.append(area.weight);
            sb.append(' ');
        }
        return sb.toString();
    }

    private static List im(int i) {
        int i2 = -i;
        return Collections.singletonList(new Camera.Area(new Rect(i2, i2, i, i), 1));
    }
}
