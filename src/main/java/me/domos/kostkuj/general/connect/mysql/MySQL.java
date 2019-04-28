package me.domos.kostkuj.general.connect.mysql;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ECfg;
import me.domos.kostkuj.general.fileManager.EMessages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

    public static Connection con;

    public boolean isConected() {
        return (con != null);

    }

    public void connect() {
        if (!isConected()) {
            try {
                con = DriverManager.getConnection(ECfg.MYSQL_URL.getValue(), ECfg.MYSQL_USERNAME.getValue(), ECfg.MYSQL_PASSWORD.getValue());
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.GREEN + "MYSQL PRIPOJENA!!");
            } catch (SQLException e) {
                new SendSystem().debugMsg(ECfg.MYSQL_PREFIX.getValue() + " " + ECfg.MYSQL_USERNAME.getValue() + " " + ECfg.MYSQL_PASSWORD.getValue());
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
            }
        }
    }

    public void disconnect() {
        if (isConected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + "MYSQL ODPOJENA!!");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public PreparedStatement getStatement(String sql) {
        if (isConected()) {
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement(sql);
                return ps;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public PreparedStatement getStatement(String sql, int generatedKey) {
        if (isConected()) {
            PreparedStatement ps;
            try {
                ps = con.prepareStatement(sql, generatedKey);
                return ps;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

}
