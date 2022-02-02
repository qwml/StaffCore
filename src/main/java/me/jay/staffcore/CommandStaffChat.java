package me.jay.staffcore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandStaffChat implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StaffCore.getInstance().getConfig();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(player.isOp() || player.hasPermission(config.getString("StaffChat.Permission"))) {
                if(args.length <= 0) {
                    player.sendMessage(ChatColor.RED + "Correct usage: /sc <message>");
                } else {
                    String message = "";
                    for (String arg : args) {
                        message = message + arg + " ";
                    }
                    if(message.length() <= 0) {
                        player.sendMessage(ChatColor.RED + "Correct usage: /sc <message>");
                    } else {
                        staffchat.StaffChatMessage(player, message);
                        return true;
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "[ERROR] You don't have permission to run this command.");
            }
        } else {
            StaffCore.getInstance().getLogger().info(ChatColor.RED + "[StaffCore] [ERROR] This command must be executed as a player.");
        }

        return true;
    }
    
}