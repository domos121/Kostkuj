package me.domos.kostkuj.models.voteModel;

import me.domos.kostkuj.bukkit.items.AddItem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.general.connect.mysql.vote.MysqlListener;
import me.domos.kostkuj.general.fileManager.EMessages;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VoteSklad {

    MysqlListener lis = new MysqlListener();
    ItBuilder it = ItBuilder.getInstance();
    AddItem ai = new AddItem();

    private boolean freeSlot(Player p) {
        for (int i = 0; i < 36; i++) {
            if (p.getInventory().getItem(i) == null) {
                return true;
            }
        }
        return false;
    }

    public boolean vyber(Player p, String user, String product, int pocet, int id){

        if (!freeSlot(p)){
            p.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + " Nemas volny slot v inventari");
            return false;
        }

        lis.deleteStorrageId(id);
        ItemStack item = it.key("TRIPWIRE_HOOK", pocet, product);
        ai.add(item, p);
        return true;
    }
}
