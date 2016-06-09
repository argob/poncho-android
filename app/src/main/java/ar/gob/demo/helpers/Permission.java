package ar.gob.demo.helpers;

import android.Manifest;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aestevez on 26/05/16.
 */
public enum  Permission {

    // Grupo Calendar
    READ_CALENDAR(Manifest.permission.READ_CALENDAR, "Lectura en Calendario", 0),
    WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR, "Escritura en Calendario", 1),

    // Grupo Camara.
    CAMERA(Manifest.permission.CAMERA, "Uso de Camara", 2),

    // Grupo Contactos
    READ_CONTACTS(Manifest.permission.READ_CONTACTS, "Lectura de Contactos", 3),
    WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS, "Escritura de Contactos", 4),

    // Grupo Geolocalizacion
    ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION, "GeoLocalizacion Precisa", 5),
    ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION, "GeoLocalizacion Estimada", 6),

    // Grupo Microfono
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO, "Microfono", 7),

    // Grupo Telefono
    READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE, "Estado del dispositivo", 8),
    CALL_PHONE(Manifest.permission.CALL_PHONE, "Llamadas", 9),
    READ_CALL_LOG(Manifest.permission.READ_CALL_LOG, "Lectura de Log de Llamadas", 10),
    WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG, "Escritura de Log de Llamadas", 11),
    ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL, "Buzon de Correos", 12),
    USE_SIP(Manifest.permission.USE_SIP, "Servicio IP para llamadas por Internet.", 13),
    PROCESS_OUTGOING_CALLS(Manifest.permission.PROCESS_OUTGOING_CALLS, "Procesamiento de Llamadas Salientes", 14),


    // Grupo Sensores
    BODY_SENSORS(Manifest.permission.BODY_SENSORS, "Sensor de Movimiento", 15),
    USE_FINGERPRINT(Manifest.permission.USE_FINGERPRINT, "Huella Dactilar", 16),

    // Grupo SMS
    SEND_SMS(Manifest.permission.SEND_SMS, "Envío de SMS", 17),
    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS, "Recepción de SMS", 18),
    READ_SMS(Manifest.permission.READ_SMS, "Lectura de SMS", 19),
    RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH, "Recepcion PUSH WAP", 20),
    RECEIVE_MMS(Manifest.permission.RECEIVE_WAP_PUSH, "Recepcion MMS", 21),
    READ_CELL_BROADCASTS("android.permission.READ_CELL_BROADCASTS", "Lectura Difusion por Celda", 22),

    // Favoritos
    READ_HISTORY_BOOKMARKS("com.android.browser.permission.READ_HISTORY_BOOKMARKS", "Lectura Historial Favoritos", 23),
    WRITE_HISTORY_BOOKMARKS("com.android.browser.permission.WRITE_HISTORY_BOOKMARKS", "Escritura Historial Favoritos", 24),

    //Grupo Almacenamiento
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Escritura en Disco", 25),
    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE, "Lectura en Disco", 26),

    //Request Code para solicitar Multiples permisos
    MULTIPLE_PERMISSIONS("Multiples Permisos", "Multiples Permisos", 200);


    private String strManifest;
    private int intRequestCode;
    private String strToString;


    private static final Map<Integer, Permission> lookup
            = new HashMap<Integer, Permission>();

    private Permission(String manifest, String toString, int requestCode) {
        strManifest = manifest;
        intRequestCode = requestCode;
        strToString = toString;
    }


    public String getManifest() {
        return strManifest;
    }

    public int getRequestCode() {
        return intRequestCode;
    }

    public String toString() {
        return strToString;
    }

    public String getMessageNoPermission() {
        return "Se necesitan permisos para " + toString() + ". Por favor otorgue permiso en la configuración de la APP";
    }

    public String getMessagePermission() {
        return "Ahora la app cuenta con permisos para " + toString();
    }

    static {
        for (Permission s : EnumSet.allOf(Permission.class))
            lookup.put(s.getRequestCode(), s);
    }

    public static Permission get(int intRequestCode) {
        return lookup.get(intRequestCode);
    }
}