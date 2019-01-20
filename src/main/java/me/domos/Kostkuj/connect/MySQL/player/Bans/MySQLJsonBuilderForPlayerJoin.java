package me.domos.Kostkuj.connect.MySQL.player.Bans;

import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLget;
import me.domos.Kostkuj.connect.MySQL.trests.MTrestsIP;
import me.domos.Kostkuj.enums.EMessages;
import me.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import me.domos.Kostkuj.server.player.PlayerManager;
import me.domos.Kostkuj.server.time.Time;
import me.domos.Kostkuj.server.trests.GetTrest;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class MySQLJsonBuilderForPlayerJoin {

    private String json = "[";

    MySQL mySQL = new MySQL();
    MySQLget mySQLget = new MySQLget();
    PlayerManager pm = new PlayerManager();
    Time time = new Time();
    MTrestsIP mti = new MTrestsIP();

    @Deprecated
    public void setInfoPlayerConnection(String ssuuid){
        int ip_id = mti.getLastIP(ssuuid);
        PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_ipinfo WHERE ipid = ? ORDER BY login_time DESC");
        try {
            ps.setInt(1, ip_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String slogintime = "Nikdy";
                String slogouttime = "Nikdy";
                String suuid = rs.getString("uuid");
                UUID uuid = UUID.fromString(suuid);
                String name = pm.getNameFromUuid(uuid);
                Timestamp logintime = rs.getTimestamp("login_time");
                if (logintime != null){
                     slogintime = time.getTimeFromTimeStamp(logintime);
                }
                Timestamp logouttime = rs.getTimestamp("logout_time");
                if (logouttime != null){
                    slogouttime = time.getTimeFromTimeStamp(logouttime);
                }
                OfflinePlayer offp = Bukkit.getOfflinePlayer(uuid);
                boolean isonline = offp.isOnline();
                GetTrest gt = new GetTrest(suuid, 0);
                String ssetime = "Nikdy";
                if(gt.getSettime() != null){
                    ssetime = time.getTimeFromTimeStamp(gt.getSettime());
                }
                String sexpire = "Nikdy";
                if (gt.getExpirytime() != null) {
                    sexpire = time.getTimeFromTimeStamp(gt.getExpirytime());
                }
                String sadmin = "Nikdo";
                if (gt.getAdmin() != null){
                    sadmin = pm.getNameFromUuid(UUID.fromString(gt.getAdmin()));
                }
                String sdesc = "Nikdo";
                if (gt.getAdmin() != null){
                    sdesc = gt.getDicription();
                }
                boolean isbanned = gt.isIsbanned();
                if(isonline){
                    this.json = this.json + "\n" +
                            "{\n" +
                            "  \"text\": \" ➥#name#\",\n" +
                            "  \"color\": \"green\",\n" +
                            "  \"hoverEvent\": {\n" +
                            "    \"action\": \"show_text\",\n" +
                            "    \"value\": \"§c#name#:\\n §8- §cLogin: §7#login#\\n §8- §cLogout: §7#logout#\"\n" +
                            "  },\n" +
                            "  \"clickEvent\": {\n" +
                            "    \"action\": \"run_command\",\n" +
                            "    \"value\": \"/pi #name#\"\n" +
                            "  }\n" +
                            "},\n" +
                            "{\n" +
                            "  \"text\":\",\",\n" +
                            "  \"color\":\"dark_gray\"\n" +
                            "}\n" +
                            ",";
                } else if (isbanned){
                    this.json = this.json + "\n" +
                            "{\n" +
                            "  \"text\": \" ➥#name#\",\n" +
                            "  \"color\": \"red\",\n" +
                            "  \"hoverEvent\": {\n" +
                            "    \"action\": \"show_text\",\n" +
                            "    \"value\": \"§c#name#:\\n §8- §cLogin: §7#login#\\n §8- §cLogout: §7#logout#\\n §cBanned:\\n §8- §cTyp banu: §7Uzivatelky ban\\n §8- §cDatum banu: §7#set#\\n §8- §cDatum expirace: §7#expiry#\\n §8- §cBanoval: §7#banner#\\n §8- §cDuvod: §7#reason#\"\n" +
                            "  },\n" +
                            "  \"clickEvent\": {\n" +
                            "    \"action\": \"run_command\",\n" +
                            "    \"value\": \"/pi #name#\"\n" +
                            "  }\n" +
                            "},\n" +
                            "{\n" +
                            "  \"text\":\",\",\n" +
                            "  \"color\":\"dark_gray\"\n" +
                            "}\n" +
                            ",";
                } else {
                    this.json = this.json + "\n" +
                            "{\n" +
                            "  \"text\": \" ➥#name#\",\n" +
                            "  \"color\": \"gray\",\n" +
                            "  \"hoverEvent\": {\n" +
                            "    \"action\": \"show_text\",\n" +
                            "    \"value\": \"§c#name#:\\n §8- §cLogin: §7#login#\\n §8- §cLogout: §7#logout#\"\n" +
                            "  },\n" +
                            "  \"clickEvent\": {\n" +
                            "    \"action\": \"run_command\",\n" +
                            "    \"value\": \"/pi #name#\"\n" +
                            "  }\n" +
                            "},\n" +
                            "{\n" +
                            "  \"text\":\",\",\n" +
                            "  \"color\":\"dark_gray\"\n" +
                            "}\n" +
                            ",";
                }
                this.json = this.json
                        .replace("#name#", name)
                        .replace("#login#", slogintime)
                        .replace("#logout#", slogouttime)
                        .replace("#set#", ssetime)
                        .replace("#expiry#", sexpire)
                        .replace("#banner#", sadmin)
                        .replace("#reason#", sdesc);
            }
            this.json = this.json + "{\"text\":\"\"}" + "]";
            JsonBroadCast jbc = new JsonBroadCast();
            jbc.jsonBcKostkuj(this.json, "kostkuj.join.info");
            this.json = "[";

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
