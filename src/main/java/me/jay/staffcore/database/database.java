package me.jay.staffcore.database;


import me.jay.staffcore.StaffCore;
import org.bukkit.plugin.Plugin;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    Plugin plugin = StaffCore.getPlugin(StaffCore.class);


    private Connection connection;

    public boolean isConnected(){
        return(this.connection != null);
    }

    public void connect() throws SQLException {
        String host = plugin.getConfig().getString("Database.Address");
        String port = plugin.getConfig().getString("Database.Port");
        String user = plugin.getConfig().getString("Database.Username");
        String pass = plugin.getConfig().getString("Database.Password");
        String database = plugin.getConfig().getString("Database.Database");
        String databaseCheck = plugin.getConfig().getString("Database.Type");

        if (!isConnected()) {
            if (databaseCheck.equalsIgnoreCase("MySQL")) {
                try {
                    this.connection = DriverManager.getConnection("jdbc:mysql://" + URLEncoder.encode(user, StandardCharsets.UTF_8) + ":" + URLEncoder.encode(pass, StandardCharsets.UTF_8) + "@" + host + ":" + port + "/" + database + "?autoReconnect=true");
                } catch (SQLException e) {
                    plugin.getLogger().severe("! DATABASE NEEDS TO BE CONNECTED !");
                }
            }else{
                try {
                    this.connection = DriverManager.getConnection("jdbc:H2:///home/plugins/StaffCore/h2database");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void disconnect(){
        if (isConnected()){
            try{
                this.connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

}
