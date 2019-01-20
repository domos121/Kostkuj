package me.domos.Kostkuj.connect.MySQL.trests;

import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.server.time.Time;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MTrestsIP {

    MySQL m = new MySQL();

    public void setIP(String haship) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("INSERT INTO game_kostkuj_ipmap (ip_hash) VALUE (?)");
            try {
                ps.setString(1, haship);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public boolean isIpExist(String haship) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_ipmap WHERE ip_hash = ?");
            try {
                ps.setString(1, haship);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    return false;
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
        }
        return false;
    }

    public boolean isIpExist(int ipid) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_ipmap WHERE id = ?");
            try {
                ps.setInt(1, ipid);
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

    public boolean isIpInfoExist(String uuid, String haship) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_ipinfo ipinfo INNER JOIN game_kostkuj_ipmap ip ON ipinfo.ipid = ip.id WHERE uuid = ? AND ip.ip_hash = ?");
            try {
                ps.setString(1, uuid);
                ps.setString(2, haship);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    return false;
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
        }
        return false;
    }

    public boolean isTrestExist(int id) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_bans WHERE id = ? AND unbanned = 0");
            try {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    return false;
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
        }
        return false;
    }

    public int getLastIP(String uuid) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_ipinfo WHERE uuid = ? ORDER BY login_time DESC LIMIT 1");
            try {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("ipid");
                } else {
                    return -1;
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
        }
        return -1;
    }

    public String getIp(int id) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("SELECT * FROM game_kostkuj_ipmap WHERE id = ?");
            try {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("ip_hash");
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
        }
        return "nejde";
    }

    public void setIPFirstLogin(String uuid, String haship) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("INSERT INTO game_kostkuj_ipinfo (uuid, ipid, login_time) VALUE (?,(SELECT id FROM game_kostkuj_ipmap WHERE ip_hash = ?),?)");
            try {
                ps.setString(1, uuid);
                ps.setString(2, haship);
                ps.setTimestamp(3, Time.getTimeDay(0));
                ps.executeUpdate();
                ps.close();
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

    public void updateIPLogin(String uuid, String haship) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("UPDATE game_kostkuj_ipinfo SET login_time = ? WHERE uuid = ? AND ipid = (SELECT id FROM game_kostkuj_ipmap WHERE ip_hash = ?)");
            try {
                ps.setTimestamp(1, Time.getTimeDay(0));
                ps.setString(2, uuid);
                ps.setString(3, haship);
                ps.executeUpdate();
                ps.close();
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

    public void updateIPLogout(String uuid, String haship) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("UPDATE game_kostkuj_ipinfo SET logout_time = ? WHERE uuid = ? AND ipid = (SELECT id FROM game_kostkuj_ipmap WHERE ip_hash = ?)");
            try {
                ps.setTimestamp(1, Time.getTimeDay(0));
                ps.setString(2, uuid);
                ps.setString(3, haship);
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

    public void setFirstHas(String uuid, String hash) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("INSERT INTO game_kostkuj_ipmap (ip_hash) value (?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps2 = m.getStatement("INSERT INTO game_kostkuj_ipinfo (uuid, ipid, login_time) VALUE (?, ?, ?)");
            try {
                ps.setString(1, hash);
                int rowAffected = ps.executeUpdate();
                if (rowAffected == 1) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int candidateId = rs.getInt(1);
                        ps2.setString(1, uuid);
                        ps2.setInt(2, candidateId);
                        ps2.setTimestamp(3, Time.getTimeDay(0));
                        ps2.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Bukkit.getServer().getConsoleSender().sendMessage(e.getMessage());
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int unban(int id) {
        if (m.isConected()) {
            PreparedStatement ps = m.getStatement("UPDATE game_kostkuj_bans SET unbanned = 1 WHERE id = ?");
            try {
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}
