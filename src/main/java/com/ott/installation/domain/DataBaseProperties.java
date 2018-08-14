package com.ott.installation.domain;

import javax.validation.constraints.NotEmpty;

/**
 * DataBaseProperties
 */
public class DataBaseProperties {

    @NotEmpty(message = "${database.host.notEmpty}")
    private String hostName;

    @NotEmpty(message = "${database.usename.notEmpty}")
    private String userName;

    @NotEmpty(message = "${database.password.notEmpty}")
    private String password;

    public DataBaseProperties(String hostName, String userName, String password) {
        this.hostName = hostName;
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
}