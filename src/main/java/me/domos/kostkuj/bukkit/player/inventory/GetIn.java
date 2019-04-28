package me.domos.kostkuj.bukkit.player.inventory;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.general.connect.mysql.vote.MysqlListener;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GetIn implements Listener {

    private Main plugin = Main.getPlugin(Main.class);
    MysqlListener lis = new MysqlListener();

    public void inv(Player p, String user){

        List<ItemStack> items = lis.getSkladForInv(user);

        if (items == null){
            p.sendMessage("Tvuj sklad je prazdny. Sklad se plni pouze pokud nemas misto v inentari, nebo jsi offline.");
            return;
        }

        Inventory i = plugin.getServer().createInventory(null, 27, "§8VoteGift:§c " + user);

        for (int u = 0; u < items.size(); u++) {
            i.setItem(u, items.get(u));
        }
        p.openInventory(i);
    }
}
