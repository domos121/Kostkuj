package me.domos.kostkuj.general.connect.mysql.projekty;

import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLGetProjekt {

    MySQL mySQL = new MySQL();
    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder cjb = new CustomJsonBuilder();

    public void getProjekt(String user, CommandSender sr){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM web_forum_posts post LEFT JOIN game_kostkuj_project_relation projekt on post.id = projekt.forum_id WHERE post.parent_id = 65 AND post.user_created = (SELECT id FROM web_users WHERE username = ?) order by date_created desc");
            try {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                sr.sendMessage("§8====== §7" + "Project's: §c" + user + " §8======");
                while (rs.next()){
                    int id = rs.getInt("id");
                    int idschvaleno = rs.getInt("projekt.complete");
                    String schvaleno = null;
                    String schvalil = "";
                    if (idschvaleno == 0){
                        schvaleno = "§cCeka";
                    } else if (idschvaleno == 1){
                        schvaleno = "§6Schvaleno";
                    } else if (idschvaleno == 2){
                        schvaleno = "§aDokonceno";
                    } else if (idschvaleno == 3){
                        schvaleno = "§4Zruseno";
                    }
                    if (idschvaleno > 0){
                        schvalil = "\n §aSchvalil: §c" + rs.getString("projekt.accept_user");
                    }
                    String dokoncil = "";
                    if (idschvaleno == 2){
                        dokoncil = "\n §aDokoncil: §c" + rs.getString("projekt.complete_user");
                    }
                    String titulek = rs.getString("title");
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- §7➥§c" + titulek + " §7(" + schvaleno + "§7)", "gray", "§c" + titulek + "\n §aProjektID: §c#" + id + schvalil + dokoncil, "open_url", "https://kostkuj.cz/forum/" + id));
                }
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

    public void getProjekts(CommandSender sr){
        if (mySQL.isConected()) {
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM web_forum_posts post LEFT JOIN game_kostkuj_project_relation projekt on post.id = projekt.forum_id WHERE post.parent_id = 65 order by date_created desc");
            try {
                ResultSet rs = ps.executeQuery();
                sr.sendMessage("§8====== §7" + "Projects: §c" + " §8======");
                while (rs.next()){
                    int id = rs.getInt("id");
                    int idschvaleno = rs.getInt("projekt.complete");
                    String schvaleno = null;
                    String schvalil = "";
                    if (idschvaleno == 0){
                        schvaleno = "§cCeka";
                    } else if (idschvaleno == 1){
                        schvaleno = "§6Schvaleno";
                    } else if (idschvaleno == 2){
                        schvaleno = "§aDokonceno";
                    } else if (idschvaleno == 3){
                        schvaleno = "§4Zruseno";
                    }
                    if (idschvaleno > 0){
                        schvalil = "\n §aSchvalil: §c" + rs.getString("projekt.accept_user");
                    }
                    String dokoncil = "";
                    if (idschvaleno == 2){
                        dokoncil = "\n §aDokoncil: §c" + rs.getString("projekt.complete_user");
                    }
                    String titulek = rs.getString("title");
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- §7➥§c" + titulek + " §7(" + schvaleno + "§7)", "gray", "§c" + titulek + "\n §aProjektID: §c#" + id + schvalil + dokoncil, "open_url", "https://kostkuj.cz/forum/" + id));
                }
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

    public void accept(int id, CommandSender sr){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT INTO game_kostkuj_project_relation (accept_user, accept_date, complete, forum_id) value (?,?,?,?)");
            try {
                ps.setString(1, sr.getName());
                ps.setTimestamp(2, Time.getTimeDay(0));
                ps.setInt(3, 1);
                ps.setInt(4, id);
                ps.executeUpdate();
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

    public boolean isAccept(int id){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_project_relation WHERE forum_id = ?");
            try {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }
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
        return false;
    }

    public void sendMessage(int id, String msg, String user){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT into web_forum_posts_messages (user_created, post_id, message, date_created, date_edited) value ((SELECT id FROM web_users WHERE username = ?),?,?,?,?)");
            try {
                ps.setString(1, user);
                ps.setInt(2, id);
                ps.setString(3, msg);
                ps.setTimestamp(4, Time.getTimeDay(0));
                ps.setTimestamp(5, Time.getTimeDay(0));
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getForumAuthor(int forumid){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT user2.username FROM web_forum_posts posts INNER JOIN web_users user2 on posts.user_created = user2.id WHERE posts.id = ?");
            try {
                ps.setInt(1, forumid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                   return rs.getString("user2.username");
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
        return "Vyskytla se chyba.";
    }

    public void complete(int id, CommandSender sr){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("UPDATE game_kostkuj_project_relation SET complete = 2, complete_user = ?  WHERE forum_id = ?");
            PreparedStatement ps2 = mySQL.getStatement("UPDATE web_forum_posts set active = 0 where id = ?");
            try {
                ps.setString(1, sr.getName());
                ps.setInt(2, id);
                ps2.setInt(1, id);
                ps.executeUpdate();
                ps2.executeUpdate();
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

    public void close(int id, CommandSender sr){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("UPDATE game_kostkuj_project_relation SET complete = 3, complete_user = ?  WHERE forum_id = ?");
            PreparedStatement ps2 = mySQL.getStatement("UPDATE web_forum_posts set active = 0 where id = ?");
            try {
                ps.setString(1, sr.getName());
                ps.setInt(2, id);
                ps2.setInt(1, id);
                ps.executeUpdate();
                ps2.executeUpdate();
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
    public void closes(int id, CommandSender sr){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT INTO game_kostkuj_project_relation (accept_user, accept_date, complete, forum_id) value (?,?,?,?)");
            PreparedStatement ps2 = mySQL.getStatement("UPDATE web_forum_posts set active = 0 where id = ?");
            try {
                ps.setString(1, sr.getName());
                ps.setTimestamp(2, Time.getTimeDay(0));
                ps.setInt(3, 3);
                ps.setInt(4, id);
                ps.executeUpdate();
                ps2.setInt(1, id);
                ps2.executeUpdate();
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

    public boolean isComplete(int id){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM game_kostkuj_project_relation WHERE forum_id = ? AND (complete = 2 OR complete = 3)");
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
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
