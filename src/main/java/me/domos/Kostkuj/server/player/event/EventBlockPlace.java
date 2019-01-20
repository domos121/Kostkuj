package me.domos.Kostkuj.server.player.event;

import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLset;
import me.domos.Kostkuj.enums.ECfg;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventBlockPlace {
    MySQLset mysqlset = new MySQLset();

    public void checkPlayerPlace(BlockPlaceEvent c) {
        boolean itemos = ECfg.isAcceptItemForAntiXray(c.getBlock());
        if (itemos) {
            mysqlset.setPlacedBlock(c.getBlock());
        }
    }
}
