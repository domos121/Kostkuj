package me.domos.kostkuj.general.connect.mysql.trests;

import me.domos.kostkuj.general.connect.mysql.MySQL;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.sql.*;

public class MTrests {

    MySQL m = new MySQL();


    public int setTrest(int trestId, String admin_uuid, String user_uuid, int ipid, String dicription, Timestamp time, Timestamp expiry_time, int unbanned, int[] rule) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("INSERT INTO game_kostkuj_bans (type_id, user_uuid, ip_id, description, time, expire_time, unbanned, admin_uuid) VALUE (?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps2 = m.getStatement("INSERT INTO game_kostkuj_bans_reason_relation (rule_id, ban_id) VALUE (? ,?)");
            try {
                ps.setInt(1, trestId);
                ps.setString(2, user_uuid);
                if (ipid == 0) {
                    ps.setString(3, null);
                } else {
                    ps.setInt(3, ipid);
                }
                ps.setString(4, dicription);
                ps.setTimestamp(5, time);
                ps.setTimestamp(6, expiry_time);
                ps.setInt(7, unbanned);
                ps.setString(8, admin_uuid);
                int rowAffected = ps.executeUpdate();
                if (rowAffected == 1) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int candidateId = rs.getInt(1);
                        for (int i = 0; rule.length > i; i++) {
                            ps2.setInt(1, rule[i]);
                            ps2.setInt(2, candidateId);
                            ps2.executeUpdate();
                        }
                        return candidateId;
                    }
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
            } finally {
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public boolean isRuleExist(int id) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("SELECT id FROM game_user_rules WHERE id = '" + id + "';");
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {

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

    public void setUnban(int trestId, String uuid, String reason){
        if (m.isConected()){
            PreparedStatement ps = m.getStatement("INSERT INTO game_kostkuj_bans_unban_relation (trest_id, admin_uuid, reason) VALUE (?, ?, ?)");
            try {
                ps.setInt(1, trestId);
                ps.setString(2, uuid);
                ps.setString(3, reason);
                ps.executeUpdate();
                ps.close();
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
