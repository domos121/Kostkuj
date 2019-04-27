package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.bukkit.player.KPlayerSettings;
import me.domos.kostkuj.bukkit.player.event.EventPlayerQuit;
import me.domos.kostkuj.general.connect.mysql.player.Statisticks;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Event_PlayerQuitEvent implements Listener {

    Statisticks stat = new Statisticks();

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent a) {
        EventPlayerQuit playerquit = new EventPlayerQuit();
        playerquit.playerIpEdit(a.getPlayer());
        stat.setStats(a.getPlayer().getName(), a.getPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE));
        KPlayerSettings kps = new KPlayerSettings();
        kps.setLoginTime(a.getPlayer().getName(), System.currentTimeMillis());
    }
}
