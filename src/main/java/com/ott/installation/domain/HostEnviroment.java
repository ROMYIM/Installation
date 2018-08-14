package com.ott.installation.domain;

import com.ott.installation.constant.DirectoryPermission;

/**
 * HostEnviroment
 */
public class HostEnviroment {

    private String operatingSystem;
    private int ram;
    private int disk;
    private DirectoryPermission permission;
    private String version;
    private int bitWidth;

    /**
     * @param disk the disk to set
     */
    public void setDisk(int disk) {
        this.disk = disk;
    }

    /**
     * @return the disk
     */
    public int getDisk() {
        return disk;
    }

    /**
     * @param operatingSystem the operatingSystem to set
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * @return the operatingSystem
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * @param ram the ram to set
     */
    public void setRam(int ram) {
        this.ram = ram;
    }

    /**
     * @return the ram
     */
    public int getRam() {
        return ram;
    }

    /**
     * @param permission the permission to set
     */
    public void setPermission(DirectoryPermission permission) {
        this.permission = permission;
    }

    /**
     * @return the permission
     */
    public DirectoryPermission getPermission() {
        return permission;
    }

    /**
     * @param bitWidth the bitWidth to set
     */
    public void setBitWidth(int bitWidth) {
        this.bitWidth = bitWidth;
    }

    /**
     * @return the bitWidth
     */
    public int getBitWidth() {
        return bitWidth;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }
}