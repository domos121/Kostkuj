package me.domos.kostkuj.general.connect.mysql.mysqlListener;

import me.domos.kostkuj.general.connect.mysql.MySQL;
import me.domos.kostkuj.general.fileManager.ECfg;
import me.domos.kostkuj.general.fileManager.EMessages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLis {

    MySQL mysql = new MySQL();

    public boolean isPlacedBlock(org.bukkit.block.Block b) {
        int x = b.getX();
        int y = b.getY();
        int z = b.getZ();
        PreparedStatement ps = mysql.getStatement("SELECT * FROM " + ECfg.MYSQL_TABLE_PLACE.getValue() + " WHERE x=? AND y=? AND z=?");
        try {
            ps.setLong(1, x);
            ps.setLong(2, y);
            ps.setLong(3, z);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
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

    public boolean isColumnsExist(String table, String colum) {
        if (mysql.isConected()) {
            PreparedStatement ps = mysql.getStatement("show columns from " + table + " where field = ?");
            try {
                ps.setString(1, colum);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
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
        return false;
    }


    public boolean isPlayerExist(String usernames) {
        if (mysql.isConected()) {
            PreparedStatement ps = mysql.getStatement("SELECT * FROM " + ECfg.MYSQL_TABLE_USERS.getValue() + " WHERE username =?");
            try {
                ps.setString(1, usernames);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
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
        return false;
    }


}
