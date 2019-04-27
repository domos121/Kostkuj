package me.domos.kostkuj.general.connect.mysql.player.luckPerms;

import me.domos.kostkuj.general.connect.mysql.MySQL;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLLuckPerms {

    MySQL mysql = new MySQL();

    public String isWebVip(String name) {
        if (mysql.isConected()) {
            PreparedStatement ps = mysql.getStatement("SELECT web_users.roles FROM web_users WHERE web_users.username = ?");
            try {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("roles");
                }
            } catch (SQLException e) {
                Bukkit.getServer().broadcastMessage(e.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return "a:0:{}";
    }

    public void setVip(String name, String role) {
        if (mysql.isConected()) {
            PreparedStatement ps = mysql.getStatement("UPDATE web_users SET roles = ? where username = ?");
            try {
                ps.setString(1, role);
                ps.setString(2, name);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                Bukkit.getServer().broadcastMessage(e.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
