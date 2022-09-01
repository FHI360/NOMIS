/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.dao;


import com.nomis.ovc.util.AppUtility;
import com.nomis.ovc.util.DatabaseUtilities;
import com.ovc.databasemanagement.DatabaseConnectionManager;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author HP USER
 */
public class HibernateUtil
{

private static SessionFactory sessionFactory;

static 
{
try
{
    AppUtility appUtil=new AppUtility();
    
    Configuration cfg = new Configuration();
    //cfg.configure();
    DatabaseConnectionManager dbConn=AppUtility.dbConnectionManager;
    //String dbURL=appUtil.getDatabaseURL();
    if(dbConn !=null)
    {
        cfg.setProperty("hibernate.connection.url", dbConn.getConnectionURL());
        cfg.setProperty("hibernate.dialect", dbConn.getDbDialect());
        cfg.setProperty("hibernate.connection.driver_class", dbConn.getDbDriverClass());
        cfg.setProperty("hibernate.connection.username", dbConn.getUsername());
        cfg.setProperty("hibernate.connection.password", dbConn.getPassword()); 
    }
    cfg.configure();
        //System.err.println("hibernate.connection.url value is "+cfg.getProperty("hibernate.connection.url"));
        //System.err.println("hibernate.dialect value is "+cfg.getProperty("hibernate.dialect"));
        //System.err.println("hibernate.connection.driver_class value is "+cfg.getProperty("hibernate.connection.driver_class"));
        //System.err.println("hibernate.connection.username value is "+cfg.getProperty("hibernate.connection.username"));
        //System.err.println("hibernate.connection.password value is "+cfg.getProperty("hibernate.connection.password"));
    //cfg=getPreparedConfiguration(cfg);
    //sessionFactory = cfg.buildSessionFactory();
    sessionFactory = cfg.buildSessionFactory();
        
}
//catch (Throwable ex)
catch (Exception ex)
{
    System.err.println(ex.getMessage());
    //ex.printStackTrace(System.out);
    //throw new ExceptionInInitializerError(ex);
}

}
public static SessionFactory changeConfiguration(){
    Configuration cfg = new Configuration();
    cfg.configure();
    cfg=getPreparedConfiguration(cfg);
    SessionFactory sessionFactory = cfg.buildSessionFactory();
    return sessionFactory;
}
public static Session getSession() throws HibernateException
{
    return sessionFactory.openSession();
}
public static Configuration getPreparedConfiguration(Configuration cfg)
{
    System.err.println("Inside getPreparedConfiguration(cfg) ");
    List list=DatabaseUtilities.getConnectionParameters();
    if(list !=null && !list.isEmpty())
    {
        //System.err.println("list size in getPreparedConfiguration(cfg) is "+list.size());
        String property=null;
        String value=null;
        for(int i=0; i<list.size(); i++)
        {
            String str=list.get(i).toString();
            if(str !=null && str.indexOf("=") !=-1)
            {
                String[] strArray=str.split("=");
                if(strArray !=null && strArray.length>1)
                {
                    property=strArray[0];
                    value=strArray[1];
                    if(property !=null && value !=null)
                    {
                        property=property.trim();
                        value=value.trim();
                        System.err.println("property: "+property+" value: "+value);
                        if(value.length()>0)
                        {
                            if(property.equalsIgnoreCase("hibernate.connection.driver_class"))
                            {
                                cfg.setProperty(property, value);
                            }
                            else if(property.equalsIgnoreCase("hibernate.dialect"))
                            {
                               cfg.setProperty(property, value); 
                            }
                            else if(property.equalsIgnoreCase("hibernate.connection.url"))
                            {
                                cfg.setProperty(property, value);
                            }
                            else if(property.equalsIgnoreCase("hibernate.connection.username"))
                            {
                                cfg.setProperty(property, value);
                            }
                            else if(property.equalsIgnoreCase("hibernate.connection.password"))
                            {
                               cfg.setProperty(property, value);
                            }
                        }
                    }
                }
                
            }
        }
    }
    return cfg;
}
}