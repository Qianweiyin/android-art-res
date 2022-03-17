package com.google.zxing;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ELinkToast {
    private static Context context;
    private static WeakReference dzY;

    /* loaded from: kws_10.5.7_1290-enjarify.jar:com/kdweibo/android/util/d$a.class */
    public static class a {
        public static Bitmap aj(byte[] bArr) {
            if (bArr.length != 0) {
                return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            }
            return null;
        }

        public static byte[] x(Bitmap bitmap) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    /* loaded from: kws_10.5.7_1290-enjarify.jar:com/kdweibo/android/util/d$b.class */
    public class b {
        public int[] apI() {
            DisplayMetrics apJ = apJ();
            return new int[]{apJ.widthPixels, apJ.heightPixels};
        }

        public DisplayMetrics apJ() {
            return ELinkToast.context.getResources().getDisplayMetrics();
        }

        public int at(float f) {
            return (int) TypedValue.applyDimension(1, f, ELinkToast.context.getResources().getDisplayMetrics());
        }
    }

    /* loaded from: kws_10.5.7_1290-enjarify.jar:com/kdweibo/android/util/d$c.class */
    public static class c {
        public boolean acF() {
            return Environment.getExternalStorageState().equals("mounted");
        }



        public boolean apL() {
            return f(apM());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Exception] */
        /* JADX WARN: Type inference failed for: r0v12, types: [android.net.NetworkInfo] */
        /* JADX WARN: Unknown variable types count: 1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static NetworkInfo apM() {
            /*
                r0 = 0
                r301 = r0
                android.content.Context r0 = com.kdweibo.android.util.d.access$000()     // Catch: Exception -> 0x001f
                r302 = r0
                java.lang.String r0 = "connectivity"
                r303 = r0
                r0 = r302
                r1 = r303
                java.lang.Object r0 = r0.getSystemService(r1)     // Catch: Exception -> 0x001f
                r302 = r0
                r0 = r302
                android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch: Exception -> 0x001f
                r302 = r0
                r0 = r302
                if (r0 == 0) goto L_0x001d
                r0 = r302
                android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()     // Catch: Exception -> 0x001f
                r301 = r0
            L_0x001d:
                r0 = r301
                return r0
            L_0x001f:
                r0.printStackTrace()
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kdweibo.android.util.d.c.apM():android.net.NetworkInfo");
        }

        private static boolean f(NetworkInfo networkInfo) {
            boolean z = true;
            if (networkInfo == null || !networkInfo.isConnected() || networkInfo.getType() != 1) {
                z = false;
            }
            return z;
        }

        public static String getDeviceModel() {
            return Build.MODEL;
        }

        public static String getVersionName() {
            try {
                return ELinkToast.context.getPackageManager().getPackageInfo(ELinkToast.context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException unused) {
                return "";
            }
        }

        public static boolean ub() {
            NetworkInfo apM = apM();
            return apM != null && apM.isConnected();
        }
    }


    public static void S(Context context2, String str) {
        if (str != null) {
            try {
                ClipboardManager clipboardManager = (ClipboardManager) context2.getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboardManager != null) {
                    clipboardManager.setText(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public static void V(Context context2, String str) {
        try {
            context2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
    }

    private static Activity apH() {
        WeakReference weakReference = dzY;
        if (weakReference != null) {
            return (Activity) weakReference.get();
        }
        return null;
    }


    public static String b(int i, Object... objArr) {
        Activity apH = apH();
        return (apH != null ? apH.getResources() : context.getResources()).getString(i, objArr);
    }






//    public static boolean cG(Context context2) {
//        LocationManager locationManager = (LocationManager) context2.getSystemService("location");
//        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled(TencentLocation.NETWORK_PROVIDER);
//    }

    public static Object cast(Object obj) {
        return obj;
    }

    public static void d(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i > 0) {
            layoutParams.width = i;
        }
        if (i2 > 0) {
            layoutParams.height = i2;
        }
        view.setLayoutParams(layoutParams);
    }

    public static boolean e(Collection collection) {
        return collection == null || collection.size() <= 0;
    }

    public static long getAvailableInternalMemorySize() {
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT < 18) {
            j2 = statFs.getBlockSize();
            j = statFs.getAvailableBlocks();
        } else {
            j2 = statFs.getBlockSizeLong();
            j = statFs.getAvailableBlocksLong();
        }
        return j * j2;
    }

    public static int getColor(int i) {
        return context.getResources().getColor(i);
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.String, java.lang.Exception] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static String getPackageName() {
        /*
            android.content.Context r0 = com.kdweibo.android.util.d.context     // Catch: Exception -> 0x0021
            r301 = r0
            r0 = r301
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: Exception -> 0x0021
            r301 = r0
            android.content.Context r0 = com.kdweibo.android.util.d.context     // Catch: Exception -> 0x0021
            r302 = r0
            r0 = r302
            java.lang.String r0 = r0.getPackageName()     // Catch: Exception -> 0x0021
            r302 = r0
            r0 = r301
            r1 = r302
            r2 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r1, r2)     // Catch: Exception -> 0x0021
            r301 = r0
            r0 = r301
            java.lang.String r0 = r0.packageName     // Catch: Exception -> 0x0021
            r301 = r0
            goto L_0x0027
        L_0x0021:
            r0.printStackTrace()
            java.lang.String r0 = ""
            r301 = r0
        L_0x0027:
            r0 = r301
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kdweibo.android.util.d.getPackageName():java.lang.String");
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").replaceAll("_", "");
    }




    public static boolean isGpsEnabled(Context context2) {
        LocationManager locationManager = (LocationManager) context2.getSystemService("location");
        return locationManager != null && locationManager.isProviderEnabled("gps");
    }



    public static boolean isServiceRunning(Context context2, String str) {
        ActivityManager activityManager = (ActivityManager) context2.getSystemService("activity");
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager != null ? activityManager.getRunningServices((-1) >>> 1) : null;
        boolean z = false;
        if (runningServices != null && runningServices.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= runningServices.size()) {
                    break;
                } else if (TextUtils.equals(runningServices.get(i).service.getClassName(), str)) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        return z;
    }

    public static long lN(String str) {
        long j = -1;
        try {
            if (!TextUtils.isEmpty(str)) {
                j = Long.parseLong(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static boolean lO(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null;
    }


    public static void n(Cursor cursor) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception unused) {
            }
        }
    }

    public static String np(int i) {
        Activity apH = apH();
        return (apH != null ? apH.getResources() : context.getResources()).getString(i);
    }
}
