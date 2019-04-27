package me.domos.kostkuj.general.connect.mysql.player.bans;

import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import me.domos.kostkuj.general.fileManager.EMessages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class MySQLJsonBuilder {

    private String json = "[";

    MySQL mySQL = new MySQL();
    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder jcm = new CustomJsonBuilder();
    PlayerManager pm = new PlayerManager();
    Time time = new Time();

    public void getipForCheckPlayer(String uuid, CommandSender sr) {
        PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_ipinfo WHERE uuid = ? ORDER BY login_time DESC LIMIT 5");
        try {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ipid");
                String ip_id = String.valueOf(id);
                String slogintime = "Nikdy";
                String slogouttime = "Nikdy";
                Timestamp tlogintime = rs.getTimestamp("login_time");
                Timestamp tlogouttime = rs.getTimestamp("logout_time");
                if (tlogintime != null) {
                    slogintime = time.getTimeFromTimeStamp(tlogintime);
                }
                if (tlogouttime != null) {

                    slogouttime = time.getTimeFromTimeStamp(tlogouttime);
                }
                jsm.jsonBcKostkuj(sr, jcm.clickhoverText("  §8- §c➥" + ip_id, "", "§c" + ip_id + "§8:" + "\n §7Login: §a" + slogintime + "\n §7Login: §a" + slogouttime, "suggest_command", "/checkip " + ip_id));
            }
            this.json = "[";
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getNameForCheckIp(int ip_id, String json, CommandSender sr) {
        this.json = this.json + json;
        PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_ipinfo WHERE ipid = ? ORDER BY login_time DESC LIMIT 5");
        try {
            ps.setInt(1, ip_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String suuid = rs.getString("uuid");
                UUID uuid = UUID.fromString(suuid);
                String name = pm.getNameFromUuid(uuid);
                String slogintime = "Nikdy";
                String slogouttime = "Nikdy";
                Timestamp tlogintime = rs.getTimestamp("login_time");
                Timestamp tlogouttime = rs.getTimestamp("logout_time");
                if (tlogintime != null){
                    slogintime = time.getTimeFromTimeStamp(tlogintime);
                }
                if (tlogouttime != null){

                    slogouttime = time.getTimeFromTimeStamp(tlogouttime);
                }
                this.json = this.json + "\n" + "{\n" + "  \"text\": \"\n  §8- §c➥#name#\",\n".replace("#name#", name) + "  \"hoverEvent\": {\n" + "    \"action\": \"show_text\",\n" + "    \"value\": \"§c#name#:\\n §8- §cLogin: §7#login#\\n §8- §cLogout: §7#logout#\"\n".replace("#name#", name).replace("#login#", slogintime).replace("#logout#", slogouttime) + "  },\n" + "  \"clickEvent\": {\n" + "    \"action\": \"run_command\",\n" + "    \"value\": \"/pi #name#\"\n".replace("#name#", name) + "  }\n" + "}\n" + ",";
            }
            this.json = this.json + "{\"text\":\" \"}" + "]";
            jsm.jsonBcKostkuj(sr, this.json);
            this.json = "[";
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
