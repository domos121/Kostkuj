package me.domos.Kostkuj.connect.MySQL.player;

import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.server.time.Time;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statisticks{

    private boolean isInTop = false;

    MySQL m = new MySQL();
    Time t = new Time();


    public boolean isExist(String user){
        if(m.isConected()){
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_statisticks WHERE user_id = (SELECT id FROM web_users WHERE username = ?)");
            try {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public void setStats(String user, int time){
        if (isExist(user)){
            PreparedStatement ps = m.getStatement("UPDATE game_kostkuj_statisticks SET time = ? WHERE user_id = (SELECT id FROM web_users WHERE username = ?)");
            try {
                ps.setInt(1, time);
                ps.setString(2, user);
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            PreparedStatement ps = m.getStatement("INSERT INTO game_kostkuj_statisticks (user_id, time) VALUE ((SELECT id FROM web_users WHERE username = ?), ?)");
            try {
                ps.setString(1, user);
                ps.setInt(2, time);
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getTime(CommandSender sr, String user){
        PreparedStatement ps = m.getStatement("SELECT stat.time, user.username FROM game_kostkuj_statisticks stat INNER JOIN web_users user on stat.user_id = user.id ORDER BY time DESC limit 10");
        if (m.isConected()){
            try {
                ResultSet rs = ps.executeQuery();
                int i = 0;
                this.isInTop = false;
                while (rs.next()){
                    i++;
                    String color;
                    if (i == 1){
                        color = "§6§l";
                    } else if (i == 2){
                        color = "§2§l";
                    } else if (i == 3){
                        color = "§a§l";
                    } else {
                        color = "§7";
                    }
                    String username = rs.getString("user.username");
                    String time = t.minuteToTime(rs.getInt("stat.time"));
                    if (username.equalsIgnoreCase(sr.getName())){
                        sr.sendMessage(color + i + ") §2" + username + " §8- §7" + time);
                        this.isInTop = true;
                    } else {
                        sr.sendMessage(color + i + ") §c" + username + " §8- §7" + time);
                    }
                }
                if (!isInTop){
                    Player p = Bukkit.getPlayer(sr.getName());
                    sr.sendMessage("§7N/A) §2" + sr.getName() + " §8- §7" + t.minuteToTime(p.getStatistic(Statistic.PLAY_ONE_MINUTE)));
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getTime(String user){
        PreparedStatement ps = m.getStatement("SELECT time FROM game_kostkuj_statisticks WHERE user_id = (SELECT id FROM web_users WHERE username = ?)");
        if (m.isConected()){
            try {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return t.minuteToTime(rs.getInt("time"));
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Nenalezen";
    }
}
