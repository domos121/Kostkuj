package me.domos.Kostkuj.server.listener.cmds.playerinfo;

import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLis;
import me.domos.Kostkuj.connect.MySQL.player.Bans.MySQLJsonBuilder;
import me.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.trests.GetTrest;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckIp implements CommandExecutor {

    SendSystem ss = new SendSystem();
    MySQLis mysqlis = new MySQLis();
    MySQLJsonBuilder mySQLJsonBuilder = new MySQLJsonBuilder();
    MTrestsIP mti = new MTrestsIP();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String commandLabel, String[] args) {
        if (!sr.hasPermission("kostkuj.checkip")) {
            ss.noPerm(sr);
            return true;
        }

        if (!(sr instanceof Player)) {
            ss.info(sr, "Tento prikaz nelze vykona z trminalu!");
            return true;
        }

        if (args.length == 0) {
            ss.info(sr, "Use: /checkip <ip>");
            return true;
        }

        if (!StringUtils.isNumeric(args[0])) {
            ss.info(sr, "Spatne zadaná IP!");
            return true;
        }

        int args0 = Integer.parseInt(args[0]);

        GetTrest gt = new GetTrest("", args0);

        if (!mti.isIpExist(args0)) {
            ss.info(sr, "Ip neexistuje!");
            return true;
        } else {
            String ipbanned = ss.boolenTranslate(gt.isIsbanned());
            String json = "{\n" +
                    "  \"text\":\"§8======§7 CheckIP: §c#ip# §8======\\n §8- §7Ip: §c #ip#\\n §8- §7IsIpBanned: §c#ipbanned#\\n§7Souvisejici ucty:\"".replace("#ip#", args[0]).replace("#ipbanned#", ipbanned) +
                    "},";
            mySQLJsonBuilder.getNameForCheckIp(args0, json, sr);
        }
        return true;
    }
}