package me.domos.kostkuj.models.inventoryMenuModel;

import me.domos.kostkuj.Main;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class InventoryBuilder {

    private Main plugin = Main.getPlugin(Main.class);

    private InventoryPaginator inventoryPaginator;
    private InventoryMenu inventoryMenu;
    private Inventory inventory;
    private HashMap<Integer, InventoryItem> itemsMap;

    public InventoryBuilder(HashMap<Integer, InventoryItem> itemsMap, InventoryMenu inventoryMenu){
        this.inventoryMenu = inventoryMenu;
        this.itemsMap = itemsMap;
        inventoryFiller();
    }

    private void inventoryFiller(){
        int inventoryItems = (this.inventoryMenu.getInventorySize()-9)*(inventoryPaginator.getPage()-1);
        Inventory inventory = plugin.getServer().createInventory(null, this.inventoryMenu.getInventorySize(), this.inventoryMenu.getInventoryName());
        Set sets = itemsMap.entrySet();
        Iterator iterator = sets.iterator();
        this.inventory = inventory;
    }

    private void inventoryPaginator(int page){
        this.inventoryPaginator = new InventoryPaginator(this.inventoryMenu.getInventorySize(), page ,this.inventoryMenu.getItemsMap().size());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setPaginator(int page) {
        inventoryPaginator(page);
        inventoryFiller();
    }
}
