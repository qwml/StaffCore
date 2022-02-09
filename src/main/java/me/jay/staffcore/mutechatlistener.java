package me.jay.staffcore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mutechatlistener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) throws SQLException {
        Player player = e.getPlayer();

        PreparedStatement mutechatcheckps = StaffCore.getInstance().DB.getConnection().prepareStatement("SELECT MutedChat FROM SCServer");
        ResultSet mutechatcheckrs = mutechatcheckps.executeQuery();
        int mutechatcheck = 0;
        if (mutechatcheckrs.next()){
            mutechatcheck = mutechatcheckrs.getInt("MutedChat");
        }

        if (mutechatcheck == 1){
            if (!player.hasPermission("staffcore.mutechat.bypass"))
            e.setCancelled(true);
            player.sendMessage("You cannot chat whilst muted.");
        }
    }
}
