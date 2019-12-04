package com.example.food_oasis_app;

/**
 * This class sets the UUID after someone logs in  and that UUID is  available throughout
 * the whole user's "session"
 */

public class UUID {
    private String uuid;
    private String vendorKey;
    private static UUID instance = null;

    public static UUID getInstance() {
        if (instance == null) {
            instance = new UUID(); // create the one instance.
        }
        return instance;
    }

    public UUID() {
    }

    public void setuuid(String uuid) {
        this.uuid = uuid;
    }

    public String getuuid() {
        return this.uuid;
    }

    public void setVendorKey (String key)  {
        this.vendorKey = key;
    }

    public String getVendorKey() {
        return vendorKey;
    }


}