package by.domos.Kostkuj.server.listener.cmds.bans;

import by.domos.Kostkuj.connect.MySQL.trests.MTrests;
import by.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import by.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import by.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.trests.GetTrest;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnBan implements CommandExecutor {
    SendSystem ss = new SendSystem();
    MTrestsIP mti = new MTrestsIP();
    MTrests mt = new MTrests();
    JsonBroadCast jbc = new JsonBroadCast();
    CustomJsonBuilder cjb = new CustomJsonBuilder();

    // /unban <name>
    //    0     1

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String commandLabel, String[] args) {
        if (!sr.hasPermission("kostkuj.unban")) {
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0) {
            ss.info(sr, "Use:  /unban <id:[idtrestu] | ip:[ipid] | [user] | um:[muted user]>");
            return true;
        }

        int idtrestu = 0;
        int ip = 0;

        GetTrest gt;

        if(args[0].contains("id:")){
            String sidtrestu = args[0].replace("id:", "");
            if(!StringUtils.isNumeric(sidtrestu)){
                ss.info(sr, "Id trestu neni cislo!");
                return false;
            }
            idtrestu = Integer.parseInt(sidtrestu);
        } else if (args[0].contains("ip:")){
            String sip = args[0].replace("ip:", "");
            if(!StringUtils.isNumeric(sip)){
                ss.info(sr, "IP trestu neni cislo!");
                return false;
            }
            ip = Integer.parseInt(sip);
            gt = new GetTrest(null, ip);
            idtrestu = gt.getIdtrestu();
            if(!gt.isIsbanned()){
                ss.info(sr, "Uzivatel neni zabanovan!");
                return true;
            }
        } else if (args[0].contains("um:")){
            String sunmute = args[0].replace("um:", "");
            OfflinePlayer op = Bukkit.getOfflinePlayer(sunmute);
            gt = new GetTrest(op.getUniqueId().toString());
            idtrestu = gt.getIdtrestu();
            if(!gt.isIsbanned()){
                ss.info(sr, "Uzivatel neni umlcen!");
                return true;
            }
        } else {
            String uunban = args[0].replace("u:", "").replace("p:", "");
            OfflinePlayer op = Bukkit.getOfflinePlayer(uunban);
            int lastIP = mti.getLastIP(op.getUniqueId().toString());
            gt = new GetTrest(op.getUniqueId().toString(), lastIP);
            idtrestu = gt.getIdtrestu();
            if(!gt.isIsbanned()){
                ss.info(sr, "Uzivatel neni zabanovan!");
                return true;
            }
        }

        int s = 1;
        boolean slient = false;
        if (args.length >= 2) {
            if (args[1].contains("s:true")) {
                slient = true;
                s = 2;
            } else if (args[1].contains("s:false")) {
                slient = false;
                s = 2;
            } else {
                slient = false;
                s = 1;
            }
        }

        if (idtrestu == 0 || !mti.isTrestExist(idtrestu)) {
            ss.info(sr, "Nepodařilo se nalézt správný trest.");
            return false;
        }
        String reason = "";

        for (int i = s; args.length > i; i++){
            reason = reason + args[i] + " ";
        }

        String admin;
        if (!(sr instanceof Player)) {
            admin = "47deb055-0148-3b84-a938-4d2047e53e93";
        } else {
            admin = Bukkit.getServer().getPlayer(sr.getName()).getUniqueId().toString();
        }

        if (slient){
            if (!sr.hasPermission("kostkuj.unban.slient")){
                ss.info(sr, "Nemas povoleni k potichému unbanu.");
                return true;
            }
        }

        String hover = "§7Unban:\n§7ID: §c#" + idtrestu + "\n§7Admin: §c" + sr.getName() + "\n§7Duvod: §c" + reason + "";

        mti.unban(idtrestu);

        String json = cjb.vetaClickHoverText("§8[§cKostkuj§8]: §7Trest §c➥#", "light_gray", String.valueOf(idtrestu), "red", hover, "suggest_command", "", " §7byl prominut.", "gray");

        if (!slient) {
            jbc.jsonBcKostkuj(json);
        } else {
            jbc.jsonBcKostkuj(json, "kostkuj.unban.slient");
        }
        mt.setUnban(idtrestu, admin, reason);
        return false;
    }
}