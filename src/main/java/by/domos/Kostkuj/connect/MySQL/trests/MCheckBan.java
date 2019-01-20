package by.domos.Kostkuj.connect.MySQL.trests;

import by.domos.Kostkuj.connect.MySQL.MySQL;
import by.domos.Kostkuj.server.player.PlayerManager;
import by.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import by.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import by.domos.Kostkuj.server.time.Time;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class MCheckBan {

    MySQL m = new MySQL();
    CustomJsonBuilder cjb = new CustomJsonBuilder();
    JsonSendMessage jsm = new JsonSendMessage();
    PlayerManager pm = new PlayerManager();
    Time t = new Time();

    public void getTrest(String uuid, int ip, CommandSender sr){
        if (m.isConected()){
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_bans bans INNER JOIN game_kostkuj_bans_type type ON bans.type_id = type.id WHERE (bans.user_uuid = ? OR bans.ip_id = ?) ORDER BY time DESC");
            try {
                ps.setString(1, uuid);
                ps.setInt(2, ip);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("bans.id");
                    String typ = rs.getString("type.trest");
                    Timestamp expire;
                    if (rs.getTimestamp("bans.expire_time") != null) {
                        expire = rs.getTimestamp("bans.expire_time");
                    } else {
                        expire = Time.getTimeDay(15);
                    }
                    String admin = pm.getNameFromUuid(UUID.fromString(rs.getString("bans.admin_uuid")));
                    int unbanned = rs.getInt("bans.unbanned");
                    String stav;
                    if (unbanned == 1){
                        stav = "§aUnban!";
                    } else if ((expire.getTime() < Time.getTimeSec(0).getTime()) && (typ.contains("temp") || typ.contains("mute"))){
                        stav = "§aVyprsel!";
                    } else {
                        stav = "§4Aktivni!";
                    }
                    String hover = "§7TREST\n§7Typ: §c" + typ + "\n§7Admin: §c" + admin;
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText("§8 - §7➥§c#" + String.valueOf(id) + " §7(" + stav +"§7)", "gray", hover,"run_command", "/checkban id:" + id));
                }
            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void getTrest(int id, CommandSender sr){
        if (m.isConected()){
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_bans b INNER JOIN game_kostkuj_bans_type t on b.type_id = t.id WHERE b.id = ?");
            PreparedStatement ps2 = m.getStatement("SELECT * FROM game_kostkuj_bans_reason_relation gkbrr INNER JOIN game_user_rules gur on gkbrr.rule_id = gur.id WHERE ban_id = ?");
            try {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    ps2.setInt(1, id);
                    ResultSet rs2 = ps2.executeQuery();
                    String typ = rs.getString("t.trest");
                    String user = rs.getString("b.user_uuid");
                    if (user != null){
                        user = pm.getNameFromUuid(UUID.fromString(user));
                    }
                    String ip = rs.getInt("b.ip_id") + "";
                    Timestamp settime = rs.getTimestamp("b.time");
                    String time = t.getTimeFromTimeStamp(settime);
                    Timestamp expire;
                    if (rs.getTimestamp("b.expire_time") != null) {
                        expire = rs.getTimestamp("b.expire_time");
                    } else {
                        expire = Time.getTimeDay(15);
                    }
                    String sexpire = "Nikdy";
                    if (typ.contains("temp") || typ.contains("mute")){
                        sexpire = t.getTimeFromTimeStamp(expire);
                    }
                    String admin = pm.getNameFromUuid(UUID.fromString(rs.getString("admin_uuid")));
                    String popis = rs.getString("description");
                    int unbanned = rs.getInt("unbanned");
                    String stav;
                    if (unbanned >= 1){
                        stav = "§aUnban!";
                    } else if ((expire.getTime() < Time.getTimeSec(0).getTime()) && (typ.contains("temp") || typ.contains("mute"))){
                        stav = "§aVyprsel!";
                    } else {
                        stav = "§4Aktivni!";
                    }
                    sr.sendMessage("§7Typ: §c" + typ);
                    sr.sendMessage("§7Stav: " + stav);
                    sr.sendMessage("§7User: §c" + user);
                    sr.sendMessage("§7Ip: §c" + ip);
                    sr.sendMessage("§7Time: §c" + time);
                    sr.sendMessage("§7Expire: §c" + sexpire);
                    sr.sendMessage("§7Admin: §c" + admin);
                    sr.sendMessage("§7Popis: §c" + popis);
                    sr.sendMessage(" §7Porušená pravidla:");
                    while (rs2.next()){
                        int ruleid = rs2.getInt("gkbrr.rule_id");
                        String pravidlo = rs2.getString("gur.description");
                        jsm.jsonBcKostkuj(sr, cjb.hoverText("§8 - §7➥§c" + ruleid, "red", "§c" + ruleid + ":\n§7" + pravidlo));
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
