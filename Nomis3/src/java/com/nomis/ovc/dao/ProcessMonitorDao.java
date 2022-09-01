/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.ProcessMonitor;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface ProcessMonitorDao 
{
    public void saveProcessMonitor(ProcessMonitor pm) throws Exception;
    public void updateProcessMonitor(ProcessMonitor pm) throws Exception;
    public void deleteProcessMonitor(ProcessMonitor pm) throws Exception;
    public ProcessMonitor getProcessMonitorByProcessId(String processId) throws Exception;
    public ProcessMonitor getProcessMonitorByProcessName(String processName) throws Exception;
    public List getAllProcessMonitors() throws Exception;
}
