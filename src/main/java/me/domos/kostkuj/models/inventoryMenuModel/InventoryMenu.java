package me.domos.kostkuj.models.inventoryMenuModel;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class InventoryMenu {
    // Inventář jde 0 - 53

    private SendSystem ss = new SendSystem();
    private Player playersInventory;
    private String inventoryName;
    private int inventorySize;
    private HashMap<Integer, InventoryItem> itemsMap = new HashMap<Integer, InventoryItem>();

    private InventoryBuilder inventoryBuilder;
    private Inventory inventory;

    public InventoryMenu(Player playersInventory, String inventoryName, int inventorySize){
        for (int i = 0; i <= 70; i++){
            ItemStack item = new ItemStack(Material.DIAMOND, 1);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName("Položka č." + i);
            item.setItemMeta(im);
            itemsMap.put(i, new InventoryItem(item, "test"));
        }
        this.playersInventory = playersInventory;
        this.inventoryName =  inventoryName;
        this.inventorySize = inventorySize;
        ss.debugMsg("Create InventoryMenu.");
        this.inventoryBuilder = new InventoryBuilder(itemsMap, this);
    }

    public void openInventory(){
        this.inventory = inventoryBuilder.getInventory();
        playersInventory.openInventory(inventory);
    }

    public InventoryBuilder inventoryBuilder(){
        return inventoryBuilder;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public HashMap<Integer, InventoryItem> getItemsMap() {
        return itemsMap;
    }
}
