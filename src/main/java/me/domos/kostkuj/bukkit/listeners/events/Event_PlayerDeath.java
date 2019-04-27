package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.connect.mysql.player.MPlayerDeath;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
public class Event_PlayerDeath implements Listener {

    MPlayerDeath mpd = new MPlayerDeath();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Double position_x = e.getEntity().getLocation().getX();
        Double position_y = e.getEntity().getLocation().getY();
        Double position_z = e.getEntity().getLocation().getZ();
        String world  = e.getEntity().getLocation().getWorld().getName();
        mpd.setDeath(e.getEntity().getName(), e.getDeathMessage(), position_x, position_y, position_z, world);
        DiscordConnect.sendOrangeMsg(e.getDeathMessage());
    }
}
