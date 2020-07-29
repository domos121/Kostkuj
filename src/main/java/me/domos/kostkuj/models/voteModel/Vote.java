package me.domos.kostkuj.models.voteModel;

import me.domos.kostkuj.bukkit.items.AddItem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.general.connect.mysql.vote.MysqlListener;
import me.domos.kostkuj.general.fileManager.ConfigVote;
import me.domos.kostkuj.general.fileManager.EMessages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class Vote {

    private Vote() {}

    static Vote instance = new Vote();

    public static Vote getInstance() {
        return instance;
    }

    private AddItem ai = new AddItem();

    private MysqlListener lis = new MysqlListener();
    private ConfigVote cVote = ConfigVote.getInstance();
    private ItBuilder it = ItBuilder.getInstance();

    private boolean freeSlot(OfflinePlayer op) {
        for (int i = 0; i < 36; i++) {
            if (op.getPlayer().getInventory().getItem(i) == null) {
                return true;
            }
        }
        return false;
    }

    public void VoteIsOnline(String user){
        OfflinePlayer op = Bukkit.getOfflinePlayer(user);
        Player p = Bukkit.getPlayer(user);

        if(op.isOnline()){
          Vote(p);
        } else {
          Vote(op);
        }
    }

    public void Vote(Player p){

        //List<String> key = cVote.getItems();

        List<String> key = cVote.getItems();

        Random r = new Random();

        String[] ite = key.get(r.nextInt(key.size())).split(",");

        ItemStack item = it.key("TRIPWIRE_HOOK", Integer.parseInt(ite[0]), ite[1]);

        OfflinePlayer op = Bukkit.getOfflinePlayer(p.getName());

        lis.setVote(p.getName());

        if (p.getName().trim().equalsIgnoreCase("Menshons")){
            p.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + "§dLove you to the Moon and back! MucQ!");
            return;
        }

        p.sendMessage(EMessages.PLUGIN_PREFIX.getValue() + "Dekujeme za tvou podporu!\n"
                + "§7 Prispel si: §a" + lis.getVotes(p.getName()) + " §7hlasu.");

        if (!freeSlot(op)){
            op.getPlayer().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + "Tvuj inventar byl plnej. Polozka se ti vlozila do skladu. Pro vybrani skladu pouzij /Vote sklad");
            lis.setStorrage(op.getName(), ite[0] + "," + ite[1]);
            return;
        }

        ai.add(item, p);
    }

    public void Vote(OfflinePlayer op){

        List<String> key = cVote.getItems();

        Random r = new Random();

        String ite = key.get(r.nextInt(key.size()));

        lis.setVote(op.getName());
        lis.setStorrage(op.getName(), ite);
    }
}
