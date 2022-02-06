package me.jay.staffcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Maintenance implements CommandExecutor {
    public boolean status();
        @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StaffCore.getInstance().getConfig();
        public boolean status = false;
        //TODO: Make command not player only!

        if (sender instanceof Player) {
            Player player = (Player) sender;
            String msg = config.getString("StaffChat.Format");
            String formattedMessage = msg.replace("%player%", sender.getDisplayName());

            if (config.getBoolean("Features.Maintenance")) {
                if (command.getName().equalsIgnoreCase("maintenance")) {
                    if (player.isOp() || player.hasPermission(config.getString("Maintenance.Permission.Toggle"))) {
                        if(status == false) {
                            status = true
                            formattedMessage = formattedMessage.replace("%status%", Color("&aon"));
                            Bukkit.broadcastMessage(formattedMessage);
                        } else {
                            status = false
                            formattedMessage = formattedMessage.replace("%status%", Color("&coff"));
                            Bukkit.broadcastMessage(formattedMessage);
                        }
                    } else {
                        player.sendMessage(Color(config.getString("Errors.NoPermission")));
                    }
                }
            }
        } else {
            StaffCore.getInstance().getLogger().info(ChatColor.RED + "[StaffCore] [ERROR] This command must be executed as a player.");
        }
        return true;
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }

}
