package me.jay.staffcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StaffCore.getInstance().getConfig();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (config.getBoolean("Features.ClearChat")) {
                if (command.getName().equalsIgnoreCase("clearchat")) {
                    if (player.isOp() || player.hasPermission(config.getString("ClearChat.Permission"))) {
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            if(!p.hasPermission(config.getString("ClearChat.BypassPermission"))) {
                                for(int i=0; i < 150; i++) {
                                    p.sendMessage("");
                                }
                            }
                            p.sendMessage(Color(config.getString("ClearChat.Format")))
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
