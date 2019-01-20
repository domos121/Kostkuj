package by.domos.Kostkuj.server.player.event;

import by.domos.Kostkuj.connect.MySQL.MySQL;
import by.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLget;
import by.domos.Kostkuj.enums.EMessages;
import by.domos.Kostkuj.server.chat.BanManager.PreJoinBanKick;
import by.domos.Kostkuj.server.player.ipmanager.IpHasher;
import by.domos.Kostkuj.server.trests.GetTrest;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerPreLoginEvent;

import static org.bukkit.event.player.PlayerPreLoginEvent.Result.KICK_OTHER;

public class EventPlayerPreLogin {
    MySQLget mysqlget = new MySQLget();
    PreJoinBanKick preJoinBanKick = new PreJoinBanKick();
    MySQL mysql = new MySQL();

    @Deprecated
    public void checkEmailConfirm(PlayerPreLoginEvent a) {


        String uuid = a.getUniqueId().toString();
        String name = a.getName().trim();
        int hodnota = mysqlget.getConfirm(uuid);
        PlayerPreLoginEvent.Result result = KICK_OTHER;
        a.setResult(KICK_OTHER);
        if (hodnota == -1) {
            a.disallow(result, "");
            a.setKickMessage(ChatColor.translateAlternateColorCodes('&', EMessages.USER_ISNT_REGISTERED.getValue().replace("{player}", name)));
        } else if (hodnota == 0) {
            a.disallow(result, "");
            a.setKickMessage(ChatColor.translateAlternateColorCodes('&', EMessages.USER_ISNT_AKTIV.getValue().replace("{player}", name)));
        } else if (hodnota <= -2 || hodnota > 1) {
            mysql.connect();
            a.disallow(result, "");
            a.setKickMessage(ChatColor.translateAlternateColorCodes('&', EMessages.USER_ERROR.getValue().replace("{player}", name)));
        } else {

            IpHasher ih = IpHasher.getInstance();

            String ip = a.getAddress().toString().trim().replace("/", "");

            String hash = ih.hashIp(ip);

            GetTrest gt = new GetTrest(uuid, hash);

            if (gt.isIsbanned()){
                preJoinBanKick.fcSetIpAndUuidBanKick(a, gt.getIpid(), gt.getIdtrestu(), gt.getSettime(), gt.getExpirytime(), gt.getAdmin(), gt.getDicription(), gt.getTyptrestu(), gt.getObj());
            } else {
                a.allow();
            }

        }
    }
}
