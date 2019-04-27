package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.bukkit.player.event.EventPlayerJoin;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.luckpermsData.LPCoreUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event_PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent pj) {
        EventPlayerJoin playerjoin = new EventPlayerJoin();
        LPCoreUser lpCoreUser = new LPCoreUser();
        playerjoin.playerIpEdit(pj.getPlayer());
        lpCoreUser.core(pj.getPlayer());
        if (!pj.getPlayer().hasPlayedBefore()){
            DiscordConnect.sendOrangeMsg(pj.getPlayer().getDisplayName() + " se poprvé připojil do hry.");
        }
    }
}
