package me.domos.Kostkuj.server.listener.events;

import me.domos.Kostkuj.server.player.event.EventPlayerPreLogin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;

public class Event_PlayerPreLoginEvent implements Listener {

    @EventHandler
    @Deprecated
    public void onPlayerJoin(PlayerPreLoginEvent a) {
        EventPlayerPreLogin prelogin = new EventPlayerPreLogin();
        prelogin.checkEmailConfirm(a);

    }
}

