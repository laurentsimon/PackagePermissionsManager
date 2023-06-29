package com.ampaschal.google.apps;

import com.ampaschal.google.PermissionsManager;
import com.ampaschal.google.TestHelper;
import com.ampaschal.google.enums.ProfileKey;

public class AllPermissionsApp {

    public static void main(String[] args) {

        TestHelper.logTime(ProfileKey.MAIN_CALLED);

        WordCountApp.performFileCount();
        ReadNetworkApp.performNetworkCount();
        ProcessExecApp.performShellExec();

        TestHelper.logTime(ProfileKey.MAIN_EXITING);
    }
}
