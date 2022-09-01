/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class EnvironmentInformationProvider implements Serializable
{
    private ProgressIndicator currentTime = new ProgressIndicator();
    private ProgressIndicator tomcatBaseDirectory = new ProgressIndicator();
    private ProgressIndicator serverName = new ProgressIndicator();
    private ProgressIndicator tomcatVersion = new ProgressIndicator();
    private ProgressIndicator javaVersion = new ProgressIndicator();

    public ProgressIndicator getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(ProgressIndicator currentTime) {
        this.currentTime = currentTime;
    }

    public ProgressIndicator getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(ProgressIndicator javaVersion) {
        this.javaVersion = javaVersion;
    }

    public ProgressIndicator getServerName() {
        return serverName;
    }

    public void setServerName(ProgressIndicator serverName) {
        this.serverName = serverName;
    }

    public ProgressIndicator getTomcatBaseDirectory() {
        return tomcatBaseDirectory;
    }

    public void setTomcatBaseDirectory(ProgressIndicator tomcatBaseDirectory) {
        this.tomcatBaseDirectory = tomcatBaseDirectory;
    }

    public ProgressIndicator getTomcatVersion() {
        return tomcatVersion;
    }

    public void setTomcatVersion(ProgressIndicator tomcatVersion) {
        this.tomcatVersion = tomcatVersion;
    }
    
}
