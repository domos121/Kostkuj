package by.domos.Kostkuj.server.listener.events;

import by.domos.Kostkuj.connect.MySQL.player.Statisticks;
import by.domos.Kostkuj.server.player.event.EventPlayerQuit;
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
    }
}
