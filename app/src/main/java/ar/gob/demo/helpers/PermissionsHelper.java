package ar.gob.demo.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by aestevez
 */
public class PermissionsHelper {

    private Activity activity;

    public PermissionsHelper(Activity ctx) {
        this.activity = ctx;
    }

    /*
        Metodo que recibe como parametro un Manifest.permission como parametro, del permiso que se desea chequear
     */
    public static boolean checkPermission(String perm) {
        if (DeviceInfoHelper.isMarshMallow()) {
            int result = ContextCompat.checkSelfPermission(AppApplication.getInstance().getApplicationContext(), perm);
            return (result == PackageManager.PERMISSION_GRANTED) ? true : false;
        } else {
            return true;
        }

    }

    /*
        Metodo que recibe un requestCode para chequear si tiene permisos
     */
    public static boolean checkPermission(int requestCode) {
        if (DeviceInfoHelper.isMarshMallow()) {
            Permission permission = Permission.get(requestCode);
            int result = ContextCompat.checkSelfPermission(AppApplication.getInstance().getApplicationContext(), permission.getManifest());
            return (result == PackageManager.PERMISSION_GRANTED) ? true : false;
        } else {
            return true;
        }
    }

    public static boolean checkPermissionForReadCalendar() {
        return checkPermission(Permission.READ_CALENDAR.getRequestCode());
    }

    public static boolean checkPermissionForWriteCalendar() {
        return checkPermission(Permission.WRITE_CALENDAR.getRequestCode());
    }

    public static boolean checkPermissionForCamera() {
        return checkPermission(Permission.WRITE_CALENDAR.getRequestCode());
    }

    public static boolean checkPermissionForReadContacts() {
        return checkPermission(Permission.READ_CONTACTS.getRequestCode());
    }

    public static boolean checkPermissionForWriteContacts() {
        return checkPermission(Permission.WRITE_CONTACTS.getRequestCode());
    }

    public static boolean checkPermissionForFineLocation() {
        return checkPermission(Permission.WRITE_CONTACTS.getRequestCode());
    }

    public static boolean checkPermissionForCoarseLocation() {
        return checkPermission(Permission.ACCESS_COARSE_LOCATION.getRequestCode());
    }

    public static boolean checkPermissionForMicrophone() {
        return checkPermission(Permission.RECORD_AUDIO.getRequestCode());
    }

    public static boolean checkPermissionForReadPhoneState() {
        return checkPermission(Permission.READ_PHONE_STATE.getRequestCode());
    }

    public static boolean checkPermissionForCallPhone() {
        return checkPermission(Permission.CALL_PHONE.getRequestCode());
    }

    public static boolean checkPermissionForReadCallLog() {
        return checkPermission(Permission.READ_CALL_LOG.getRequestCode());
    }

    public static boolean checkPermissionForWriteCallLog() {
        return checkPermission(Permission.WRITE_CALL_LOG.getRequestCode());
    }

    public static boolean checkPermissionForAddVoiceMail() {
        return checkPermission(Permission.ADD_VOICEMAIL.getRequestCode());
    }

    public static boolean checkPermissionForUseSip() {
        return checkPermission(Permission.USE_SIP.getRequestCode());
    }

    public static boolean checkPermissionForProcessOutgoingCalls() {
        return checkPermission(Permission.PROCESS_OUTGOING_CALLS.getRequestCode());
    }

    public static boolean checkPermissionForBodySensors() {
        return checkPermission(Permission.BODY_SENSORS.getRequestCode());
    }

    public static boolean checkPermissionForUseFingerprint() {
        return checkPermission(Permission.USE_FINGERPRINT.getRequestCode());
    }

    public static boolean checkPermissionForSendSMS() {
        return checkPermission(Permission.SEND_SMS.getRequestCode());
    }

    public static boolean checkPermissionForReceiveSMS() {
        return checkPermission(Permission.RECEIVE_SMS.getRequestCode());
    }

    public static boolean checkPermissionForReadSMS() {
        return checkPermission(Permission.READ_SMS.getRequestCode());
    }

    public static boolean checkPermissionForReceiveWapPush() {
        return checkPermission(Permission.RECEIVE_WAP_PUSH.getRequestCode());
    }

    public static boolean checkPermissionForReceiveMMS() {
        return checkPermission(Permission.RECEIVE_MMS.getRequestCode());
    }

    public static boolean checkPermissionForReadCellBroadcasts() {
        return checkPermission(Permission.READ_CELL_BROADCASTS.getRequestCode());
    }

    public static boolean checkPermissionForReadHistoryBookmarks() {
        return checkPermission(Permission.READ_HISTORY_BOOKMARKS.getRequestCode());
    }

    public static boolean checkPermissionForWriteHistoryBookmarks() {
        return checkPermission(Permission.WRITE_HISTORY_BOOKMARKS.getRequestCode());
    }

    public static boolean checkPermissionForWriteExternalStorage() {
        return checkPermission(Permission.WRITE_EXTERNAL_STORAGE.getRequestCode());
    }

    public static boolean checkPermissionForReadExternalStorage() {
        return checkPermission(Permission.READ_EXTERNAL_STORAGE.getRequestCode());
    }

    public void requestPermission(@NonNull int intRequestCode) {
        Permission permission = Permission.get(intRequestCode);
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission.getManifest())) {
            //Toast.makeText(activity, "Se necesitan permisos para esta accion. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            showSnackBar(permission.getMessageNoPermission());
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission.getManifest()}, intRequestCode);
        }
    }

    public boolean handlePermission(Permission permission) {
        if (checkPermission(permission.getRequestCode())) {
            return true;
        } else {
            showSnackBar(permission.getMessageNoPermission());
            return false;
        }
    }

    public boolean handlePermissionRequest(int requestCode, Permission permission) {
        Permission permissionWriteExternalStorage = Permission.WRITE_EXTERNAL_STORAGE;
        Permission permissionSendSms = Permission.SEND_SMS;
        Permission permissionReadCalendar = Permission.READ_CALENDAR;
        Permission permissionReadContacts = Permission.READ_CONTACTS;

        if (requestCode == permissionWriteExternalStorage.getRequestCode()) {
            return handlePermission(permissionWriteExternalStorage);
        }
        if (requestCode == permissionSendSms.getRequestCode()) {
            return handlePermission(permissionSendSms);
        }
        if (requestCode == permissionReadCalendar.getRequestCode()) {
            return handlePermission(permissionReadCalendar);
        }
        if (requestCode == permissionReadContacts.getRequestCode()) {
            return handlePermission(permissionReadContacts);
        }

        return false;
    }

    public void requestPermission(@NonNull int[] intRequestCode) {
        Permission permission = Permission.get(intRequestCode[0]);
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission.getManifest())) {
            //Toast.makeText(activity, "Se necesitan permisos para esta accion. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            showSnackBar();
        } else {
            //List<String> params = null;
            String[] permisos = new String[27];
            for (int intCode : intRequestCode) {
                Permission permiso = Permission.get(intCode);
                permisos[intCode] = permiso.getManifest();
            }
            ActivityCompat.requestPermissions(activity, permisos, Permission.MULTIPLE_PERMISSIONS.getRequestCode());
        }
    }

    public void requestPermission(@NonNull String[] perms) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, perms[0])) {
            showSnackBar();
            //Toast.makeText(activity, "Se necesitan permisos para esta accion. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, perms, Permission.MULTIPLE_PERMISSIONS.getRequestCode());
        }
    }

    public void requestPermissionForReadCalendar() {
        requestPermission(Permission.READ_CALENDAR.getRequestCode());
    }

    public void requestPermissionForWriteCalendar() {
        requestPermission(Permission.WRITE_CALENDAR.getRequestCode());
    }

    public void requestPermissionForCamera() {
        requestPermission(Permission.CAMERA.getRequestCode());
    }

    public void requestPermissionForReadContacts() {
        requestPermission(Permission.READ_CONTACTS.getRequestCode());
    }

    public void requestPermissionForWriteContacts() {
        requestPermission(Permission.WRITE_CONTACTS.getRequestCode());
    }

    public void requestPermissionForFineLocation() {
        requestPermission(Permission.ACCESS_FINE_LOCATION.getRequestCode());
    }

    public void requestPermissionForCoarseLocation() {
        requestPermission(Permission.ACCESS_COARSE_LOCATION.getRequestCode());
    }

    public void requestPermissionForMicrophone() {
        requestPermission(Permission.RECORD_AUDIO.getRequestCode());
    }

    public void requestPermissionForReadPhoneState() {
        requestPermission(Permission.READ_PHONE_STATE.getRequestCode());
    }

    public void requestPermissionForCallPhone() {
        requestPermission(Permission.CALL_PHONE.getRequestCode());
    }

    public void requestPermissionForReadCallLog() {
        requestPermission(Permission.READ_CALL_LOG.getRequestCode());
    }

    public void requestPermissionForWriteCallLog() {
        requestPermission(Permission.WRITE_CALL_LOG.getRequestCode());
    }

    public void requestPermissionForAddVoiceMail() {
        requestPermission(Permission.ADD_VOICEMAIL.getRequestCode());
    }

    public void requestPermissionForUseSip() {
        requestPermission(Permission.USE_SIP.getRequestCode());
    }

    public void requestPermissionForProcessOutgoingCalls() {
        requestPermission(Permission.PROCESS_OUTGOING_CALLS.getRequestCode());
    }

    public void requestPermissionForBodySensors() {
        requestPermission(Permission.BODY_SENSORS.getRequestCode());
    }

    public void requestPermissionForUseFingerprints() {
        requestPermission(Permission.USE_FINGERPRINT.getRequestCode());
    }

    public void requestPermissionForSendSms() {
        requestPermission(Permission.SEND_SMS.getRequestCode());
    }

    public void requestPermissionForReceiveSms() {
        requestPermission(Permission.RECEIVE_SMS.getRequestCode());
    }

    public void requestPermissionForReadSms() {
        requestPermission(Permission.READ_SMS.getRequestCode());
    }

    public void requestPermissionForReceiveWapPush() {
        requestPermission(Permission.RECEIVE_WAP_PUSH.getRequestCode());
    }

    public void requestPermissionForReceiveMms() {
        requestPermission(Permission.RECEIVE_MMS.getRequestCode());
    }

    public void requestPermissionForReadCellBroadcasts() {
        requestPermission(Permission.READ_CELL_BROADCASTS.getRequestCode());
    }

    public void requestPermissionForReadHistoryBookmarks() {
        requestPermission(Permission.READ_HISTORY_BOOKMARKS.getRequestCode());
    }

    public void requestPermissionForWriteHistoryBookmarks() {
        requestPermission(Permission.WRITE_HISTORY_BOOKMARKS.getRequestCode());
    }

    public void requestPermissionForWriteExternalStorage() {
        requestPermission(Permission.WRITE_EXTERNAL_STORAGE.getRequestCode());
    }

    public void requestPermissionForReadExternalStorage() {
        requestPermission(Permission.READ_EXTERNAL_STORAGE.getRequestCode());
    }

    public void showSnackBar() {
        showSnackBar("No hay permisos suficientes");
    }

    public void showSnackBar(String message) {
        Snackbar.make(activity.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_LONG)
                .setAction("Configuración", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openSettings();
                    }
                })
                .setDuration(Snackbar.LENGTH_INDEFINITE)
                .show();
    }

    public void openSettings() {
        IntentHelper.goToDeviceSettings(activity);
    }

    /**
     * Devuelve true si se tiene acceso a todos los permisos
     * Siempre devuelve true para versiones anteriores a Marshmallow
     */
    public static boolean hasSelfPermissions(String[] permissions) {
        if (!DeviceInfoHelper.isMarshMallow()) {
            return true;
        }

        for (String permission : permissions) {
            if (!checkPermission(permission)) {
                return false;
            }
        }
        return true;
    }
}