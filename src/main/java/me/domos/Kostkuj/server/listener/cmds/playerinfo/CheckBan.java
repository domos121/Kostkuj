package me.domos.Kostkuj.server.listener.cmds.playerinfo;

import me.domos.Kostkuj.connect.MySQL.trests.MCheckBan;
import me.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CheckBan implements CommandExecutor {
    private SendSystem ss = new SendSystem();
    private MCheckBan mcb = new MCheckBan();
    private MTrestsIP mti = new MTrestsIP();

    public CheckBan() {
    }

    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission("kostkuj.checkban")) {
            this.ss.noPerm(sr);
            return true;
        } else if (args.length == 0) {
            this.ss.info(sr, "Use: /checkban <[nick] | id:[id trestu] | ip:[ipid]>");
            return true;
        } else {
            String ip;
            int ids;
            if (args[0].contains("id:")) {
                ip = args[0].replace("id:", "");
                if (!StringUtils.isNumeric(ip)) {
                    this.ss.info(sr, "Id není číslo.");
                    return true;
                }

                ids = Integer.parseInt(ip);
                sr.sendMessage(" §8===== §7CheckBan: §c#" + ip + " §8=====");
                this.mcb.getTrest(ids, sr);
            } else if (args[0].contains("ip:")) {
                ip = args[0].replace("ip:", "");
                if (!StringUtils.isNumeric(ip)) {
                    this.ss.info(sr, "Ip není číslo.");
                    return true;
                }

                sr.sendMessage(" §8===== §7CheckBan: §cip:" + ip + " §8=====");
                this.mcb.getTrest("", Integer.parseInt(ip), sr);
            } else {
                OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
                ids = this.mti.getLastIP(op.getUniqueId().toString());
                sr.sendMessage(" §8===== §7CheckBan: §c" + op.getName() + " §8=====");
                this.mcb.getTrest(op.getUniqueId().toString(), ids, sr);
            }

            return false;
        }
    }
}
