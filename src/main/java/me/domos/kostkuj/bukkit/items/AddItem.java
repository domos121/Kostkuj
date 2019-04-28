package me.domos.kostkuj.bukkit.items;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.models.craftCoinModel.CCTransactionTyp;
import me.domos.kostkuj.models.craftCoinModel.CraftCoin;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddItem {

    private SendSystem ss = new SendSystem();
    private ItBuilder ib = ItBuilder.getInstance();


    public boolean add(ItemStack item, Player p){
        if (item.getItemMeta().getDisplayName().contains("CraftCoin_")) {
            String pocet = item.getItemMeta().getDisplayName().replace("§6CraftCoin_§a", "").replace(",- CC", "");
            String kolo = item.toString();
            String potreba = ib.craftCoin(pocet).toString();
            if (kolo.equalsIgnoreCase(potreba)){
                ss.info(p, "Obdržel jsi §a'§r" + item.getItemMeta().getDisplayName() + "§a'§8 x §a" + item.getAmount());
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_BOTTLE_THROW,1F, 0.5F);
                CraftCoin craftCoin = new CraftCoin(Integer.parseInt(pocet));
                craftCoin.updateCraftCoin(p, CCTransactionTyp.GAME);
            }
            return false;
        } else {
            if (item.getItemMeta().getDisplayName().equals("")) {
                ss.info(p, "Obdržel jsi §a'" + item.getType().name() + "'§8 x §a" + item.getAmount());
            } else {
                ss.info(p, "Obdržel jsi §a'§r" + item.getItemMeta().getDisplayName() + "§a'§8 x §a" + item.getAmount());
            }
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP,1F, 0.5F);
            p.getInventory().addItem(item);
        }
        return true;
    }
}
