package ar.gob.demo.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import ar.gob.demo.helpers.PermissionsHelper;

public abstract class BaseActivity extends AppCompatActivity {

    private final String TAG = BaseActivity.class.getName();
    protected Activity activity;
    protected ProgressDialog pDialog;
    protected PermissionsHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        permissionHelper = new PermissionsHelper(this);
    }

    protected void showProgressDialog(int message) {
        try {
            pDialog = new ProgressDialog(activity);
            pDialog.setMessage(getResources().getString(message));
            pDialog.setCancelable(true);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
        }
    }

    protected void dismissProgressDialog() {
        try {
            if (pDialog != null) {
                pDialog.dismiss();
            }
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && this.getCurrentFocus() != null && this.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public PermissionsHelper getPermissionHelper() {
        return permissionHelper;
    }

}