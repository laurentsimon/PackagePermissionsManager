package com.ampaschal.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PermissionObject {

    @JsonProperty("fs")
    private boolean fs;
    @JsonProperty("fs.read")
    private boolean fsRead;
    @JsonProperty("fs.write")
    private boolean fsWrite;
    @JsonProperty("fs.allowedPaths")
    private List<String> allowedPaths;
    @JsonProperty("fs.deniedPaths")
    private List<String> deniedPaths;

    @JsonProperty("net")
    private boolean net;

    @JsonProperty("net.connect")
    private boolean netConnect;

    @JsonProperty("net.accept")
    private boolean netAccept;

    @JsonProperty("net.allowedUrls")
    private List<String> allowedUrls;

    @JsonProperty("net.deniedUrls")
    private List<String> deniedUrls;

    @JsonProperty("runtime")
    private boolean runtime;

    @JsonProperty("runtime.exec")
    private boolean runtimeExec;

    @JsonProperty("runtime.allowedCommands")
    private List<String> allowedCommands;

    @JsonProperty("runtime.deniedCommands")
    private List<String> deniedCommands;

    public boolean isFs() {
        return fs;
    }

    public void setFs(boolean fs) {
        this.fs = fs;
    }

    public boolean isFsRead() {
        return fsRead;
    }

    public void setFsRead(boolean fsRead) {
        this.fsRead = fsRead;
    }

    public boolean isFsWrite() {
        return fsWrite;
    }

    public void setFsWrite(boolean fsWrite) {
        this.fsWrite = fsWrite;
    }

    public List<String> getAllowedPaths() {
        return allowedPaths;
    }

    public void setAllowedPaths(List<String> allowedPaths) {
        this.allowedPaths = allowedPaths;
    }

    public List<String> getDeniedPaths() {
        return deniedPaths;
    }

    public void setDeniedPaths(List<String> deniedPaths) {
        this.deniedPaths = deniedPaths;
    }

    public boolean isNet() {
        return net;
    }

    public void setNet(boolean net) {
        this.net = net;
    }

    public boolean isNetConnect() {
        return netConnect;
    }

    public void setNetConnect(boolean netConnect) {
        this.netConnect = netConnect;
    }

    public boolean isNetAccept() {
        return netAccept;
    }

    public void setNetAccept(boolean netAccept) {
        this.netAccept = netAccept;
    }

    public List<String> getAllowedUrls() {
        return allowedUrls;
    }

    public void setAllowedUrls(List<String> allowedUrls) {
        this.allowedUrls = allowedUrls;
    }

    public List<String> getDeniedUrls() {
        return deniedUrls;
    }

    public void setDeniedUrls(List<String> deniedUrls) {
        this.deniedUrls = deniedUrls;
    }

    public boolean isRuntime() {
        return runtime;
    }

    public void setRuntime(boolean runtime) {
        this.runtime = runtime;
    }

    public boolean isRuntimeExec() {
        return runtimeExec;
    }

    public void setRuntimeExec(boolean runtimeExec) {
        this.runtimeExec = runtimeExec;
    }

    public List<String> getAllowedCommands() {
        return allowedCommands;
    }

    public void setAllowedCommands(List<String> allowedCommands) {
        this.allowedCommands = allowedCommands;
    }

    public List<String> getDeniedCommands() {
        return deniedCommands;
    }

    public void setDeniedCommands(List<String> deniedCommands) {
        this.deniedCommands = deniedCommands;
    }
}
