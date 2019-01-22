package me.domos.Kostkuj.server.listener.cmds.playerinfo;

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
    private SendSystem ss = new SendSystem();
    private MySQLJsonBuilder mySQLJsonBuilder = new MySQLJsonBuilder();
    private MTrestsIP mti = new MTrestsIP();

    public CheckIp() {
    }

    public boolean onCommand(CommandSender sr, Command cmd, String commandLabel, String[] args) {
        if (!sr.hasPermission("kostkuj.checkip")) {
            this.ss.noPerm(sr);
            return true;
        } else if (!(sr instanceof Player)) {
            this.ss.info(sr, "Tento prikaz nelze vykona z trminalu!");
            return true;
        } else if (args.length == 0) {
            this.ss.info(sr, "Use: /checkip <ip>");
            return true;
        } else if (!StringUtils.isNumeric(args[0])) {
            this.ss.info(sr, "Spatne zadaná IP!");
            return true;
        } else {
            int args0 = Integer.parseInt(args[0]);
            GetTrest gt = new GetTrest("", args0);
            if (!this.mti.isIpExist(args0)) {
                this.ss.info(sr, "Ip neexistuje!");
                return true;
            } else {
                String ipbanned = this.ss.boolenTranslate(gt.isIsbanned());
                String json = "{\n" + "  \"text\":\"§8======§7 CheckIP: §c#ip# §8======\\n §8- §7Ip: §c #ip#\\n §8- §7IsIpBanned: §c#ipbanned#\\n§7Souvisejici ucty:\"".replace("#ip#", args[0]).replace("#ipbanned#", ipbanned) + "},";
                this.mySQLJsonBuilder.getNameForCheckIp(args0, json, sr);
                return true;
            }
        }
    }
}