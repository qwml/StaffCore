package me.jay.staffcore;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    public void commands(){

    }

    public void files(){

    }

    public void listeners(){

    }

    public void database(){

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
