package me.jay.staffcore;

import me.jay.staffcore.database.database;
import me.jay.staffcore.database.databasequeries;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffCore extends JavaPlugin implements Listener {

    public database DB;
    public databasequeries DBQ;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.DB = new database();
        this.DBQ = new databasequeries(this);
        commands();
        listeners();
        files();
        database();
        startup();
    }

    public void commands(){
        getCommand("command").setExecutor(new staffchat(this));
    }

    public void files(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void listeners(){
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void database(){
        // Ignore this for now.
    }

    public void startup(){
        this.getLogger().info("&8[&cStaffCore&8] &aYour core is now Active.");
        this.getLogger().info("&7Created by Jayie and Mario.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
