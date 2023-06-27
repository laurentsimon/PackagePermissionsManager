package com.ampaschal.google.enums;

public enum ResourceType {
    FS (0),
    Net (1),
    RUNTIME(2),
    ;

    private final int id;

    ResourceType(int id) {
        this.id = id;
    }
    public static ResourceType getResourceType(int id) {
        for (ResourceType res: values()) {
            if (res.id == id) {
                return res;
            }
        }

        return null;
    }

    public static int getResourceTypeId(ResourceType resourceType) {
        for (ResourceType res: values()) {
            if (res == resourceType) {
                return res.id;
            }
        }

        return -1;
    }
}
