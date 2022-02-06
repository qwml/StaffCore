package me.jay.staffcore.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.jay.staffcore.commands.Maintenance.status;

public class AsyncPlayerPreLoginEvent implements Listener {

    @EventHandler
    public void onPrePlayerJoin(AsyncPlayerPreLoginEvent e) {
        Player player = e.getPlayer();
        FileConfiguration config = StaffCore.getInstance().getConfig();
    
        if(!player.isOp() || !player.hasPermission(config.getString("Maintenance.Permission.BypassPermission"))) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Color(config.getString("Maintenance.Reason")))
        }
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
    
}
