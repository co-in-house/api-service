package com.inhouse.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.inhouse.util.Config;


@Dependent
public class DBConnection {

    private static final String dataSourceName; 
    
    static {
        try {
            dataSourceName = Config.getProp("CONNECTION");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Produces
    public static Connection getConnection() throws NamingException, SQLException {
        Context ctx = (Context) InitialContext.doLookup("java:comp/env");
        DataSource dataSource = (DataSource) ctx.lookup(dataSourceName);
        return dataSource.getConnection();
    }

    public static void closeConnection(@Disposes Connection conn) throws SQLException {
        conn.close();
    }
}
