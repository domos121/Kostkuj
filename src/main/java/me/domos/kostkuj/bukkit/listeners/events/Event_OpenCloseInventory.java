package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.general.connect.mysql.sklad.MSklad;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class Event_OpenCloseInventory implements Listener {

    MSklad mSklad = new MSklad();

    @EventHandler
    public void openInv(InventoryOpenEvent e){
        if (e.getView().getTitle().contains("§6Sklad: ")){
            String pl = e.getView().getTitle().replace("§6Sklad: §8", "");
            OfflinePlayer op = Bukkit.getOfflinePlayer(pl);
            mSklad.openSklad(op.getName());
        }
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent e){
        if (e.getView().getTitle().contains("§6Sklad: ")){
            String pl = e.getView().getTitle().replace("§6Sklad: §8", "");
            OfflinePlayer op = Bukkit.getOfflinePlayer(pl);
            for (int i = 0; i < e.getInventory().getStorageContents().length; i++){
                if (e.getInventory().getStorageContents()[i] != null){
                }
            }
            mSklad.closeSklad(op.getName());
        }
    }
}