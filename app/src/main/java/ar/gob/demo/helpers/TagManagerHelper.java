package ar.gob.demo.helpers;

import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;

public class TagManagerHelper {

    private static final String CONTAINER_ID = "GTM-XXXX";
    private static final int CONTAINER_ID_RES = 0;
    private TagManager tagManager;
    private DataLayer dataLayer;

    public TagManagerHelper() {
        this.tagManager = TagManager.getInstance(AppApplication.getInstance());
        this.dataLayer = tagManager.getDataLayer();
        setVerboseLoggingEnabled(true);
    }

    public void setVerboseLoggingEnabled(boolean logStatus) {
        tagManager.setVerboseLoggingEnabled(logStatus);
    }

    public void pushEvent(String eventName, String mapKey, String mapValue) {
        dataLayer.pushEvent(eventName, DataLayer.mapOf(mapKey, mapValue));
    }

}
