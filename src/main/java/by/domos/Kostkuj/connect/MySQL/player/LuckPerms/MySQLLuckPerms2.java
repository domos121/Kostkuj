package by.domos.Kostkuj.connect.MySQL.player.LuckPerms;

import by.domos.Kostkuj.connect.MySQL.MySQL;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLLuckPerms2 {

    MySQL mySQl = new MySQL();

    public String[][] getGroups(String name) {
        String[][] nic = {{"default"}};
        PreparedStatement ps = mySQl.getStatement("SELECT lpu.permission, lpu.expiry FROM game_luckperms_players lpp INNER JOIN game_luckperms_user_permissions lpu on lpp.uuid = lpu.uuid WHERE lpp.username = ? AND lpu.permission LIKE '%group%';");
        try {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            ArrayList<String> groups = new ArrayList<String>();
            while (rs.next()) {
                groups.add(rs.getString("lpu.permission") + "," + rs.getString("lpu.expiry"));
            }
            String[][] groupss = new String[groups.size()][];
            for (int u = 0; groups.size() > u; u++) {
                groupss[u] = groups.get(u).split(",");
            }
            return groupss;
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nic;
    }

    public String getPrimaryGroup(String user) {
        if (user.equalsIgnoreCase("CONSOLE")) {
            return "console";
        }
        PreparedStatement ps = mySQl.getStatement("SELECT primary_group FROM game_luckperms_players WHERE username = ?");
        try {
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("primary_group");
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
        return "default";
    }
}
