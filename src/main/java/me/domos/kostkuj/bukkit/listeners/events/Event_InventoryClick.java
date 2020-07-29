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
            Player ps = (Player) e.getWhoClicked();
            if (ps.getOpenInventory().getTitle().contains("_GiftChest")) {
                e.setCancelled(true);
            }
        }

        Player p = (Player)e.getWhoClicked();
        Inventory open = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        if(open == null){
            return;
        }
        if (e.getView().getTitle().contains("VoteGift:")){
            String user = e.getView().getTitle().replace("§8VoteGift:§c ", "");
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
