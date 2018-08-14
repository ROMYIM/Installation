package com.ott.installation.domain;

/**
 * EnviromentStatus
 */
public class EnviromentStatus {

    private boolean osStatus;
    private boolean ramStatus;
    private boolean diskStatus;

    public EnviromentStatus(boolean osStatus, boolean ramStatus, boolean diskStatus) {
        this.osStatus = osStatus;
        this.ramStatus = ramStatus;
        this.diskStatus = diskStatus;
    }

    public boolean getOsStatus() {
        return osStatus;
    }

    public boolean getRamStatus() {
        return ramStatus;
    }

    public boolean getDiskStatus() {
        return diskStatus;
    }
    
}