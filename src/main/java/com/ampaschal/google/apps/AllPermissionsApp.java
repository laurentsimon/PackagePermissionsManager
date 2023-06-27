package com.ampaschal.google.apps;

import com.ampaschal.google.PermissionsManager;

public class AllPermissionsApp {

    public static void main(String[] args) {

        PermissionsManager.setup();

        WordCountApp.performFileCount();
        ReadNetworkApp.performNetworkCount();
        ProcessExecApp.performShellExec();
    }
}
