package me.domos.kostkuj.bukkit.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ItBuilder {

    private ItBuilder() {}
    static ItBuilder instance = new ItBuilder();
    public static ItBuilder getInstance() {
        return instance;
    }

    EnchBuilder eb = EnchBuilder.getInstance();

    public ItemStack item(Material mat, int pocet){
        ItemStack item = new ItemStack(mat, pocet);
        return item;
    }

    public ItemStack item(Material mat, byte meta, int pocet, BookMeta bookMeta){
        ItemStack item = new ItemStack(mat, pocet, meta);
        item.setItemMeta(bookMeta);
        return item;
    }

    public ItemStack item(Material mat, int pocet, ItemMeta itemMeta){
        ItemStack item = new ItemStack(mat, pocet);
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStack item(Material mat, int pocet, EnchantmentStorageMeta itemMeta){
        ItemStack item = new ItemStack(mat, pocet);
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStack setName(ItemStack itemStack, String name){
        ItemStack ite = itemStack;
        ItemMeta im = ite.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        ite.setItemMeta(im);
        return ite;
    }

    public ItemStack buildItem(String[] pitem, String[] pitem2){
        if (pitem2.length == 4 && pitem[0].equalsIgnoreCase("403")){
            ItemStack enchbook = item(Material.getMaterial(pitem[0]), Integer.parseInt(pitem2[2]), eb.bEnch(pitem2[3]));
            return enchbook;
        }else if(pitem2.length == 4){
            ItemStack enchitem = item(Material.getMaterial(pitem[0]), Integer.parseInt(pitem2[2]), eb.iEnch(pitem2[3]));
            return enchitem;
        } else {
            ItemStack normitem = item(Material.getMaterial(pitem[0]), Integer.parseInt(pitem2[2]));
            return normitem;
        }
    }

    public ItemStack enchBook(String item, int pocet, String ench){
        ItemStack enchbook = item(Material.getMaterial(item), pocet, eb.bEnch(ench));
        return enchbook;
    }

    public ItemStack normitem(String item, int pocet){
        ItemStack enchbook = item(Material.getMaterial(item), pocet);
        return enchbook;
    }

    public ItemStack enchitem(String item, int pocet, String ench){
        ItemStack enchitem = item(Material.getMaterial(item), pocet, eb.iEnch(ench));
        return enchitem;
    }

    public ItemStack key(String mat, int pocet, String name){
        ItemStack item = new ItemStack(Material.getMaterial(mat), pocet);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack craftCoin(String name){
        ItemStack item = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6CraftCoin_§a" + name + ",- CC");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, true);
        item.setItemMeta(meta);
        return item;
    }

    public MapAndChance koloItems(List<String> it){
        List<ItemStack> listItems = new ArrayList<>();
        MapAndChance mapAndChance = new MapAndChance();
        HashMap<Integer, ItemStack> items = new HashMap<>();
        int totalchance = 0;
        for (int i = 0; it.size() > i; i++){
            String[] splitToItem = it.get(i).split("%");
            int chance = Integer.parseInt(splitToItem[1]);
            totalchance = totalchance + chance;
            if (splitToItem[0].contains("CRAFTCOINS_")){
                String[] name = splitToItem[0].split(",");
                ItemStack item = craftCoin(name[1]);
                items.put(totalchance,item);
                listItems.add(item);
            } else if (splitToItem[0].contains("PREMIUM_")){

            } else if (splitToItem[0].contains("ENCHANTED_BOOK")){
                String[] name = splitToItem[0].split(",");
                String names = name[1];
                for (int is = 2; name.length > is; is++){
                    names = names + ";" + name[is];
                }
                ItemStack item = enchBook("ENCHANTED_BOOK", 1, names.replace("|", ","));
                items.put(totalchance,item);
                listItems.add(item);
            } else if(splitToItem[0].contains("EITEM_")) {
                String[] name = splitToItem[0].split(",");
                String names = name[3];
                for (int is = 4; name.length > is; is++){
                    names = names + ";" + name[is];
                }
                ItemStack item = enchitem(name[1], Integer.parseInt(name[2]), names.replace("|", ","));
                items.put(totalchance,item);
                listItems.add(item);
            }  else if (splitToItem[0].contains("KEY_")){
                String[] name = splitToItem[0].split(",");
                ItemStack item = key("TRIPWIRE_HOOK", Integer.parseInt(name[1]), name[2]);
                items.put(totalchance,item);
                listItems.add(item);
            } else {
                String[] name = splitToItem[0].split(",");
                ItemStack item = item(Material.getMaterial(name[0]), Integer.parseInt(name[1]));
                items.put(totalchance,item);
                listItems.add(item);
            }
        }
        mapAndChance.setItems(items, totalchance, listItems);
        return mapAndChance;
    }

    public class MapAndChance{
        private HashMap<Integer, ItemStack> items;
        private Integer maxChance;
        private List<ItemStack> listItems;

        public void setItems(HashMap<Integer, ItemStack> items, Integer maxChance, List<ItemStack> listItems){
            this.listItems = listItems;
            this.items = items;
            this.maxChance = maxChance;
        }

        public Integer getChance() {
            return maxChance;
        }

        public HashMap<Integer, ItemStack> getItems() {
            return items;
        }

        public List<ItemStack> getListItems() {
            return listItems;
        }

        public ItemStack losItem(){
            Random random = new Random();
            int ranNum = random.nextInt(this.maxChance);
            if (this.items.containsKey(ranNum)){
                return this.items.get(ranNum);
            } else {
                for (int i = ranNum; i <= maxChance; i++){
                    if (this.items.containsKey(i)){
                        return this.items.get(i);
                    }
                }
            }
            return null;
        }
    }
}