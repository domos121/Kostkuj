package me.domos.kostkuj.models.inventoryMenuModel;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryPaginator {

    private SendSystem ss = new SendSystem();
    private ItBuilder itBuilder = ItBuilder.getInstance();


    private int items;
    private int inventorySize;
    private int paginatorSize = 9;

    //Page
    private int page = 1;
    private int maxPage;
    private int nextPage;
    private int prevPage;
    private boolean isFirst = false;
    private boolean isLast = false;
    private List<ItemStack> paginatorItems;

    public InventoryPaginator(int inventorySize, int page, int items){
        this.inventorySize = inventorySize;
        this.page = page;
        this.items = items;
        pageMath();
        pageItemsCreator();
        ss.debugMsg("Create InentoryPaginator.\n" +
                "maxPage: " + maxPage + "\n" +
                "items: " + items + "\n" +
                "paginatorSize" + paginatorSize);
    }

    private void pageMath(){
        pageMathMaxPage();
        pageMathPrevPage();
        pageMathNextPage();
    }

    private void pageMathPrevPage() {
        if (this.page == 1){
            this.isFirst = true;
            this.prevPage = this.page;
        } else {
            this.prevPage = --this.page;
        }
    }

    private void pageMathNextPage(){
        if (this.page == maxPage){
            this.isLast = true;
            this.nextPage = this.page;
        } else {
            this.nextPage = this.page++;
        }
    }

    private void pageMathMaxPage(){
        int inventorySize = this.inventorySize - this.paginatorSize;
        int allItems = this.items;
        int maxPage = 0;

        while (allItems >= inventorySize){
            maxPage++;
            allItems = allItems-inventorySize;
        }
        if (allItems > 0){
            maxPage++;
        }
        this.maxPage = maxPage;
    }

    private void pageItemsCreator(){
        ItemStack nextPageItem = itBuilder.setName(itBuilder.item(Material.RED_WOOL, 1), "&cNextPage&a>>");
        ItemStack prevPageItem = itBuilder.setName(itBuilder.item(Material.RED_WOOL, 1), "&a<<&cPrevPage");
        ItemStack pageItem = itBuilder.setName(itBuilder.item(Material.RED_WOOL, 1), "&c" + page + "&a/&c" + maxPage);
        ItemStack item = itBuilder.setName(itBuilder.item(Material.WHITE_WOOL, 1), "-");
        List<ItemStack> items = new ArrayList<>();
        items.add(item);
        items.add(item);
        items.add(item);
        items.add(prevPageItem);
        items.add(pageItem);
        items.add(nextPageItem);
        items.add(item);
        items.add(item);
        items.add(item);
        this.paginatorItems = items;
    }

    public List<ItemStack> getPaginatorItems() {
        return paginatorItems;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isLast() {
        return isLast;
    }

    public int getPage(){
        return page;
    }
}
