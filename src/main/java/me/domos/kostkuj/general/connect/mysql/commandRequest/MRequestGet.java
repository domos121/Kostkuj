package me.domos.kostkuj.general.connect.mysql.commandRequest;

import me.domos.kostkuj.general.connect.mysql.MySQL;
import me.domos.kostkuj.general.fileManager.EMessages;
import me.domos.kostkuj.general.requestCommandSender.SendCommandFromDatabase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MRequestGet {

    MySQL mysql = new MySQL();
    SendCommandFromDatabase cs = new SendCommandFromDatabase();


    public void getRequest() {
        if (mysql.isConected()) {
            PreparedStatement ps = mysql.getStatement("SELECT user.username, action.id, type.name, action.settings FROM game_request_actions action INNER JOIN game_request_actions_types type on action.type_id = type.id INNER JOIN web_users user on action.user_id = user.id");
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("action.id");
                    String user = rs.getString("user.username");
                    String cmd = rs.getString("type.name");
                    String sett = rs.getString("action.settings");
                    if (!cmd.equalsIgnoreCase("RestartingPC")) {
                        cs.sendCMD(id, cmd, user, sett);
                        delRequestId(id);
                    }
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
    }

    public void delRequestId(int id) {
        PreparedStatement ps = mysql.getStatement("DELETE FROM game_request_actions WHERE id = ?");
        try {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
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
