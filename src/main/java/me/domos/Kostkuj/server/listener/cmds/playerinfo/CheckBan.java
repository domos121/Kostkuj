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

    SendSystem ss = new SendSystem();
    MCheckBan mcb = new MCheckBan();
    MTrestsIP mti = new MTrestsIP();

    // /checkban id:nick | [nick]

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission("kostkuj.checkban")) {
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0){
            ss.info(sr, "Use: /checkban <[nick] | id:[id trestu] | ip:[ipid]>");
            return true;
        }
        if (args[0].contains("id:")){
            String id = args[0].replace("id:", "");
            if (!StringUtils.isNumeric(id)) {
                ss.info(sr, "Id není číslo.");
                return true;
            }
            int ids = Integer.parseInt(id);
            sr.sendMessage(" §8===== §7CheckBan: §c#" + id + " §8=====");
            mcb.getTrest(ids, sr);
        } else if(args[0].contains("ip:")){
            String ip = args[0].replace("ip:", "");
            if (!StringUtils.isNumeric(ip)) {
                ss.info(sr, "Ip není číslo.");
                return true;
            }
            sr.sendMessage(" §8===== §7CheckBan: §cip:" + ip + " §8=====");
            mcb.getTrest("", Integer.parseInt(ip), sr);
        } else {
            OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
            int ip = mti.getLastIP(op.getUniqueId().toString());
            sr.sendMessage(" §8===== §7CheckBan: §c" + op.getName() + " §8=====");
            mcb.getTrest(op.getUniqueId().toString(), ip, sr);
        }
        return false;
    }
}
