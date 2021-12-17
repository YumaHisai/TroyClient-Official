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

    public MySQL() {
    }

    public boolean isConnected() {
        return this.connection != null;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!this.isConnected()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
        }

    }

    public void disconnect() {
        if (this.isConnected()) {
            try {
                this.connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

    }

    public Connection getconnection() {
        return this.connection;
    }
}
