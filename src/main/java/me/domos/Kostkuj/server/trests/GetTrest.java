package me.domos.Kostkuj.server.trests;

import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.server.time.Time;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

public class GetTrest {

    MySQL m = new MySQL();

    private boolean isbanned = true;
    private int idtrestu;
    private int ipid;
    private String typtrestu;
    private String user;
    private String admin;
    private Timestamp settime;
    private Timestamp expirytime;
    private HashMap<Integer, String> obj = new HashMap();
    private String hash;
    private String dicription;


    public GetTrest(String uuid, String haship) {
        this.obj.clear();
        if (m.isConected()) {
            this.isbanned = true;
            this.idtrestu = 0;
            this.ipid = 0;
            this.typtrestu = null;
            this.user = null;
            this.admin = null;
            this.hash = null;
            this.dicription = null;
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_bans ban INNER JOIN game_kostkuj_bans_type type ON ban.type_id = type.id WHERE ((ban.expire_time > ? AND ban.unbanned = 0) OR ((ban.type_id = 1 OR ban.type_id = 3) AND ban.unbanned = 0)) AND ban.type_id != 6 AND ban.type_id != 5 AND (ban.user_uuid = ? OR ip_id = (SELECT game_kostkuj_ipmap.id FROM game_kostkuj_ipmap WHERE ip_hash = ?))");
            PreparedStatement ps2 = m.getStatement("SELECT * FROM game_kostkuj_bans_reason_relation bans INNER JOIN game_user_rules rule ON bans.rule_id = rule.id WHERE ban_id = ?");
            try {
                ps.setTimestamp(1, Time.getTimeDay(0));
                ps.setString(2, uuid);
                ps.setString(3, haship);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this.isbanned = true;
                    this.idtrestu = rs.getInt("ban.id");
                    this.typtrestu = rs.getString("type.trest");
                    this.user = rs.getString("ban.user_uuid");
                    this.admin = rs.getString("ban.admin_uuid");
                    this.settime = rs.getTimestamp("ban.time");
                    this.expirytime = rs.getTimestamp("ban.expire_time");
                    this.dicription = rs.getString("ban.description");
                    this.ipid = rs.getInt("ban.ip_id");
                    ps2.setInt(1, rs.getInt("ban.id"));
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        this.obj.put(rs2.getInt("bans.rule_id"), rs2.getString("rule.description"));
                    }
                } else {
                    this.isbanned = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public GetTrest(String uuid, int ipid) {
        this.obj.clear();
        if (m.isConected()) {
            this.isbanned = true;
            this.idtrestu = 0;
            this.ipid = 0;
            this.typtrestu = null;
            this.user = null;
            this.admin = null;
            this.hash = null;
            this.dicription = null;
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_bans ban INNER JOIN game_kostkuj_bans_type type ON ban.type_id = type.id WHERE ((ban.expire_time > ? AND ban.unbanned = 0) OR ((ban.type_id = 1 OR ban.type_id = 3) AND ban.unbanned = 0)) AND ban.type_id != 6 AND ban.type_id != 5 AND (ban.user_uuid = ? OR ban.ip_id = ?)");
            PreparedStatement ps2 = m.getStatement("SELECT * FROM game_kostkuj_bans_reason_relation bans INNER JOIN game_user_rules rule ON bans.rule_id = rule.id WHERE ban_id = ?");
            try {
                ps.setTimestamp(1, Time.getTimeDay(0));
                ps.setString(2, uuid);
                ps.setInt(3, ipid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this.isbanned = true;
                    this.idtrestu = rs.getInt("ban.id");
                    this.typtrestu = rs.getString("type.trest");
                    this.user = rs.getString("ban.user_uuid");
                    this.admin = rs.getString("ban.admin_uuid");
                    this.settime = rs.getTimestamp("ban.time");
                    this.expirytime = rs.getTimestamp("ban.expire_time");
                    this.dicription = rs.getString("ban.description");
                    this.ipid = rs.getInt("ban.ip_id");
                    ps2.setInt(1, rs.getInt("ban.id"));
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        this.obj.put(rs2.getInt("bans.rule_id"), rs2.getString("rule.description"));
                    }
                } else {
                    this.isbanned = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public GetTrest(String uuid) {
        this.obj.clear();
        if (m.isConected()) {
            this.isbanned = true;
            this.idtrestu = 0;
            this.ipid = 0;
            this.typtrestu = null;
            this.user = null;
            this.admin = null;
            this.hash = null;
            this.dicription = null;
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_bans ban INNER JOIN game_kostkuj_bans_type type ON ban.type_id = type.id WHERE ban.expire_time > ? AND ban.unbanned = 0 AND ban.type_id = 6 AND ban.user_uuid = ?");
            PreparedStatement ps2 = m.getStatement("SELECT * FROM game_kostkuj_bans_reason_relation bans INNER JOIN game_user_rules rule ON bans.rule_id = rule.id WHERE ban_id = ?");
            try {
                ps.setTimestamp(1, Time.getTimeDay(0));
                ps.setString(2, uuid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this.isbanned = true;
                    this.idtrestu = rs.getInt("ban.id");
                    this.typtrestu = rs.getString("type.trest");
                    this.user = rs.getString("ban.user_uuid");
                    this.admin = rs.getString("ban.admin_uuid");
                    this.settime = rs.getTimestamp("ban.time");
                    this.expirytime = rs.getTimestamp("ban.expire_time");
                    this.dicription = rs.getString("ban.description");
                    ps2.setInt(1, rs.getInt("ban.id"));
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        this.obj.put(rs2.getInt("bans.rule_id"), rs2.getString("rule.description"));
                    }
                } else {
                    this.isbanned = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getIpid() {
        return ipid;
    }

    public boolean isIsbanned() {
        return isbanned;
    }

    public int getIdtrestu() {
        return idtrestu;
    }

    public String getTyptrestu() {
        return typtrestu;
    }

    public String getUser() {
        return user;
    }

    public String getAdmin() {
        return admin;
    }

    public Timestamp getSettime() {
        return settime;
    }

    public Timestamp getExpirytime() {
        return expirytime;
    }

    public HashMap<Integer, String> getObj() {
        return obj;
    }

    public String getDicription() {
        return dicription;
    }
}
