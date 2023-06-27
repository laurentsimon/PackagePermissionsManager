package com.ampaschal.google;

import java.util.HashMap;
import java.util.Map;

public class PermissionsObject {

    private Map<String, PermissionObject> permissions = new HashMap<>();

    public void setPermissions(Map<String, PermissionObject> permissions) {
        this.permissions = permissions;
    }

    public Map<String, PermissionObject> getPermissions() {
        return this.permissions;
    }

}
