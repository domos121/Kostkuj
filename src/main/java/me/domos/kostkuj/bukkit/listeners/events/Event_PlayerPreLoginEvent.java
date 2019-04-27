package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.bukkit.player.event.EventPlayerPreLogin;
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

