package by.domos.Kostkuj.connect.MySQL.stavba;

import by.domos.Kostkuj.connect.MySQL.MySQL;
import by.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import by.domos.Kostkuj.server.chat.JSON.JsonSendMessage;
import by.domos.Kostkuj.server.time.Time;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MStavba {

    private MySQL mySQL = new MySQL();
    private Time time = new Time();

    public void create(String user, String popis){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("INSERT INTO game_kostkuj_stavba (admin_id, aktive, description) VALUE ((SELECT id FROM web_users WHERE username = ?), ?, ?)");
            try {
                ps.setString(1, user);
                ps.setInt(2, 0);
                ps.setString(3, popis);
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){

                }
            }
        }
    }

    public void get(CommandSender sr){
        JsonSendMessage jsm = new JsonSendMessage();
        CustomJsonBuilder cjb = new CustomJsonBuilder();
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_stavba s INNER JOIN web_users u on s.admin_id = u.id ORDER BY s.id DESC LIMIT 10");
            PreparedStatement ps2 = mySQL.getStatement("SELECT * FROM game_kostkuj_stavba_relation rel INNER JOIN web_forum_posts forum ON rel.forum_id = forum.id INNER JOIN web_users u on forum.user_created = u.id WHERE rel.stavba_id = ?");
            try {
                ResultSet rs = ps.executeQuery();
                sr.sendMessage("§8======= §7HLASOVANI §8=======");
                while (rs.next()){
                    int id = rs.getInt("s.id");
                    String start = "§4Nespuštěno";
                    String close = "§4Nespuštěno";
                    String stav = "";
                    String popis = rs.getString("s.description");
                    String user = rs.getString("u.username");
                    if (rs.getTimestamp("s.start_date") != null){
                        start = time.getTimeFromTimeStamp(rs.getTimestamp("s.start_date"));
                    }
                    if (rs.getTimestamp("s.close_date") != null){
                        close = time.getTimeFromTimeStamp(rs.getTimestamp("s.close_date"));
                    }
                    if (rs.getInt("s.aktive") == 0){
                        stav = "§cČeká";
                    } else if (rs.getInt("s.aktive") == 1){
                        stav = "§6Aktivní";
                    } else if (rs.getInt("s.aktive") == 2){
                        stav = "§aDokončen";
                    }
                        String hover = " §7Soutěžící:";
                    ps2.setInt(1, id);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()){
                        String forumuser = rs2.getString("u.username");
                        hover = hover + "\n §8 - §c" + forumuser;
                    }
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- " + "§8[§a" + id + "§8] §7" + popis + "§a| §8(" + stav + "§8)", "", hover, "", ""));
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){

                }
            }
        }
    }

    public boolean isVote(){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_stavba WHERE aktive = 0 OR aktive = 1");
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){

                }
            }
        }
        return false;
    }

    public int getNonAktive(){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_stavba WHERE aktive = 0");
            try {
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return rs.getInt("id");
                } else {
                    return 0;
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){

                }
            }
        }
        return -1;
    }

    public boolean isForumExist(int id){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM web_forum_posts WHERE id = ? AND parent_id = 37");
            try {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){

                }
            }
        }
        return false;
    }

    public boolean isImgExist(String url){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM web_images WHERE url = ?");
            try {
                ps.setString(1, url);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){

                }
            }
        }
        return false;
    }

    public void addPosition(String url, int idfora, int stavba){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("INSERT INTO game_kostkuj_stavba_relation (stavba_id, img, forum_id) value (?, ?, ?)");
            try {
                ps.setInt(1, stavba);
                ps.setString(2, url);
                ps.setInt(3, idfora);
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){

                }
            }
        }
    }

}
