package me.jay.staffcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class StaffList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StaffCore.getInstance().getConfig();
        List<String> listmessage = config.getStringList("StaffList.Format");
        if (sender instanceof Player){
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("stafflist")){

                if (player.hasPermission(config.getString("StaffList.Permission.View"))){

                    Player playername = null;
                    for(Player name : Bukkit.getOnlinePlayers()){
                        if (player.hasPermission(config.getString("StaffList.Permission.Staff"))) {
                            playername = name;
                        }
                    }

                    for (String listmessage2 : listmessage){
                        listmessage2.replace("%list%", playername.toString());
                        player.sendMessage(listmessage2);
                    }



                }

            }


        }
        return true;
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
