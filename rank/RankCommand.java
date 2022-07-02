package com.milk.boimanboom.rank;

import com.milk.boimanboom.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    private Main main;

    public RankCommand(Main main) {
        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("milk.boimanboom.admin")) {
                if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer tar = Bukkit.getOfflinePlayer(args[0]);

                        for (Rank rank : Rank.values()) {
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                main.getRankManager().setRank(tar.getUniqueId(), rank, false);

                                if (tar == player) {
                                    player.sendMessage(ChatColor.GREEN + "You Changed Your rank to " + rank.getDisplay());
                                } else {
                                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "========================================================================\n " + ChatColor.GREEN + tar.getName() + "'s rank has been set to: " + rank.getDisplay() + ChatColor.BLUE + " \n========================================================================");
                                    if (tar.isOnline()) {
                                        tar.getPlayer().sendMessage(ChatColor.GREEN + "Your rank has been changed to " + rank.getDisplay() + ChatColor.GREEN + ".");
                                    } else {
                                        player.sendMessage(ChatColor.RED + "You gave " + rank.getDisplay() + "" + ChatColor.RED + " to " + tar.getName() + "" + ChatColor.RED + " which is offline!");
                                    }
                                }
                                return false;
                            }
                        }

                        player.sendMessage(ChatColor.RED + "You did not Specify a valid rank!");
                    } else {
                        player.sendMessage(ChatColor.RED + "This Player has never joined the server before!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please use /rank <player> <rank>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
            }
        }
        return false;
    }
}
