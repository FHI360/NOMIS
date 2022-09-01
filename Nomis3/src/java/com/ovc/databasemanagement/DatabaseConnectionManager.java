/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ovc.databasemanagement;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class DatabaseConnectionManager implements Serializable
{
    private String dbDriverClass="org.apache.derby.jdbc.EmbeddedDriver";
    private String dbDialect="org.hibernate.dialect.DerbyDialect";
    private String connectionURL="jdbc:derby:C:\\Nomis3\\dbs\\nomis3db";
    private String username="nomis";
    private String password="nomispw";

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getDbDialect() {
        return dbDialect;
    }

    public void setDbDialect(String dbDialect) {
        this.dbDialect = dbDialect;
    }

    public String getDbDriverClass() {
        return dbDriverClass;
    }

    public void setDbDriverClass(String dbDriverClass) {
        this.dbDriverClass = dbDriverClass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
