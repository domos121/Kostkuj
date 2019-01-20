package by.domos.Kostkuj.connect.MySQL;

import by.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import by.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import by.domos.Kostkuj.server.time.Time;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MVoteMute {

    MySQL mySQL = new MySQL();

    public int setCreateMute(String user_muted, String user_created){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT INTO game_kostkuj_votemute (create_user, create_date, banned_user, complete) value ((SELECT id FROM web_users WHERE username = ?), ?, (SELECT id FROM web_users WHERE username = ?), 0)", Statement.RETURN_GENERATED_KEYS);
            try {
                ps.setString(1, user_created);
                ps.setTimestamp(2, Time.getTimeDay(0));
                ps.setString(3, user_muted);
                int rowAffected = ps.executeUpdate();
                if (rowAffected == 1) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int candidateId = rs.getInt(1);
                        return candidateId;
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public void setAddVote(String user, int id){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT INTO game_kostkuj_votemute_relation (mute_id, user_id) value (?, (SELECT id FROM web_users WHERE username = ?))");
            try {
                ps.setInt(1, id);
                ps.setString(2, user);
                ps.executeUpdate();
            } catch (SQLException e){
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

    public void setUpdateVote(int id){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("UPDATE game_kostkuj_votemute set complete = 1 WHERE id = " + id);
            try {
                ps.executeUpdate();
            } catch (SQLException e){
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

    public void getVoteMute(CommandSender sr){
        JsonSendMessage jsm = new JsonSendMessage();
        CustomJsonBuilder cjb = new CustomJsonBuilder();
        Time time = new Time();
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_votemute votemute INNER JOIN web_users u on votemute.banned_user = u.id INNER JOIN web_users u2 on votemute.create_user = u2.id ORDER BY votemute.id desc LIMIT 10");
            PreparedStatement ps2 = mySQL.getStatement("SELECT * FROM game_kostkuj_votemute_relation rel INNER JOIN web_users u on rel.user_id = u.id WHERE mute_id = ?");
            try {
                ResultSet rs = ps.executeQuery();
                sr.sendMessage("§8======= §7VOTEMUTE §8=======");
                while (rs.next()){
                    int id = rs.getInt("votemute.id");
                    String banned = rs.getString("u.username");
                    String create = rs.getString("u2.username");
                    String dokoncen = rs.getInt("votemute.complete") + "";
                    String ti = time.getTimeFromTimeStamp(rs.getTimestamp("votemute.create_date"));
                    if (dokoncen.equalsIgnoreCase("1")){
                        dokoncen = " §7(§aSchváleno§7)";
                    } else {
                        dokoncen = " §7(§4Neschváleno§7)";
                    }
                    ps2.setInt(1, id);
                    ResultSet rs2 = ps2.executeQuery();
                    String schválil = "";
                    while (rs2.next()){
                        schválil = schválil + "\n §8- §a" +  rs2.getString("u.username");
                    }
                    String hover = "§c" + banned + ":\n" + "§7Založil: §c" + create + "\n§7Čas: §c" + ti + "\n" + "§7Schválil: " + schválil;
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- §c" + banned + dokoncen, "", hover, "run_command", "/pi " + banned));
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
