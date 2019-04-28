package me.domos.kostkuj.bukkit.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EnchBuilder {

    private EnchBuilder() {}
    static EnchBuilder instance = new EnchBuilder();
    public static EnchBuilder getInstance() {
        return instance;
    }

    // mending,1;unbreaking,5;

    public String[][] ench(String ench){
        String[] radekEnch = ench.split(";");
        // mending,1
        // unbreaking,5
        ArrayList<String> arrayEnch = new ArrayList<String>();

        for (int u = 0; u < radekEnch.length; u++){
            arrayEnch.add(radekEnch[u]);
        }
        String[][] poleEnch = new String[arrayEnch.size()][];
        for (int i = 0; i < arrayEnch.size(); i++){
            poleEnch[i] = arrayEnch.get(i).split(",");
        }
        return poleEnch;
    }

    public ItemMeta iEnch(String ench){
        String[][] enchants = ench(ench);
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        for (int i = 0; enchants.length > i; i++) {
            meta.addEnchant(Enchantment.getByName(enchants[i][0]), Integer.parseInt(enchants[i][1]), true);
        }
        return meta;
    }

    public EnchantmentStorageMeta bEnch(String ench){
        String[][] enchants = ench(ench);
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        for (int i = 0; enchants.length > i; i++) {
            meta.addStoredEnchant(Enchantment.getByName(enchants[i][0]), Integer.parseInt(enchants[i][1]), true);
        }
        return meta;
    }
}
