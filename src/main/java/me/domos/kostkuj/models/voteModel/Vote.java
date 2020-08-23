package me.domos.kostkuj.models.voteModel;

import com.google.gson.JsonObject;
import me.domos.kostkuj.bukkit.items.AddItem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.vote.MysqlListener;
import me.domos.kostkuj.general.connect.urlReader.UrlRead;
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

        UrlRead urlRead = new UrlRead();
        String keyMonth = Time.getTimeDay(0).toString().substring(0,7).replace("-", "/");
        JsonObject activeVote = urlRead.urlRead("https://czech-craft.eu/api/server/kostkuj/player/" + p.getName().trim() + "/");
        JsonObject monthVotes = urlRead.urlRead("https://czech-craft.eu/api/server/kostkuj/player/" + p.getName().trim() + "/" + keyMonth + "/");
        int activeVotes = activeVote.get("vote_count").getAsInt();
        String nextVote = activeVote.get("next_vote").getAsString();
        int monthVoteCount = monthVotes.get("vote_count").getAsInt();

        String msg = "§6========[§aVote Kostkuj.cz§6]========\n"
                + "§7Celkem hlasů: §a" + new MysqlListener().getVotes(p.getName().trim()) + "§7.\n"
                + "§7Nyní máš: §a" + activeVotes + " §7aktivních hlasů.\n"
                + "§7Hlasů za tento měsíc: §a " + monthVoteCount + "§7.\n"
                + "§7Znovu můžeš hlasovat: §a" + nextVote + "§7.\n";

        p.sendMessage(msg);

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

    public boolean isVote(String username){
        Time time = new Time();
        JsonObject object = new UrlRead().urlRead("https://czech-craft.eu/api/server/kostkuj/player/" + username + "/next_vote/");
        if (time.getTimeSec(0).after(time.getTimeStumpFromString(object.get("next_vote").toString().replace("\"", "")))){
            return true;
        }
        return false;
    }
}
