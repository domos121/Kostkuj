package me.domos.Kostkuj.server.listener.events;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.server.luckperms.LPCoreUser;
import me.domos.Kostkuj.server.player.event.EventPlayerJoin;
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
            DiscordConnect.sendMsg("```fix\n" + pj.getPlayer().getDisplayName() + " se poprvé připojil do hry." + "\n```");
        }
    }
}