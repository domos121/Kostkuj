package me.domos.kostkuj.models.inventoryMenuModel;

import org.bukkit.inventory.ItemStack;

public class InventoryItem {

    private ItemStack item;
    private String clickEventAction;

    public InventoryItem(ItemStack item, String clickEventAction){
        this.item = item;
        this.clickEventAction = clickEventAction;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getClickEventAction() {
        return clickEventAction;
    }
}
