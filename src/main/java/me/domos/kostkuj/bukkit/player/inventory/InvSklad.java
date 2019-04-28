package me.domos.kostkuj.bukkit.player.inventory;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.general.connect.mysql.sklad.MSklad;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvSklad {


    private MSklad sk = new MSklad();
    private Main plugin = Main.getPlugin(Main.class);

    public boolean openSklad(Player p){

        int chestsize = sk.getUserChest(p);

        if (chestsize == 0){
            sk.createUserChest(p);
            p.sendMessage("§6Byl ti vygenerovanej sklad.");
            return true;
        } else if (chestsize > 0) {

        } else {
            return true;
        }

        p.sendMessage("§6Otevřel jsi sklad.");

        Inventory i =  plugin.getServer().createInventory(null, chestsize, "§6Sklad: §8" + p.getName());

        p.openInventory(i);

        return true;
    }

}
