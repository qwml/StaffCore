package me.jay.staffcore.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.jay.staffcore.commands.Freeze.frozen;

public class PlayerMoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        FileConfiguration config = StaffCore.getInstance().getConfig();
        
        if(isFrozeen(player)) {
            e.setCancelled(true);
        }
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }

    public boolean isFrozen(Player player) {
        if(frozen.contains(player.getName()));
        return false;
    }
    
}
