package com.milk.boimanboom.gamemode;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("milk.boimanboom.admin")) {
                if (cmd.getName().equalsIgnoreCase("gmc")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ChatColor.GREEN + "Your gamemode has been set to " + ChatColor.YELLOW + "CREATIVE");
                } else if (cmd.getName().equalsIgnoreCase("gms")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GREEN + "Your gamemode has been set to " + ChatColor.YELLOW + "SURVIVAL");
                } else if (cmd.getName().equalsIgnoreCase("gmsp")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(ChatColor.GREEN + "Your gamemode has been set to " + ChatColor.YELLOW + "SPECTATOR");
                } else if (cmd.getName().equalsIgnoreCase("gma")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(ChatColor.GREEN + "Your gamemode has been set to " + ChatColor.YELLOW + "ADVENTURE");
                }
            }
        }

        return false;
    }
}
