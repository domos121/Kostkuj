package me.domos.kostkuj.bukkit.listeners.events;

import me.domos.kostkuj.models.voteModel.VoteSklad;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Event_InventoryClick implements Listener {

    VoteSklad v = new VoteSklad();

    @EventHandler
    public void click(InventoryClickEvent e){
        if (e.getClickedInventory() != null) {
            if (e.getClickedInventory().getName().contains("_GiftChest")) {
                e.setCancelled(true);
            }
        }


        Player p = (Player)e.getWhoClicked();
        Inventory open = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        if(open == null){
            return;
        }
        if (e.getInventory().getName().contains("VoteGift:")){
            if (e.getClickedInventory().getName().equalsIgnoreCase("Inventory")){
                e.setCancelled(true);
                return;
            }
            String user = open.getName().replace("§8VoteGift:§c ", "");
            e.setCancelled(true);

            if (item == null || !item.hasItemMeta()){
                return;
            }
            List<String> lore = item.getItemMeta().getLore();

            if (v.vyber(p, user,  item.getItemMeta().getDisplayName(), item.getAmount(), Integer.parseInt(lore.get(0)))){
                open.setItem(e.getRawSlot(), null);
            }
        }
    }
}
