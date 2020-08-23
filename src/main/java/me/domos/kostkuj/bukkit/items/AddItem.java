package me.domos.kostkuj.bukkit.items;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.models.craftCoinModel.CCTransactionTyp;
import me.domos.kostkuj.models.craftCoinModel.CraftCoin;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
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
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_BOTTLE_THROW,1F, 0.5F);
                CraftCoin craftCoin = new CraftCoin(Integer.parseInt(pocet));
                craftCoin.updateCraftCoin(p, CCTransactionTyp.GAME);
                ss.info(p, "Obdržel jsi §a'§r" + item.getItemMeta().getDisplayName() + "§a'§8 x §a" + item.getAmount());
            }
            return true;
        } else {
            p.getInventory().addItem(item);
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP,1F, 0.5F);
            if (item.getItemMeta().getDisplayName().equals("")) {
                ss.info(p, "Obdržel jsi §a'" + item.getType().name() + "'§8 x §a" + item.getAmount());
            } else {
                ss.info(p, "Obdržel jsi §a'§r" + item.getItemMeta().getDisplayName() + "§a'§8 x §a" + item.getAmount());
            }
        }
        return true;
    }

    public boolean add(Player p, int count, String minecraftId, String nbt){
        CraftPlayer player = (CraftPlayer) p;

        NBTTagCompound ntb = null;
        try {
            ntb = MojangsonParser.parse(nbt);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        MinecraftKey minecraftKey = new MinecraftKey(minecraftId.toLowerCase());

        net.minecraft.server.v1_16_R1.ItemStack item = new net.minecraft.server.v1_16_R1.ItemStack(IRegistry.ITEM.get(minecraftKey));
        item.setTag(ntb);
        item.setCount(count);

        ss.info(p, "obdržel si §a'§r" + item.getItem().getName() + "§a'§8 x §a" + item.getCount());

        EntityItem entityItem = player.getHandle().drop(item, false);
        entityItem.setPickupDelay(0);
        entityItem.setOwner(p.getUniqueId());
        return true;
    }
}
