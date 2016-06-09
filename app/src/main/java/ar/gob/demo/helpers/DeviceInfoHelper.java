package ar.gob.demo.helpers;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;

public class DeviceInfoHelper {

    private static Context context = AppApplication.getInstance();

    public static String getApplicationInfo() {
        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n", getCountry(), getBrandInfo(), getModelInfo(), getDeviceInfo(), getVersionInfo(), getLocale(context));
    }

    public static String getCountry() {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return String.format("Country: %s", mTelephonyMgr.getNetworkCountryIso());
    }

    public static String getBrandInfo() {
        return String.format("Brand: %s", Build.BRAND);
    }

    public static String getModelInfo() {
        return String.format("Model: %s", Build.MODEL);
    }

    public static String getDeviceInfo() {
        return String.format("Device: %s", Build.DEVICE);
    }

    public static String getVersionInfo() {
        String versionInfo = "";
        try {
            versionInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format("%s", versionInfo);
    }

    public static String getLocale(Context context) {
        return String.format("Locale: %s", context.getResources().getConfiguration().locale.getDisplayName());
    }

    /*
     * Devuelve true si la Version de Android es M o superior
     */
    public static boolean isMarshMallow() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }

    public static String getPackageName() {
        return context.getPackageName();
    }

}