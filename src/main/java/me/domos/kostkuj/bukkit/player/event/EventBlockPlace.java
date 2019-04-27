package me.domos.kostkuj.bukkit.player.event;

import me.domos.kostkuj.general.connect.mysql.mysqlListener.MySQLset;
import me.domos.kostkuj.general.fileManager.ECfg;
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
