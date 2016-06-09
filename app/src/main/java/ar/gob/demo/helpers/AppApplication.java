package ar.gob.demo.helpers;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class AppApplication extends Application {

    public static final String TAG = AppApplication.class.getSimpleName();
    private static AppApplication mInstance;
    public static DialogHelper dialogHelper;
    private static TagManagerHelper tagManagerHelper;

    public static synchronized AppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        Fabric.with(this, new Crashlytics());
        StorageHelper storageHelper = StorageHelper.getInstance();
        storageHelper.init(this);
        dialogHelper = new DialogHelper();
        tagManagerHelper = new TagManagerHelper();
    }

}



