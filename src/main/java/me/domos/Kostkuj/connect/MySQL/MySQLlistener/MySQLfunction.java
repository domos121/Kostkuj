package me.domos.Kostkuj.connect.MySQL.MySQLlistener;

import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.enums.ECfg;
import me.domos.Kostkuj.enums.EMessages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;

public class MySQLfunction {
    MySQL mysql = new MySQL();
    MySQLis mysqlis = new MySQLis();


    public void createTables(){
        createTableUsers();
    }

    private void createTableUsers(){
        if(mysql.isConected()) {
            try {
                PreparedStatement ps = mysql.getStatement("CREATE TABLE IF NOT EXISTS " + ECfg.MYSQL_TABLE_USERS.getValue() + " (" + ECfg.MYSQL_TABLE_USERS_COLUMN_ID.getValue() + " INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY)");
                ps.executeUpdate();
                if (!mysqlis.isColumnsExist(ECfg.MYSQL_TABLE_USERS.getValue(), ECfg.MYSQL_TABLE_USERS_COLUMN_ID.getValue())){
                    ps.executeUpdate("ALTER TABLE " + ECfg.MYSQL_TABLE_USERS.getValue() + " ADD COLUMN " + ECfg.MYSQL_TABLE_USERS_COLUMN_ID.getValue() + " INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY");
                    Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + "The '" + ECfg.MYSQL_TABLE_USERS_COLUMN_ID.getValue() + "' column has been added to the " + ECfg.MYSQL_TABLE_USERS.getValue() + " table.");
                }
                if (!mysqlis.isColumnsExist(ECfg.MYSQL_TABLE_USERS.getValue(), ECfg.MYSQL_TABLE_USERS_COLUMN_UUID.getValue())){
                    ps.executeUpdate("ALTER TABLE " + ECfg.MYSQL_TABLE_USERS.getValue() + " ADD COLUMN " + ECfg.MYSQL_TABLE_USERS_COLUMN_UUID.getValue() + " CHAR(36) NOT NULL AFTER " + ECfg.MYSQL_TABLE_USERS_COLUMN_ID.getValue());
                    Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + "The '" + ECfg.MYSQL_TABLE_USERS_COLUMN_UUID.getValue() + "' column has been added to the " + ECfg.MYSQL_TABLE_USERS.getValue() + " table.");
                }
                if (!mysqlis.isColumnsExist(ECfg.MYSQL_TABLE_USERS.getValue(), ECfg.MYSQL_TABLE_USERS_COLUMN_USERNAME.getValue())){
                    ps.executeUpdate("ALTER TABLE " + ECfg.MYSQL_TABLE_USERS.getValue() + " ADD COLUMN " + ECfg.MYSQL_TABLE_USERS_COLUMN_USERNAME.getValue() + " VARCHAR(180) NOT NULL AFTER " + ECfg.MYSQL_TABLE_USERS_COLUMN_UUID.getValue());
                    Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + "The '" + ECfg.MYSQL_TABLE_USERS_COLUMN_USERNAME.getValue() + "' column has been added to the " + ECfg.MYSQL_TABLE_USERS.getValue() + " table.");
                }
                if (!mysqlis.isColumnsExist(ECfg.MYSQL_TABLE_USERS.getValue(), ECfg.MYSQL_TABLE_USERS_COLUMN_ENABLED.getValue())){
                    ps.executeUpdate("ALTER TABLE " + ECfg.MYSQL_TABLE_USERS.getValue() + " ADD COLUMN " + ECfg.MYSQL_TABLE_USERS_COLUMN_ENABLED.getValue() + " SMALLINT NOT NULL DEFAULT '0' AFTER " + ECfg.MYSQL_TABLE_USERS_COLUMN_USERNAME.getValue());
                    Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + "The '" + ECfg.MYSQL_TABLE_USERS_COLUMN_ENABLED.getValue() + "' column has been added to the " + ECfg.MYSQL_TABLE_USERS.getValue() + " table.");
                }
                ps.close();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(EMessages.PLUGIN_PREFIX.getValue() + ChatColor.RED + e.getMessage());
            }
        }
    }
}
