package by.domos.Kostkuj.server.listener.events;

import by.domos.Kostkuj.connect.MySQL.player.MPlayerDeath;
import by.domos.Kostkuj.connect.discord.DiscordConnect;
import org.bukkit.entity.Player;
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
        DiscordConnect.sendMsg("```fix\n" + e.getDeathMessage() +  "\n```");
    }
}
