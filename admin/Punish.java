package com.milk.boimanboom.admin;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class Punish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("milk.boimanboom.admin")){



                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);

                        switch (args[1].toLowerCase()) {
                            case "kick":
                                target.kickPlayer(ChatColor.RED + "You have been kicked for doing something that is not allowed on our server\nif you are wondering why this isn't a warn\nIt is because this is to much for a warn");
                                break;
                            case "ban":
                                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.RED + "You have Been banned for one of these reasons\nHacking\nexploiting\nchat spam\nscams", null, null);
                                target.kickPlayer(ChatColor.RED + "You have Been banned for one of these reasons\nHacking\nexploiting\nchat spam\nscams");
                                break;

                            case "tempban":
                                Calendar cal = Calendar.getInstance();
                                cal.add(Calendar.HOUR, 12);

                                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.RED + "is stipd\nplz sty awy", null, null);
                                target.kickPlayer(ChatColor.RED + "You have Been banned for one of these reasons\nHacking and giving other players an unfair advantage\nexploiting\nchat spam\nscams\nbulling other players");
                                break;

                            default:
                                player.sendMessage(ChatColor.RED + "You didn't specify whether to kick ban for tempban");
                                return false;

                        }
                    }

                } else {
                    player.sendMessage("This Player is offline, please wait until this  player so rejoined");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Please use /punish <player> <kick/ban/tempban>");
            }
        }


        return false;
    }
}
