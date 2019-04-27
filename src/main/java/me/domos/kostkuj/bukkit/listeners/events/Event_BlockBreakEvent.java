package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.bukkit.player.event.EventBlockBreak;
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
