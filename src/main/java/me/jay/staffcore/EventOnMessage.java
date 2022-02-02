package me.jay.staffcore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventOnMessage implements Listener {
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        FileConfiguration config = StaffCore.getInstance().getConfig();
        
        if(message.startsWith(config.getString("StaffChat.Prefix")) && config.getBoolean("Features.StaffChat")) {
            if(player.isOp() || player.hasPermission(config.getString("StaffChat.Permission"))) {
                String msg = message.split(config.getString("StaffChat.Prefix"))[1];
                if(msg.length() >= 1) {
                    staffchat.StaffChatMessage(player, msg);
                    e.setCancelled(true);
                }
            }
        }
    }
    
}
