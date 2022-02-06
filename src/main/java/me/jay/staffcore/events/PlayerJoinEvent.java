package me.jay.staffcore.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.jay.staffcore.commands.Vanish.vanished;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        FileConfiguration config = StaffCore.getInstance().getConfig();
        
        for(String players : vanished) {
            Player players = Bukkit.getPlayer(players);
            players.hidePlayer(player);
            msg = Color(config.getString("Vanish.Format.KeptVanish"))
            player.sendMessage(msg);
            e.setJoinMessage(null)
        }
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
    
}
