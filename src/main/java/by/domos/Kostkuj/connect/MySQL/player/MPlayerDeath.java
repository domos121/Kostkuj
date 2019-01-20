package by.domos.Kostkuj.connect.MySQL.player;

import by.domos.Kostkuj.connect.MySQL.MySQL;
import by.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import by.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.time.Time;
import org.bukkit.command.CommandSender;

import java.sql.*;

public class MPlayerDeath {

    MySQL m = new MySQL();
    SendSystem ss = new SendSystem();
    CustomJsonBuilder cjb = new CustomJsonBuilder();
    JsonSendMessage jsm = new JsonSendMessage();
    Time time = new Time();

    public void setDeath(String user, String deathmessage, Double x, Double y, Double z, String world){
        if (m.isConected()){
            PreparedStatement ps = m.getStatement("INSERT INTO game_kostkuj_player_death (user_id, message, position_x, position_y, position_z, world, time) VALUE ((SELECT id FROM web_users WHERE username = ?), ?, ?, ?, ?, ?, ?)");
            try {
                ps.setString(1, user);
                ps.setString(2, deathmessage);
                ps.setDouble(3, x);
                ps.setDouble(4, y);
                ps.setDouble(5, z);
                ps.setString(6, world);
                ps.setTimestamp(7, Time.getTimeDay(0));
                ps.executeUpdate();
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

    public void getDeath(String user, CommandSender sr){
        if (m.isConected()){
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_player_death WHERE user_id = (SELECT id FROM web_users WHERE username = ?) ORDER BY id DESC limit 15");
            try {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    int x = rs.getInt("position_x");
                    int y = rs.getInt("position_y");
                    int z = rs.getInt("position_z");
                    String msg = rs.getString("message");
                    String world = rs.getString("world");
                    Timestamp tstime = rs.getTimestamp("time");
                    String sour = "§7x:§c" + x + ", §7y:§c" + y + ", §7z:§c" + z + ", §7world: §c" + world;
                    sr.sendMessage("§8====== §7Death: §c" + user + " §8======");
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- §7➥§c" + time.getTimeFromTimeStamp(tstime), "dark_gray", "§c" + time.getTimeFromTimeStamp(tstime) + "\n" + sour + "\n§7Msg: §c" + msg, "suggest_command", x + " " + y + " " + z + " " + world));
                } else {
                    sr.sendMessage("§8====== §7Death: §c" + user + " §8======");
                    sr.sendMessage("§7Smrti nenalezeny.");
                }
                while (rs.next()){
                    int x = rs.getInt("position_x");
                    int y = rs.getInt("position_y");
                    int z = rs.getInt("position_z");
                    String msg = rs.getString("message");
                    String world = rs.getString("world");
                    String sour = "§7x:§c" + x + ", §7y:§c" + y + ", §7z:§c" + z + ", §7world: §c" + world;
                    Timestamp tstime = rs.getTimestamp("time");
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- §7➥§c" + time.getTimeFromTimeStamp(tstime), "dark_gray", "§c" + time.getTimeFromTimeStamp(tstime) + "\n" + sour + "\n§7Msg: §c" + msg, "suggest_command", x + " " + y + " " + z + " " + world));
                }
            } catch (SQLException e) {
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
