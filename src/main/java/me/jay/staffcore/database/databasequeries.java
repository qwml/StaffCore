package me.jay.staffcore.database;

import me.jay.staffcore.StaffCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class databasequeries {

    private final StaffCore plugin;
    public databasequeries(StaffCore plugin){
        this.plugin = plugin;
    }

    public void createPlayerTable() throws SQLException {
        PreparedStatement table = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS SCPlayer(playerUUID varchar(255), Frozen int(1), Vanished int(1), PRIMARY KEY(playerUUID))");
        table.executeUpdate();
        plugin.getLogger().info(Color("&8[&6Marriage&8] &6Database table created."));
    }

    public void createServerTable() throws SQLException {
        PreparedStatement table = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS SCServer(ServerID int(1), Maintenance int(1), MutedChat int(1), PRIMARY KEY(ServerID))");
        table.executeUpdate();
        plugin.getLogger().info(Color("&8[&6Marriage&8] &6Database table created."));
    }

    public void serverTableValues() throws SQLException {
        if (!doesServerExist()){
            PreparedStatement createPlayer = plugin.DB.getConnection().prepareStatement("INSERT IGNORE INTO SCServer(ServerID, Maintenance, MutedChat) VALUES (?,?,?)");
            createPlayer.setInt(1, 1);
            createPlayer.setInt(2, 0);
            createPlayer.setInt(3, 0);
            createPlayer.executeUpdate();
        }
    }

    public void createPlayer(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();
        if (!doesPlayerExist(player)){
            PreparedStatement createPlayer = plugin.DB.getConnection().prepareStatement("INSERT IGNORE INTO SCPlayer(playerUUID, Frozen, Vanished) VALUES (?,?,?)");
            createPlayer.setString(1, uuid.toString());
            createPlayer.setInt(2, 0);
            createPlayer.setInt(3, 0);
            createPlayer.executeUpdate();
        }
    }


    public boolean doesPlayerExist(Player player) throws SQLException{
        UUID uuid = player.getUniqueId();

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM SCPlayer WHERE playerUUID=?");
        ps1.setString(1, String.valueOf(uuid));
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()){
            return true;
        }else{
            return false;
        }
    }

    public boolean doesServerExist() throws SQLException{

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM SCServer WHERE ServerID=?");
        ps1.setInt(1, 1);
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()){
            return true;
        }else{
            return false;
        }
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }

}
