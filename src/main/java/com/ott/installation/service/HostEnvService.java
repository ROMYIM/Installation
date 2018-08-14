package com.ott.installation.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import com.ott.installation.constant.DirectoryPermission;
import com.ott.installation.domain.EnviromentStatus;
import com.ott.installation.domain.HostEnviroment;

import org.springframework.stereotype.Service;

/**
 * HostEnvService
 */
@Service
public class HostEnvService {

    public HostEnviroment getHostProperties() {
        HostEnviroment hostEnviroment = new HostEnviroment();
        try {
            getHostSystem(hostEnviroment);
            hostEnviroment.setRam(getHostPhysicalMemory());
            hostEnviroment.setDisk(getHostDiskSize());
            hostEnviroment.setPermission(getDirectoryPermission());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return hostEnviroment;
    }

    public EnviromentStatus checkEnviroment(HostEnviroment hostEnviroment) {
        boolean osStatus, ramStatus, diskStatus;
        osStatus = ramStatus = diskStatus = true;
        String operatingSystem = hostEnviroment.getOperatingSystem().toLowerCase();
        if (operatingSystem.contains("windows") || operatingSystem.contains("mac")) {
            osStatus = false;
        }
        if (hostEnviroment.getRam() < 4096) {
            ramStatus = false;
        }
        if (hostEnviroment.getDisk() < 40) {
            diskStatus = false;
        }
        return new EnviromentStatus(osStatus, ramStatus, diskStatus);
    }

    private void getHostSystem(HostEnviroment hostEnviroment) throws IOException {
        OperatingSystemMXBean mxBean = ManagementFactory.getOperatingSystemMXBean();
        hostEnviroment.setOperatingSystem(mxBean.getName());
        hostEnviroment.setVersion(mxBean.getVersion());
        String command = "getconf LONG_BIT";
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String readString = reader.readLine();
        hostEnviroment.setBitWidth(Integer.parseInt(readString));
    }

    private int getHostPhysicalMemory() throws IOException {
        String command = "cat /proc/meminfo";
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        for (String readString = reader.readLine(); readString != null; ) {
            String[] memoryInfo = readString.split("\\s+");
            if (memoryInfo[0].equals("MemTotal:")) {
                return Integer.parseInt(memoryInfo[1]);
            } 
        }
        return -1;
    }

    private int getHostDiskSize() throws IOException {
        String command = "df -hl";
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        for (String readString = reader.readLine(); readString != null; ) {
            String[] diskInfo = readString.split("\\s+");
            if (diskInfo[5].equals("/")) {
                return Integer.parseInt(diskInfo[1].toLowerCase().replace("g", ""));
            }
        }
        return -1;
    }

    private DirectoryPermission getDirectoryPermission() throws NullPointerException {
        File optDir = new File("/opt");
        int permissionFlag = DirectoryPermission.NONE.ordinal();
        if (optDir.canExecute()) {
            permissionFlag += DirectoryPermission.ONLY_EXECUTE.ordinal();
            boolean canRead = optDir.setReadable(true);
            boolean canWrite = optDir.setWritable(true);
            if (canRead && canWrite) {
                return DirectoryPermission.ALL;
            }
            return DirectoryPermission.READ_EXECUTE;
        } else {
            if (optDir.canRead()) {
                permissionFlag += DirectoryPermission.ONLY_READ.ordinal();
            }
            if (optDir.canWrite()) {
                permissionFlag += DirectoryPermission.ONLY_WRITE.ordinal();
            }
            switch (permissionFlag) {
                case 0:
                    return DirectoryPermission.NONE;
                case 1:
                    return DirectoryPermission.ONLY_READ;
                case 2:
                    return DirectoryPermission.ONLY_WRITE;
                default:
                    return DirectoryPermission.READ_WRITE;
            }
        }
    }
}