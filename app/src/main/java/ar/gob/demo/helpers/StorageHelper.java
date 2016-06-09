package ar.gob.demo.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sergio on 25/4/16.
 */
public class StorageHelper {

    private static final String TAG = "StorageHelper";
    private static final String SHARED_PREFERENCES = "appPreferences";
    private static StorageHelper instance;
    private Context ctx;
    private SharedPreferences sharedPreferences;

    public static synchronized StorageHelper getInstance() {
        if (instance == null) {
            instance = new StorageHelper();
        }
        return instance;
    }

    public void init(Context ctx) {
        this.ctx = ctx;
        if (sharedPreferences == null) {
            sharedPreferences = ctx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
    }

    public Context getContext() {
        return ctx;
    }

    public String getPreferences(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putPreferences(String key, String value) {
        if (key != null && key.trim().length() > 0 && value != null && value.trim().length() > 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public long getLongPreferences(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void putLongPreferences(String key, long value) {
        if (key != null && key.trim().length() > 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }


    public boolean getBoolPreferences(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void putBoolPreferences(String key, boolean value) {
        if (key != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void remove(String key) {
        if (key != null && key.trim().length() > 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.commit();
        }
    }

}
