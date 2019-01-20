package by.domos.Kostkuj.connect.MySQL.MySQLlistener;

import by.domos.Kostkuj.connect.MySQL.MySQL;
import by.domos.Kostkuj.enums.ECfg;
import by.domos.Kostkuj.enums.EMessages;
import by.domos.Kostkuj.server.time.Time;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLset {
    MySQL mysql = new MySQL();
    MySQLget mysqlget = new MySQLget();

    public void setUserPlayer(PlayerPreLoginEvent a) {
        String uuid = a.getUniqueId().toString();
        String name = a.getName().trim().toString();
        try {
            PreparedStatement ps = mysql.getStatement("INSERT INTO " + ECfg.MYSQL_TABLE_USERS.getValue() + " (username, uuid, enabled) VALUES (?, ?, 1)");
            ps.setString(1, name);
            ps.setString(2, uuid);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
            e.printStackTrace();
        }

    }

    public void setPlacedBlock(org.bukkit.block.Block b) {
        long x = b.getX();
        long y = b.getY();
        long z = b.getZ();
        try {
            PreparedStatement ps = mysql.getStatement("INSERT INTO " + ECfg.MYSQL_TABLE_PLACE.getValue() + " (x, y, z) VALUES (?, ?, ?)");
            ps.setLong(1, x);
            ps.setLong(2, y);
            ps.setLong(3, z);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setBreakBlock(BlockBreakEvent b) {
        String block = b.getBlock().getType().name().toString();
        String uuid = b.getPlayer().getUniqueId().toString();
        try {
            PreparedStatement ps = mysql.getStatement("INSERT INTO " + ECfg.MYSQL_TABLE_BREAK.getValue() + " (item, uuid, time, position_x, position_y, position_z, world) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, block);
            ps.setString(2, uuid);
            ps.setTimestamp(3, Time.getTimeDay(0));
            ps.setInt(4, b.getBlock().getX());
            ps.setInt(5, b.getBlock().getY());
            ps.setInt(6, b.getBlock().getZ());
            ps.setString(7, b.getBlock().getWorld().getName().trim());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
            e.printStackTrace();
        }
    }


}
