package com.ampaschal.google;

import com.ampaschal.google.enums.ResourceOp;
import com.ampaschal.google.enums.ResourceType;

import java.util.Set;

public interface PermissionsCallback {

    void onPermissionRequested(String subject, int subjectPathSize, ResourceType resourceType, ResourceOp resourceOp, String resourceItem);

    void onPermissionFailure(Set<String> subjectPaths, ResourceType resourceType, ResourceOp resourceOp, String resourceItem);

}
