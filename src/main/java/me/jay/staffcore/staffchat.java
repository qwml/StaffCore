package me.jay.staffcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class staffchat {
    
    public static void StaffChatMessage(Player sender, String message) {
        FileConfiguration config = StaffCore.getInstance().getConfig();
        String chatformat = config.getString("StaffChat.Format");
        String formattedMessage = chatformat.replace("%player%", sender.getDisplayName());
        formattedMessage = formattedMessage.replace("%message%", message);
        formattedMessage = ChatColor.translateAlternateColorCodes('&', formattedMessage);
        
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.hasPermission(config.getString("StaffChat.Permission"))) {
                player.sendMessage(formattedMessage);
            }
        }
    }
    
}
