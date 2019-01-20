package me.domos.Kostkuj.server.trests;

import me.domos.Kostkuj.connect.MySQL.CraftCoin.MCraftCoins;
import me.domos.Kostkuj.connect.MySQL.player.MPlayerInfo;
import me.domos.Kostkuj.connect.MySQL.trests.MTrests;
import me.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import me.domos.Kostkuj.connect.email.BanEmail;
import me.domos.Kostkuj.server.BayPass;
import me.domos.Kostkuj.server.chat.BanManager.SendBanMessage;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.player.ipmanager.IpHasher;
import me.domos.Kostkuj.server.time.Time;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Trest {

    private Time time = new Time();
    private IpHasher ih = IpHasher.getInstance();
    private SendBanMessage sbm = new SendBanMessage();
    private BayPass bp = new BayPass();
    private MCraftCoins mcc = new MCraftCoins();

    @Deprecated
    public Trest(CommandSender sr, String[] args) {
        String action = null;
        String user = null;
        String time = null;
        String ipid = null;
        String rule = null;
        String desc = "";
        long ftime = 0;

        SendSystem ss = new SendSystem();
        for (int i = 0; args.length > i; i++) {
            if (args[i].contains("a:")) {
                action = args[i].replace("a:", "");
            } else if (args[i].contains("u:")) {
                user = args[i].replace("u:", "");
            } else if(args[i].contains("p:")){
                user = args[i].replace("p:", "");
            } else if (args[i].contains("t:")) {
                time = args[i].replace("t:", "");
            } else if (args[i].contains("ip:")) {
                ipid = args[i].replace("ip:", "");
            } else if (args[i].contains("r:")) {
                rule = args[i].replace("r:", "");
            } else if (args[i].contains("d:")) {
                for (int u = 0; args.length > i; i++) {
                    if (args[i].contains("a:") || args[i].contains("u:") || args[i].contains("t:") || args[i].contains("ip:") || args[i].contains("r:")) {
                        i--;
                        break;
                    } else {
                        desc = desc + args[i].replace("d:", "") + " ";
                    }
                }
            } else {
                ss.info(sr, "Překontroluj si tento argument: " + args[i] + ".");
                return;
            }
        }
        if (action == null) {
            ss.info(sr, "Napiš nějakou akci a:<ban, kick, atd...>");
            return;
        }

        if ((user == null && ipid == null) || (user == null && (action.equalsIgnoreCase("tempban") || action.equalsIgnoreCase("kick") || action.equalsIgnoreCase("ban") || action.equalsIgnoreCase("mute")))) {
            ss.info(sr, "Napiš správného vinníka (u:<user> nebo ip:<ipid>), podle akce.");
            return;
        }

        if (ipid != null && !StringUtils.isNumeric(ipid)) {
            ss.info(sr, "Ipadresa je ve špatném formátu.");
            return;
        }

        if ((action.contains("temp") || action.contains("mute")) && time == null) {
            ss.info(sr, "Chybí ti na jak dlouho chceš udělit trest t:<time>");
            return;
        }

        if (rule == null) {
            ss.info(sr, "Nemůžeš trestat bez důvodu, dopiš porušené pravidlo r:<1,5,11,atd..>");
            return;
        }

        TrestAction ta = new TrestAction();
        int faction = ta.action(sr, action);

        if (faction == -1) {
            ss.info(sr, "Špatně zapsaná akce.");
            return;
        }

        if (time != null) {
            ftime = ta.time(sr, time);
            if (ftime == -1 || ftime < 0) {
                ss.info(sr, "Spatne zadana casova volba nebo cas samotny.");
                return;
            }
        }

        if (ipid == null) {
            ipid = "0";
        }


        int fipid = ta.ipid(sr, ipid, user, faction);


        if (fipid == -1) {
            ss.info(sr, "Ip adresa neexistuje!");
            return;
        }

        int[] frule = ta.rule(sr, rule);

        if (frule[0] == -1) {
            ss.info(sr, "Špatně definované pravidla. Překontroluj řetězec!");
            return;
        }


        MTrests mt = new MTrests();
        for (int aFrule : frule) {
            if (!mt.isRuleExist(aFrule)) {
                ss.info(sr, "Pravidlo " + aFrule + " neexistuje.");
                return;
            }
        }

        String fbanneduuid;

        if (user != null) {
            fbanneduuid = ta.user_uuid(sr, user);
        } else {
            fbanneduuid = null;
        }

        String admin;
        if (!(sr instanceof Player)) {
            admin = "47deb055-0148-3b84-a938-4d2047e53e93";
        } else {
            admin = Bukkit.getServer().getPlayer(sr.getName()).getUniqueId().toString();
        }

        Timestamp expir = Time.getTimeSec(ftime);
        String cc = "";
        if (faction == 1 || faction == 2) {
            MPlayerInfo mpi = new MPlayerInfo(user);
            GetTrest trest = new GetTrest(fbanneduuid, fipid);
            if (trest.isIsbanned()) {
                ss.info(sr, "Uzivatel uz je zabanovan!");
                return;
            }
            if (faction == 1) {
                expir = null;
                int removeccid = mcc.setCC(user, mpi.getCc() * -1, 3, false);
                cc = " [RCCID: " + removeccid + "]";
            }
            int idrstu = mt.setTrest(faction, admin, fbanneduuid, 0, desc + cc, Time.getTimeSec(0), expir, 0, frule);
            KickFromServer(idrstu, user, action, sr.getName(), desc + cc, Time.getTimeSec(0), expir, frule);
            BanEmail.send(idrstu, mpi.getUser_id());
        } else if (faction == 3 || faction == 4) {
            cc = "";
            int removeccid = 0;
            int userid = 0;
            if (user != null){
                MPlayerInfo mpi = new MPlayerInfo(user);
                removeccid = mcc.setCC(user, mcc.getCC(user) * -1, 3, false);
                userid = mpi.getUser_id();
            }
            GetTrest trest = new GetTrest(fbanneduuid, fipid);
            if (trest.isIsbanned()) {
                ss.info(sr, "Uzivatel uz je zabanovan!");
                return;
            }
            if (faction == 3) {
                expir = null;

                cc = " [RCCID: " + removeccid + "]";
            }
            int idrstu = mt.setTrest(faction, admin, fbanneduuid, fipid, desc + cc, Time.getTimeSec(0), expir, 0, frule);
            KickIpFromServer(idrstu, user, action, sr.getName(), fipid, desc + cc, Time.getTimeSec(0), expir, frule);
            if (userid != 0) {
                BanEmail.send(idrstu, userid);
            }
        } else if (faction == 6) {
            GetTrest trest = new GetTrest(fbanneduuid);
            if (trest.isIsbanned()) {
                ss.info(sr, "Uzivatel uz je umlcen!");
                return;
            }
            int idrsu = mt.setTrest(faction, admin, fbanneduuid, 0, desc, Time.getTimeSec(0), Time.getTimeSec(ftime), 0, frule);
            SendBanMessage sbm = new SendBanMessage();
            Time tims = new Time();
            sbm.sendBanMessage(user, sr.getName(), action, tims.getTimeFromTimeStamp(Time.getTimeSec(0)), tims.getTimeFromTimeStamp(Time.getTimeSec(ftime)), idrsu, desc, rule);
        } else {
            OfflinePlayer op = Bukkit.getOfflinePlayer(user);
            if (!op.isOnline()) {
                ss.info(sr, "Uzivatel neni online");
                return;
            }
            int idrstu = mt.setTrest(faction, admin, fbanneduuid, 0, desc, Time.getTimeSec(0), Time.getTimeSec(0), 1, frule);
            KickFromServer(idrstu, user, action, sr.getName(), desc, Time.getTimeDay(0), Time.getTimeDay(0), frule);
        }

    }

    @Deprecated
    private void KickFromServer(int idtrstu, String user, String typtrestu, String admin, String desc, Timestamp settime, Timestamp expiretime, int[] frule) {
        OfflinePlayer op = Bukkit.getOfflinePlayer(user);
        Player p;
        String expire;
        if (expiretime != null) {
            expire = time.getTimeFromTimeStamp(expiretime);
        } else {
            expire = "Nikdy";
        }
        String rule = "";
        if (!typtrestu.contains("temp")) {
            expire = "Nikdy.";
        }
        if (desc.equalsIgnoreCase("")) {
            desc = "Zadny.";
        }
        for (int aFrule : frule) {
            rule = rule + aFrule + ", ";
        }
        if (op.isOnline()) {
            if (!typtrestu.contains("mute")) {
                p = Bukkit.getServer().getPlayer(user);
                p.kickPlayer("§6========== §c" + p.getName() + "§6 ==========\n" +
                        "§cTREST:\n" +
                        "§cIDTrestu: §7#" + idtrstu + "\n" +
                        "§cTyp trestu: §7" + typtrestu + "\n" +
                        "§cDatum trestu: §7" + time.getTimeFromTimeStamp(settime) + "\n" +
                        "§cDatum expirace:§7 " + expire + "\n" +
                        "§cTrestajici:§7 " + admin + "\n" +
                        "§cPoruseni pravidel:§7 " + rule + "\n" +
                        "§cPopis:§7 " + desc + "\n" +
                        "§6================================================\n\n" +
                        "§7Unban lze zakoupit na našem webu §awww.kostkuj.cz§7.\n\n" +
                        "§6V případě nesouhlasu se lze obrátit na vedení serveru.");
                sbm.sendBanMessage(user, admin, typtrestu, time.getTimeFromTimeStamp(settime), expire, idtrstu, desc, rule);
            } else {
                sbm.sendBanMessage(user, admin, typtrestu, time.getTimeFromTimeStamp(settime), expire, idtrstu, desc, rule);
            }
        } else {
            sbm.sendBanMessage(user, admin, typtrestu,time.getTimeFromTimeStamp(settime), expire, idtrstu, desc, rule);
        }
    }

    @Deprecated
    public void KickIpFromServer(int idtrstu, String user, String typtrestu, String admin, int ip, String desc, Timestamp settime, Timestamp expiretime, int[] frule) {
        MTrestsIP mti = new MTrestsIP();
        String banhash = mti.getIp(ip);
        if (user == null || user.equalsIgnoreCase("")){
            user = String.valueOf(ip);
        }
        String expire = time.getTimeFromTimeStamp(expiretime);
        String rule = "";
        if (!typtrestu.contains("temp")) {
            expire = "Nikdy.";
        }
        if (desc.equalsIgnoreCase("")) {
            desc = "Zadny.";
        }
        for (int aFrule : frule) {
            rule = rule + aFrule + ", ";
        }
        List<Player> onlinePlayers = new ArrayList<Player>();
        for (Player c : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(c);
            String sip = c.getAddress().getAddress().toString().replace("/", "");
            String userhaship = ih.hashIp(sip);
            if (banhash.equalsIgnoreCase(userhaship)) {
                c.kickPlayer("§6========== §c" + c.getName() + "§6 ==========\n" +
                        "§cTREST:\n" +
                        "§cIDTrestu: §7#" + idtrstu + "\n" +
                        "§cTyp trestu: §7" + typtrestu + "\n" +
                        "§cDatum trestu: §7" + time.getTimeFromTimeStamp(settime) + "\n" +
                        "§cDatum expirace:§7 " + expire + "\n" +
                        "§cTrestajici:§7 " + admin + "\n" +
                        "§cPoruseni pravidel:§7 " + rule + "\n" +
                        "§cPopis:§7 " + desc + "\n" +
                        "§6================================================\n\n" +
                        "§7Unban lze zakoupit na našem webu §awww.kostkuj.cz§7.\n\n" +
                        "§6V případě nesouhlasu se lze obrátit na vedení serveru.");
            }
        }
        sbm.sendBanMessage(user, admin, typtrestu,time.getTimeFromTimeStamp(settime), expire, idtrstu, desc, rule);
    }
}
