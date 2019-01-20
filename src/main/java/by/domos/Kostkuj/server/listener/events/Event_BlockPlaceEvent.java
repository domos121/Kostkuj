package by.domos.Kostkuj.server.listener.events;

import by.domos.Kostkuj.server.player.event.EventBlockPlace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Event_BlockPlaceEvent implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent c) {
        EventBlockPlace destroy = new EventBlockPlace();
        destroy.checkPlayerPlace(c);
    }
}
