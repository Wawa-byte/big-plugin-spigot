package com.milk.boimanboom.message;

import com.milk.boimanboom.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MessageCommand implements CommandExecutor {

    private Main main;

    public MessageCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }


                        player.sendMessage(ChatColor.LIGHT_PURPLE + "To " + main.getRankManager().getRank(target.getUniqueId()).getDisplay() + " " + target.getName() + ": " + ChatColor.WHITE + builder.toString());
                        target.sendMessage(ChatColor.LIGHT_PURPLE + "From " + main.getRankManager().getRank(player.getUniqueId()).getDisplay() + " " + player.getName()  + ": " + ChatColor.WHITE + builder);
                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "This Player was not Found!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Please use /msg <player> <message>");
            }
        }

        return false;
    }
}
