package me.domos.kostkuj.bukkit.listeners.cmds.playerInfo;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.player.MPlayerInfo;
import me.domos.kostkuj.general.connect.mysql.player.Statisticks;
import me.domos.kostkuj.general.connect.mysql.player.bans.MySQLJsonBuilder;
import me.domos.kostkuj.general.connect.mysql.player.luckPerms.MySQLLuckPerms2;
import me.domos.kostkuj.general.connect.mysql.trests.MTrestsIP;
import me.domos.kostkuj.models.banModel.GetTrest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class PlayerInfo implements CommandExecutor {
    private SendSystem ss = new SendSystem();
    private MTrestsIP mti = new MTrestsIP();
    private Time time = new Time();
    private MySQLJsonBuilder mySQLJsonBuilder = new MySQLJsonBuilder();
    private CustomJsonBuilder cjb = new CustomJsonBuilder();
    private JsonSendMessage jsm = new JsonSendMessage();
    private Statisticks s = new Statisticks();
    private MySQLLuckPerms2 mlps = new MySQLLuckPerms2();

    public PlayerInfo() {
    }

    public boolean onCommand(CommandSender sr, Command cmd, String commandLabel, String[] args) {
        if (!(sr instanceof Player)){
            ss.noPlayer(sr);
            return true;
        }

        if (!sr.hasPermission(ECmd.PLAYERINFO.getPerm())) {
            this.ss.noPerm(sr);
            return true;
        } else {
            OfflinePlayer op = Bukkit.getOfflinePlayer(sr.getName());
            if (args.length >= 1 && sr.hasPermission(ECmd.PLAYERINFO_OTHER.getPerm())) {
                op = Bukkit.getOfflinePlayer(args[0]);
            }

            MPlayerInfo mpi = new MPlayerInfo(op.getName());
            int lastip = this.mti.getLastIP(op.getUniqueId().toString());
            String acite = "§4Neaktivovan";
            if (mpi.getEnabled() == 1) {
                acite = "§aAktivovan";
            }

            GetTrest gt = new GetTrest(op.getUniqueId().toString(), lastip);
            GetTrest mute = new GetTrest(op.getUniqueId().toString());
            String[][] groups = this.mlps.getGroups(op.getName());
            String hoverdole = "";
            String groupa = "Default";

            try {
                if (groups[0][0].equalsIgnoreCase("default")) {
                    hoverdole = "Vyskyla se chyba pri komunikaci.";
                }

                for(int i = 0; groups.length > i; ++i) {
                    if (!groups[i][0].contains("default")) {
                        groupa = groups[i][0].replace("group.", "");
                        hoverdole = hoverdole + "\n§c" + groupa + "§8:\n§7 Expire: §c" + this.time.unixToTime(Integer.parseInt(groups[i][1]));
                    }
                }
            } catch (ArrayIndexOutOfBoundsException var19) {
                hoverdole = "\n§c Pravděpodobně hráč neexistuje.";
            }

            String hoverformute = "§aNeni potrestan";
            String hoverforban = "§aNeni potrestan";
            if (gt.isIsbanned()) {
                hoverforban = this.getHover(gt.isIsbanned(), gt.getIdtrestu(), gt.getTyptrestu(), gt.getAdmin(), gt.getUser(), gt.getIpid(), gt.getExpirytime(), gt.getSettime(), gt.getDicription(), gt.getObj());
            }

            if (mute.isIsbanned()) {
                hoverformute = this.getHover(mute.isIsbanned(), mute.getIdtrestu(), mute.getTyptrestu(), mute.getAdmin(), mute.getUser(), mute.getIpid(), mute.getExpirytime(), mute.getSettime(), mute.getDicription(), mute.getObj());
            }

            if (op.isOnline()) {
                Player p = (Player) op;
                sr.sendMessage("§8====== §7PLAYER: §c" + p.getDisplayName() + " §8======");
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7User: " + p.getDisplayName() + " §8[" + this.ss.boolenTranslateIsOnline(op.isOnline()) + "§8]", "", "§c" + p.getName() + ":\n §7Aktivace: " + acite + "\n §7UUID: §c" + p.getUniqueId().toString() + "\n §7UserID: §c" + mpi.getUser_id() + "\n §7RegisterDate: §c" + mpi.getRegisterdate() + "\n §7Op: §c" + this.ss.boolenTranslate(p.isOp()) + "\n §7Fly: §c" + this.ss.boolenTranslate(p.isFlying()) + "\n §7Gamemode: §c" + p.getGameMode(), "suggest_command", p.getUniqueId().toString()));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7CraftCoins: §c" + mpi.getCc(), "", "§aClick", "suggest_command", mpi.getCc() + ""));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Ip: §c" + lastip, "", "§aClick", "suggest_command", "/checkip " + lastip));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Hlad: §c" + p.getFoodLevel() + "§8/§c20", "", "§aMáš taky hlad?", "suggest_command", "Mám hlad!"));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Životy: §c" + (int)p.getHealth() + "§8/§c20", "", "§aNeumíráš?", "suggest_command", "Neumíráš?"));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7ExpLv: §c" + p.getExpToLevel(), "", "§cNic tu není? :D", "", ""));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Odehráno: §c" + this.s.getTime(op.getName()), "", "§cNic tu není? :D", "", ""));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Muted: §c" + this.ss.boolenTranslate(mute.isIsbanned()), "", hoverformute, "suggest_command", "/checkban id:" + mute.getIdtrestu()));
                Location l = p.getLocation();
                String location_hover = "§cLokace: \n§7Biome: §c" + l.getChunk().getWorld().getBiome(l.getBlockX(), l.getBlockY()).name() + "\n§7Chunk: §c" + l.getChunk().getX() + "§7;§c" + l.getChunk().getZ() + " §7Region: §c" + Math.ceil((double)(l.getBlockX() / 512)) + "§7;§c" + Math.ceil((double)(l.getBlockZ() / 512)) + "\n§7Entity: §c" + l.getChunk().getEntities().length;
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Pozice: §c(" + p.getLocation().getWorld().getName() + ", " + p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ() + ")", "", location_hover, "suggest_command", "/tppos " + p.getLocation().getBlockX() + " " + p.getLocation().getBlockY() + " " + p.getLocation().getBlockZ() + " " + p.getLocation().getWorld().getName()));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Groups: §c" + this.mlps.getPrimaryGroup(p.getName()), "", "§cGroups§8: " + hoverdole, "", ""));
                sr.sendMessage(" §cIp§8:");
                this.mySQLJsonBuilder.getipForCheckPlayer(op.getUniqueId().toString(), sr);
                return true;
            } else {
                sr.sendMessage("§8====== §7PLAYER: §c" + op.getName() + " §8======");
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7User: §c" + op.getName() + " §8[" + this.ss.boolenTranslateIsOnline(op.isOnline()) + "§8]", "", "§c" + op.getName() + ":\n §7Aktivace: " + acite + "\n §7UUID: §c" + op.getUniqueId().toString() + "\n §7UserID: §c" + mpi.getUser_id() + "\n §7RegisterDate: §c" + mpi.getRegisterdate() + "\n §7Op: §c" + this.ss.boolenTranslate(op.isOp()), "suggest_command", op.getUniqueId().toString()));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7CraftCoins: §c" + mpi.getCc(), "", "§aClick", "suggest_command", mpi.getCc() + ""));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Ip: §c" + lastip, "", "§aClick", "suggest_command", "/checkip " + lastip));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Odehráno: §c" + this.s.getTime(op.getName()), "", "§cNic tu není? :D", "", ""));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Muted: §c" + this.ss.boolenTranslate(mute.isIsbanned()), "", hoverformute, "suggest_command", "/checkban id:" + mute.getIdtrestu()));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Banned: §c" + this.ss.boolenTranslate(gt.isIsbanned()), "", hoverforban, "suggest_command", "/checkban id:" + gt.getIdtrestu()));
                Location l = new Location(Bukkit.getWorld(mpi.getWorld()), mpi.getPosition_x(), mpi.getPosotion_y(), mpi.getPosition_z());
                String location_hover = "§cLokace: \n§7Biome: §c" + l.getChunk().getWorld().getBiome(l.getBlockX(), l.getBlockY()).name() + "\n§7Chunk: §c" + l.getChunk().getX() + "§7;§c" + l.getChunk().getZ() + " §7Region: §c" + Math.ceil((double)(l.getBlockX() / 512)) + "§7;§c" + Math.ceil((double)(l.getBlockZ() / 512)) + "\n§7Entity: §c" + l.getChunk().getEntities().length;
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Pozice: §c(" + l.getWorld().getName() + ", " + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + ")", "", location_hover, "suggest_command", "/tppos " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ() + " " + l.getWorld().getName()));
                this.jsm.jsonBcKostkuj(sr, this.cjb.clickhoverText("§7Groups: §c" + this.mlps.getPrimaryGroup(op.getName()), "", "§cGroups§8: " + hoverdole, "", ""));
                sr.sendMessage(" §cIp§8:");
                this.mySQLJsonBuilder.getipForCheckPlayer(op.getUniqueId().toString(), sr);
                return false;
            }
        }
    }

    private String getHover(boolean isbanned, int id, String typ, String uadmin, String user, int ip, Timestamp expire, Timestamp settime, String popis, HashMap<Integer, String> rule) {
        PlayerManager pm = new PlayerManager();
        Time t = new Time();
        Iterator it = rule.entrySet().iterator();

        String rules;
        Map.Entry rul;
        for(rules = ""; it.hasNext(); rules = rules + "\n §c" + rul.getKey() + "§8) §7" + rul.getValue()) {
            rul = (Map.Entry)it.next();
        }

        if (!isbanned) {
            return "§aNeni potrestan";
        } else {
            if (user != null) {
                user = pm.getNameFromUuid(UUID.fromString(user));
            }

            String sexpire = "Nikdy";
            if (typ.contains("temp") || typ.contains("mute")) {
                sexpire = t.getTimeFromTimeStamp(expire);
            }

            String admin = pm.getNameFromUuid(UUID.fromString(uadmin));
            String time = t.getTimeFromTimeStamp(settime);
            String hover = "§7Trest: §c#" + id + "\n§7Typ: §c" + typ + "\n§7User: §c" + user + "\n§7Ip: §c" + ip + "\n§7Time: §c" + time + "\n§7Expire: §c" + sexpire + "\n§7Admin: §c" + admin + " \n§7Porušená pravidla:" + rules;
            return hover;
        }
    }
}
