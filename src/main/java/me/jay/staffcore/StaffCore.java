package me.jay.staffcore;

import me.jay.staffcore.database.database;
import me.jay.staffcore.database.databasequeries;

import me.jay.staffcore.events.EventOnMessage;
import me.jay.staffcore.events.PlayerJoinEvent;
import me.jay.staffcore.events.AsyncPlayerPreLoginEvent;
import me.jay.staffcore.events.PlayerMoveEvent;

import me.jay.staffcore.commands.StaffChat;
import me.jay.staffcore.commands.StaffChat;
import me.jay.staffcore.commands.StaffList;
import me.jay.staffcore.commands.MuteChat;
import me.jay.staffcore.commands.Vanish;
import me.jay.staffcore.commands.Maintenance;
import me.jay.staffcore.commands.Freeze;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class StaffCore extends JavaPlugin implements Listener {
    
    private static StaffCore instance;
    public database DB;
    public databasequeries DBQ;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.DB = new database();
        this.DBQ = new databasequeries(this);
        commands();
        listeners();
        files();
        try {
            database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        startup();
    }

    public void commands(){
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("clearchat").setExecutor(new ClearChat());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("mutechat").setExecutor(new MuteChat());
        getCommand("stafflist").setExecutor(new StaffList());
        getCommand("maintenance").setExecutor(new Maintenance());
        getCommand("freeze").setExecutor(new Freeze());
    }

    public void files(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void listeners(){
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new EventOnMessage(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginEvent(), this);
    }

    public void database() throws SQLException {
        // Ignore this for now.
        if (!DB.isConnected()) {
            DB.connect();
        }else if(DB.isConnected()){
            DBQ.createPlayerTable();
            DBQ.createServerTable();
            DBQ.serverTableValues();
        }
    }

    public void startup(){
        this.getLogger().info("&8[&cStaffCore&8] &aYour StaffCore is now Active.");
        this.getLogger().info("&7Created by Jayie and Mario.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DB.disconnect();
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent e) throws SQLException {
        Player player = e.getPlayer();
        if (DB.isConnected()){
            DBQ.createPlayer(player);
        }

    }
    
    public static StaffCore getInstance() {
        return instance;
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
