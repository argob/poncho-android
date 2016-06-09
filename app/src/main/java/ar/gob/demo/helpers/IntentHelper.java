package ar.gob.demo.helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.io.Serializable;
import ar.gob.demo.R;
import ar.gob.demo.ui.activities.AboutActivity;
import ar.gob.demo.ui.activities.HomeActivity;
import ar.gob.demo.ui.activities.OnBoardingActivity;

public class IntentHelper {

    private static AppApplication appInstance = AppApplication.getInstance();

    public static void goToOnBoardingActivity(Activity activity, boolean finish) {
        launchIntent(activity, OnBoardingActivity.class, finish);
    }

    public static void goToHomeActivity(Activity activity) {
        launchIntentAndFinish(activity, HomeActivity.class);
    }

    public static void goToAboutActivity(Activity activity) {
        launchIntent(activity, AboutActivity.class, false);
    }

    public static void goToDeviceSettings(Activity activity) {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + DeviceInfoHelper.getPackageName()));
        activity.startActivity(intent);
    }

    public static void shareURL(Activity activity, String url) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static void goToSmsSend(Activity activity, String number, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + Uri.encode(number)));
        intent.putExtra("sms_body", message);
        activity.startActivity(intent);
    }

    public static void shareItem(Activity activity, String extraText) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, extraText);
        activity.startActivity(Intent.createChooser(shareIntent, activity.getString(R.string.share_chooser)));
    }

    private static <T> Intent launchIntent(Activity activity, Class<T> className, boolean finish) {
        return launchIntent(activity, className, finish, null);
    }

    private static <T> Intent launchIntentAndFinish(Activity activity, Class<T> className) {
        return launchIntent(activity, className, true, null);
    }

    private static <T> Intent launchIntentAndFinish(Activity activity, Class<T> className, Bundle params) {
        return launchIntent(activity, className, true, params);
    }

    private static <T> Intent launchIntent(Activity activity, Class<T> className, boolean finish, Bundle params, int requestCode) {
        Intent intent = new Intent(appInstance, className);
        if (params != null) {
            intent.putExtras(params);
        }
        activity.startActivityForResult(intent, requestCode);

        if (finish) {
            activity.finish();
        }
        return intent;
    }

    private static <T> Intent launchIntent(Activity activity, Class<T> className, boolean finish, Bundle params) {
        Intent intent = new Intent(appInstance, className);
        if (params != null) {
            intent.putExtras(params);
        }
        activity.startActivity(intent);

        if (finish) {
            activity.finish();
        }
        return intent;
    }

    private static <T> Intent launchIntent(Activity activity, Class<T> className, boolean finish, String key, Serializable serializable) {
        Intent intent = new Intent(appInstance, className);
        if (key != null) {
            intent.putExtra(key, serializable);
        }
        activity.startActivity(intent);

        if (finish) {
            activity.finish();
        }
        return intent;
    }

}
