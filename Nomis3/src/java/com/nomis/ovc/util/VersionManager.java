/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

/**
 *
 * @author smomoh
 */
public class VersionManager 
{
    public static Double getVersionNumber()
    {
      return new Double("2.658");   
    }
    public static String getVersionName()
    {
      return "version "+getVersionNumber().doubleValue();   
    }
}

