package me.jay.staffcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MuteChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StaffCore.getInstance().getConfig();
        if (sender instanceof Player){
            Player player = (Player) sender;

            if (config.getBoolean("Features.MuteChat")){

                if (command.getName().equalsIgnoreCase("mutechat")){
                    if (player.hasPermission(config.getString("MuteChat.Permission"))){

                        PreparedStatement mutechatps;
                        PreparedStatement mutechatcheckps;
                        try {
                            mutechatps = StaffCore.getInstance().DB.getConnection().prepareStatement("UPDATE SCServer(MutedChat) VALUES (?)");
                            mutechatcheckps = StaffCore.getInstance().DB.getConnection().prepareStatement("SELECT MutedChat FROM SCServer");
                            ResultSet mutechatcheckrs = mutechatcheckps.executeQuery();
                            int mutechatcheck = -1;
                            String message;
                            if (mutechatcheckrs.next()){
                                mutechatcheck = mutechatcheckrs.getInt("MutedChat");
                            }

                            if (mutechatcheck == 1) {
                                mutechatps.setInt(1, 1);
                                message = Color(config.getString("MutedChat.Format.Mute"))
                                player.sendMessage(message);
                            }else{
                                mutechatps.setInt(1, 0);
                                message = Color(config.getString("MutedChat.Format.Unmute"))
                                player.sendMessage(message);
                            }


                            mutechatps.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }
                }

            }

        }

        return true;
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
