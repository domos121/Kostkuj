package me.domos.kostkuj.general.connect.mysql.mysqlListener;

import me.domos.kostkuj.general.connect.mysql.MySQL;
import me.domos.kostkuj.general.fileManager.ECfg;
import me.domos.kostkuj.general.fileManager.EMessages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLget {

    MySQL mysql = new MySQL();

    public int getConfirm(String uuid) {
        if (mysql.isConected()) {
            try {
                PreparedStatement ps = mysql.getStatement("SELECT * FROM " + ECfg.MYSQL_TABLE_USERS.getValue() + " WHERE uuid=?");
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int bl = rs.getInt("enabled");
                    return bl;
                }
                return -1;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
            }
        }
        return -2;
    }
    
    public int getCount(String sql){
        PreparedStatement ps = mysql.getStatement(sql);
        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public boolean getBoolean(String sql){
        PreparedStatement ps = mysql.getStatement(sql);
        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}