package ar.gob.demo.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import ar.gob.demo.R;

@SuppressLint("NewApi")
public class DialogHelper {

    public void showNoSDCardDialog(final Activity origin) {
        showMessage(origin, origin.getResources().getString(R.string.no_sd_card_msg));
    }

    public void showMessage(final Activity origin, final String msj) {
        showMessage(origin, msj, (OnClickListener) null);
    }

    public void showMessage(final Activity origin, final String msj, final OnClickListener listener) {
        showMessage(origin, origin.getResources().getString(R.string.app_name), msj, listener);
    }

    public void showMessage(final Activity origin, final String title, final String msj) {
        showMessage(origin, title, msj, null);
    }

    public void showMessage(final Activity origin, final String title, final String msj, final boolean iconStatus, final OnClickListener positiveListener) {
        origin.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                if (title != null) {
                    builder.setTitle(title);
                } else {
                    builder.setTitle(origin.getResources().getString(R.string.app_name));
                }
                if ((msj == null) || (msj.trim().length() == 0)) {
                    builder.setMessage(origin.getResources().getString(R.string.http_exception_msg));
                } else {
                    builder.setMessage(msj);
                }
                builder.setPositiveButton(origin.getResources().getString(R.string.accept), positiveListener);

                builder.show();
            }
        });
    }

    public void showMessage(final Activity origin, final String title, final String msj, final OnClickListener positiveListener) {
        origin.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                if (title != null) {
                    builder.setTitle(title);
                } else {
                    builder.setTitle(origin.getResources().getString(R.string.app_name));
                }
                if ((msj == null) || (msj.trim().length() == 0)) {
                    builder.setMessage(origin.getResources().getString(R.string.http_exception_msg));
                } else {
                    builder.setMessage(msj);
                }
                builder.setPositiveButton(origin.getResources().getString(R.string.accept), positiveListener);

                builder.show();
            }
        });
    }

    public void showMessageNotCancelable(final Activity origin, final String title, final String msj, final OnClickListener positiveListener) {
        origin.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                builder.setCancelable(false);
                if (title != null) {
                    builder.setTitle(title);
                } else {
                    builder.setTitle(origin.getResources().getString(R.string.app_name));
                }
                if ((msj == null) || (msj.trim().length() == 0)) {
                    builder.setMessage(origin.getResources().getString(R.string.http_exception_msg));
                } else {
                    builder.setMessage(msj);
                }
                builder.setPositiveButton(origin.getResources().getString(R.string.accept), positiveListener);

                builder.show();
            }
        });
    }

    public void showDialog(final Activity origin, final String title, final String positive, final OnClickListener positiveListener,
                           final String negative, final OnClickListener negativeListener, final boolean cancelListener) {
        origin.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                builder.setTitle(title);
                builder.setCancelable(cancelListener);
                builder.setPositiveButton(positive, positiveListener);
                builder.setNegativeButton(negative, negativeListener);
                AlertDialog dialog = builder.show();
                TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
                if (messageText != null) {
                    messageText.setGravity(Gravity.LEFT);
                }
            }
        });
    }

    public void showDialog(final Activity origin, final String title, final String msj, final String positive,
                           final OnClickListener positiveListener, final String negative, final OnClickListener negativeListener) {
        origin.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                builder.setTitle(title);
                builder.setMessage(msj);
                builder.setCancelable(false);
                builder.setPositiveButton(positive, positiveListener);
                builder.setNegativeButton(negative, negativeListener);
                AlertDialog dialog = builder.show();
                TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
                if (messageText != null) {
                    messageText.setGravity(Gravity.LEFT);
                }
            }
        });
    }

    public void showDialog(final Activity origin, final String title, final String msj, final String positive,
                           final OnClickListener positiveListener, final String negative, final OnClickListener negativeListener,
                           final boolean cancelListener) {
        origin.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                builder.setTitle(title);
                builder.setMessage(msj);
                builder.setCancelable(cancelListener);
                builder.setPositiveButton(positive, positiveListener);
                builder.setNegativeButton(negative, negativeListener);
                AlertDialog dialog = builder.show();
                TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
                if (messageText != null) {
                    messageText.setGravity(Gravity.LEFT);
                }
            }
        });
    }

    public AlertDialog.Builder showRating(final Activity origin, int customLayout, final String positive,
                                          final OnClickListener positiveListener, final String negative, final OnClickListener negativeListener) {
        LayoutInflater inflater = origin.getLayoutInflater();
        final View dialogLayout = inflater.inflate(customLayout, null);

       // final RatingBar ratingBar = (RatingBar) dialogLayout.findViewById(R.id.ratingBar);
        final RatingBar ratingBar = (RatingBar) dialogLayout.findViewById(R.id.ratingBar);

        AlertDialog.Builder builder = getBuilder(origin);
        builder.setView(dialogLayout);

        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);

        return builder;
    }

    public void showOptionDialog(final Activity origin, final String title, final String[] items, final OnClickListener listener,
                                 final OnCancelListener onCancelListener, final boolean cancelable) {
        origin.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = getBuilder(origin);
                if (title != null) {
                    builder.setTitle(title);
                } else {
                    builder.setTitle(origin.getResources().getString(R.string.app_name));
                }
                builder.setCancelable(cancelable);
                builder.setItems(items, listener);
                if (onCancelListener == null) {
                    builder.setCancelable(false);
                } else {
                    builder.setOnCancelListener(onCancelListener);
                }
                builder.show();
            }
        });
    }

    public void showOptionDialog(final Activity origin, final String[] items, final OnClickListener listener, final boolean cancelable) {
        showOptionDialog(origin, null, items, listener, null, cancelable);
    }

    public void showOptionDialog(final Activity origin, String title, String[] items, OnClickListener listener, final boolean cancelable) {
        showOptionDialog(origin, title, items, listener, null, cancelable);
    }

    private AlertDialog.Builder getBuilder(Activity origin) {
        if (Build.VERSION.SDK_INT >= 11) {
            return new AlertDialog.Builder(origin);
        }
        return new AlertDialog.Builder(origin);
    }

}