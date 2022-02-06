package me.jay.staffcore.commands;

public class Vanish {
    //TODO: Use database to improve keeping vanish status on rejoin!
    //Vanish status is currently only kept using the "vanished" ArrayList and PlayerJoinEvent

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StaffCore.getInstance().getConfig();
        public ArrayList<String> vanished = new ArrayList<String>();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (config.getBoolean("Features.Vanish")) {
                if (command.getName().equalsIgnoreCase("vanish")) {
                    if (player.isOp() || player.hasPermission(config.getString("Vanish.Permission"))) {
                                if(!isVanished(player)) {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        players.hidePlayer(player);
                                        vanished.add(player.getName());
                                        msg = Color(config.getString("Vanish.Format.Vanish"))
                                        player.sendMessage(msg);
                                    }
                                } else {
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        players.hidePlayer(player);
                                        vanished.remove(player.getName());
                                        msg = Color(config.getString("Vanish.Format.Unvanish"))
                                        player.sendMessage(msg);
                                    }
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

    public boolean isVanished(Player player) {
        if(vanished.contains(player.getName()));
        return false;
    }
    
}
