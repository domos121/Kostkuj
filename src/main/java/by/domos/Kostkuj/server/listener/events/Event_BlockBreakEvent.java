package by.domos.Kostkuj.server.listener.events;

import by.domos.Kostkuj.server.player.event.EventBlockBreak;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Event_BlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent b) {
        EventBlockBreak destroy = new EventBlockBreak();
        destroy.checkPlayerBreak(b);
    }
}
