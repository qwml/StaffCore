package me.jay.staffcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Freeze {
    //TODO: Use database to improve keeping freeze status on rejoin!
    //Freeze status is currently only kept using the "frozen" ArrayList 

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StaffCore.getInstance().getConfig();
        public ArrayList<String> frozen = new ArrayList<String>();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (config.getBoolean("Features.Freeze")) {
                if (command.getName().equalsIgnoreCase("freeze")) {
                    if (player.isOp() || player.hasPermission(config.getString("Freeze.Permission"))) {
                        if (args.length == 0) {
                            sender.sendMessage(Color(config.getString("Errors.NoPlayerSpecified")));
                            return true;
                        }
                        if (target == null) {
                            sender.sendMessage(Color(config.getString("Errors.NullPlayer")));
                            return true;
                        }
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if(!isFrozen(player)) {
                            frozen.add(target.getName());
                            String preTargetMsg = Color(config.getString("Freeze.Format.Player.Freeze"));
                            String preModMsg = Color(config.getString("Freeze.Format.Moderator.Freeze"));
                            String targetMsg = preTargetMsg.replace("%player%", sender.getDisplayName());
                            String modMsg = premodMsg.replace("%target%", target.getDisplayName());
                            target.sendMessage(targetMsg);
                            player.sendMessage(modMsg);
                        } else {
                            frozen.remove(target.getName());
                            String preTargetMsg = Color(config.getString("Freeze.Format.Player.Unfreeze"));
                            String preModMsg = Color(config.getString("Freeze.Format.Moderator.Unfreeze"));
                            String targetMsg = preTargetMsg.replace("%player%", sender.getDisplayName());
                            String modMsg = premodMsg.replace("%target%", target.getDisplayName());
                            target.sendMessage(targetMsg);
                            player.sendMessage(modMsg);
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

    public boolean isFrozen(Player player) {
        if(frozen.contains(player.getName()));
        return false;
    }
    
}
