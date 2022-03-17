package com.google.zxing;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * public class ap {
 *     public static boolean aC(String str) {
 *         return str == null || str.trim().length() == 0;
 *     }
 */
public class ELinkClassI {
    public static boolean aC(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String cX(long j) {
        String str;
        long j2 = j / 1024;
        if (j2 < 1) {
            str = j + " B";
        } else if (j2 / 1024 >= 1) {
            double d = (j2 * 1.0d) / 1024.0d;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            str = decimalFormat.format(d) + " MB";
        } else {
            str = j2 + " KB";
        }
        return str;
    }

    public static boolean e(char c) {
        Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
        return of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || of == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || of == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || of == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    public static boolean isHttpUrl(String str) {
        boolean z = false;
        if (mG(str)) {
            return false;
        }
        if (str.startsWith("http:") || str.startsWith("https:") || str.startsWith("HTTP:") || str.startsWith("HTTPS:")) {
            z = true;
        }
        return z;
    }

    public static boolean isNull(String str) {
        if (!mI(str)) {
            return true;
        }
        return str.equalsIgnoreCase("null");
    }

    public static boolean isNumeric(String str) {
        if (mG(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static int kf(String str) {
        char[] charArray;
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c >= 913 && c <= ((char) (-27))) {
                i += 2;
            } else if (c >= 0 && c <= 255) {
                i++;
            }
        }
        return i;
    }

    public static String mF(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

    public static boolean mG(String str) {
        return aC(str) || "null".equals(str);
    }

    public static String mH(String str) {
        if (mG(str)) {
            str = "";
        }
        return str;
    }

    public static boolean mI(String str) {
        return str != null && str.length() > 0;
    }

    public static int mJ(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    public static double mK(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        return Double.parseDouble(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v34, types: [double] */
    /* JADX WARN: Type inference failed for: r306v1, types: [double] */
    /* JADX WARN: Type inference failed for: r306v2 */
    /* JADX WARN: Type inference failed for: r306v4 */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static String mL(String r301) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kdweibo.android.util.ap.mL(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v34, types: [double] */
    /* JADX WARN: Type inference failed for: r306v1, types: [double] */
    /* JADX WARN: Type inference failed for: r306v2 */
    /* JADX WARN: Type inference failed for: r306v4 */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static String mM(String r301) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kdweibo.android.util.ap.mM(java.lang.String):java.lang.String");
    }

    public static boolean mN(String str) {
        String replace = str.replace(" ", "");
        for (int i = 0; i < replace.length(); i++) {
            if ((replace.charAt(i) < 'A' || replace.charAt(i) > 'Z') && (replace.charAt(i) < 'a' || replace.charAt(i) > 'z')) {
                return false;
            }
        }
        return true;
    }

    public static String mO(String str) {
        if (str != null && str.length() >= 2) {
            str = str.replaceAll("\\s\\s+$", " ");
        }
        return str;
    }

    public static boolean mP(String str) {
        if (aC(str)) {
            return false;
        }
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            if (!Pattern.matches("[一-龥]", str.substring(i, i2))) {
                return false;
            }
            i = i2;
        }
        return true;
    }

    public static boolean mQ(String str) {
        boolean z = false;
        if (mG(str)) {
            return false;
        }
        if (str.startsWith("http:") || str.startsWith("https:") || str.startsWith("HTTP:") || str.startsWith("HTTPS:") || str.startsWith("ftp:") || str.startsWith("FTP:")) {
            z = true;
        }
        return z;
    }

    public static boolean mR(String str) {
        if (aC(str)) {
            return false;
        }
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            if (Pattern.matches("[一-龥]", str.substring(i, i2))) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    public static boolean mS(String str) {
        if (aC(str)) {
            return false;
        }
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            if (!Pattern.matches("[一-龥]", str.substring(i, i2))) {
                return false;
            }
            i = i2;
        }
        return true;
    }
}
