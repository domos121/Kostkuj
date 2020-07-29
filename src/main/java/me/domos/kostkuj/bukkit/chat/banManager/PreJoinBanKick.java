package me.domos.kostkuj.bukkit.chat.banManager;

import me.domos.kostkuj.bukkit.player.PlayerManager;
import me.domos.kostkuj.bukkit.time.Time;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.PlayerPreLoginEvent;

import java.sql.Timestamp;
import java.util.*;

import static org.bukkit.event.player.PlayerPreLoginEvent.Result.KICK_BANNED;

public class PreJoinBanKick {

    private Time time = new Time();

    private PlayerManager pm = new PlayerManager();

    @Deprecated
    public void fcSetIpAndUuidBanKick(PlayerPreLoginEvent p, int ipid, int id, Timestamp set, Timestamp expire, String admin, String disc, String typbanu, HashMap<Integer, String> obj) {

        String expires;
        if (expire != null) {
            expires = time.getTimeFromTimeStamp(expire);
        } else {
            expires = "Nikdy.";
        }
        String ip = "";
        if(!typbanu.contains("temp")) {
           expires = "Nikdy.";
        }
        if(ipid == 0){
            ip = "Zadna.";
        } else if (ipid > 0){
            ip = ipid + "";
        }
        if (disc == null || disc.equalsIgnoreCase("")){
            disc = "Zadny.";
        }
        UUID uuuid = UUID.fromString(admin);
        Set sets = obj.entrySet();
        String rule = "";
        Iterator inter = sets.iterator();
        while (inter.hasNext()){
            Map.Entry mtrey2 = (Map.Entry)inter.next();
            rule = rule + mtrey2.getKey() + ", ";
        }
        OfflinePlayer op = Bukkit.getOfflinePlayer(uuuid);
        p.disallow(KICK_BANNED, "§6========== §c" + p.getName() + "§6 ==========\n" +
                "§cTREST:\n" +
                "§cIDTrestu: §7#" + id + "\n" +
                "§cTyp trestu: §7" + typbanu + "\n" +
                "§cDatum trestu: §7" + time.getTimeFromTimeStamp(set) + "\n" +
                "§cDatum expirace:§7 " + expires + "\n" +
                "§cTrestajici:§7 " + pm.getNameFromUuid(uuuid) + "\n" +
                "§cPoruseni pravidel:§7 " + rule + "\n" +
                "§cPopis:§7 " + disc + "\n" +
                "§6================================================\n\n" +
                "§7Unban lze zakoupit na našem webu §awww.kostkuj.cz§7.\n\n" +
                "§6V případě nesouhlasu se lze obrátit na vedení serveru.");
        //sendBanMessage.sendBanJoinMessage(p.getName().trim(), op.getName(), typbanu, time.getTimeFromTimeStamp(set), expires, obj, id, disc, rule);
    }

}
