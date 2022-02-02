package me.jay.staffcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class stafflist implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("stafflist")){

                if (player.hasPermission("*insert permission*")){

                    Player playername = null;
                    for(Player name : Bukkit.getOnlinePlayers()){
                        name = playername;
                    }


                    for (String listmessage : ){
                        listmessage.replace(();
                        player.sendMessage(listmessage);
                    }

                }

            }


        }
        return true;
    }
}
