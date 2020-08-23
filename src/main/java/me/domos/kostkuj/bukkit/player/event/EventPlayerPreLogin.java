package me.domos.kostkuj.bukkit.player.event;

import me.domos.kostkuj.bukkit.chat.banManager.PreJoinBanKick;
import me.domos.kostkuj.bukkit.player.KPlayerSettings;
import me.domos.kostkuj.bukkit.player.ipmanager.IpHasher;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import me.domos.kostkuj.general.connect.mysql.mysqlListener.MySQLget;
import me.domos.kostkuj.general.fileManager.ECfg;
import me.domos.kostkuj.general.fileManager.EMessages;
import me.domos.kostkuj.models.banModel.GetTrest;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerPreLoginEvent;

import static org.bukkit.event.player.PlayerPreLoginEvent.Result.KICK_OTHER;

public class EventPlayerPreLogin {
    MySQLget mysqlget = new MySQLget();
    PreJoinBanKick preJoinBanKick = new PreJoinBanKick();
    MySQL mysql = new MySQL();
    KPlayerSettings kps = new KPlayerSettings();

    @Deprecated
    public void checkEmailConfirm(PlayerPreLoginEvent a) {
        String uuid = a.getUniqueId().toString();
        String name = a.getName().trim();
        int hodnota = mysqlget.getConfirm(uuid);
        PlayerPreLoginEvent.Result result = KICK_OTHER;
        a.setResult(KICK_OTHER);
        KPlayerSettings.LoginTimeBuffer loginBuffer = kps.getLoginTime(a.getName());
        if (loginBuffer.isAcces()){
            a.disallow(result, "");
            a.setKickMessage("§6Login time out §a" + (ECfg.LOGIN_TIME_OUT_TIME.getInt()-loginBuffer.getTime()) + "s§6!");
            return;
        }
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

            if (gt.isIsbanned() && !a.getName().equalsIgnoreCase("domos121")){
                preJoinBanKick.fcSetIpAndUuidBanKick(a, gt.getIpid(), gt.getIdtrestu(), gt.getSettime(), gt.getExpirytime(), gt.getAdmin(), gt.getDicription(), gt.getTyptrestu(), gt.getObj());
            } else {
                a.allow();
            }

        }
    }
}
