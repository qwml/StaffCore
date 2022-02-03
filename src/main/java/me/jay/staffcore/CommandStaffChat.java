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

            if (config.getBoolean("Features.StaffChat")) {

                if (command.getName().equalsIgnoreCase("staffchat")) {

                    if (player.isOp() || player.hasPermission(config.getString("StaffChat.Permission"))) {
                        if (args.length <= 0) {
                            player.sendMessage(ChatColor.RED + "Correct usage: /sc <message>");
                        } else {
                            String message = "";
                            for (int i = 0; i < args.length; i++) {
                                message = message + args[i] + " ";
                            }
                            if (message.length() <= 0) {
                                player.sendMessage(ChatColor.RED + "Correct usage: /sc <message>");
                            }
                            staffchat.StaffChatMessage(player, message);
                        }
                    } else {
                        player.sendMessage(Color(config.getString("Errors.NoPermission")));
                    }
                }
            }

        }else {
            StaffCore.getInstance().getLogger().info(ChatColor.RED + "[StaffCore] [ERROR] This command must be executed as a player.");
        }
        return true;
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
    
}