package com.milk.boimanboom;

import com.milk.boimanboom.admin.Punish;
import com.milk.boimanboom.admin.VanishCommand;
import com.milk.boimanboom.gamemode.GamemodeCommand;
import com.milk.boimanboom.message.MessageCommand;
import com.milk.boimanboom.message.ReplyCommand;
import com.milk.boimanboom.prankcommands.*;
import com.milk.boimanboom.rank.NametagManager;
import com.milk.boimanboom.rank.RankCommand;
import com.milk.boimanboom.rank.RankListener;
import com.milk.boimanboom.rank.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {

    private HashMap<UUID, UUID> recentMessages;

    private RankManager rankManager;
    private NametagManager nametagManager;

    @Override
    public void onEnable() {

        recentMessages = new HashMap<>();

        getCommand("rank").setExecutor(new RankCommand(this));

        getCommand("msg").setExecutor(new MessageCommand(this));
        getCommand("r").setExecutor(new ReplyCommand(this));

        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));

        getCommand("punish").setExecutor(new Punish());

        getCommand("v").setExecutor(new VanishCommand());
        getCommand("vanish").setExecutor(new VanishCommand());

        GamemodeCommand gamemode = new GamemodeCommand();

        getCommand("gmc").setExecutor(gamemode);
        getCommand("gms").setExecutor(gamemode);
        getCommand("gma").setExecutor(gamemode);
        getCommand("gmsp").setExecutor(gamemode);

        // prank commands

        getCommand("pleaseopme").setExecutor(new POPCommand());
        getCommand("zoo").setExecutor(new ZooCommand());
        getCommand("makemeadmin").setExecutor(new MAdmin());
        getCommand("whatdoyoudo").setExecutor(new WDo());
        getCommand("youareananimal").setExecutor(new Yanimal());
        getCommand("whoami").setExecutor(new WhoAmI());
        getCommand("ping").setExecutor(new PinCommand());
        




        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

    }

    @EventHandler
    public void onJoinn(PlayerJoinEvent e) {
        Player player = (Player) e.getPlayer();

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("Boimanboom", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.BLUE.toString() + ChatColor.BOLD + "boimanboom");

        Score website = obj.getScore(ChatColor.YELLOW + "https://sevan7waters.w3spaces.com");
        website.setScore(1);

        Score space = obj.getScore(" ");
        space.setScore(2);

        Score rank = obj.getScore(getRankManager().getRank(player.getUniqueId()).getDisplay());
        rank.setScore(3);

        Score sRank = obj.getScore(ChatColor.RED.toString() + ChatColor.BOLD + "Rank");
        sRank.setScore(4);

        Score space2 = obj.getScore(" ");
        space2.setScore(5);

        Score Player = obj.getScore(player.getName());
        Player.setScore(6);

        Score splayer = obj.getScore(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Player");
        splayer.setScore(7);

        player.setScoreboard(board);

    }

    public HashMap<UUID, UUID> getRecentMessages() { return recentMessages; }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }

    public RankManager getRankManager() { return rankManager; }
    public NametagManager getNametagManager() { return nametagManager; }

}
