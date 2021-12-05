/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package it.md_4.troy.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = "178.174.14.140";
    private String port = "3306";
    private String database = "troyclient";
    private String username = "troy";
    private String password = "Hwdcop5625010";

    private Connection connection;

    public boolean isConnected() {
        return (connection == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()){
            Class.forName("com.mysql.cj.jdbc.Driver");



            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + database,
                    username, password);
        }
    }

    public void disconnect() {
        if(isConnected()){
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getconnection() {
        return connection;
    }

}
