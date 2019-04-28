package me.domos.kostkuj.general.connect.mysql.sklad;

import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MSklad {

    private MySQL mySQL = new MySQL();
    private Time time = new Time();

    public void openSklad(String user){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT INTO web_sale_chests_opened (user_id) VALUE ((SELECT id FROM web_users WHERE username = ?))");
            try {
                ps.setString(1, user);
                ps.executeUpdate();
                ps.close();
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

    public void closeSklad(String user){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("DELETE FROM web_sale_chests_opened WHERE user_id = (SELECT id FROM web_users WHERE username = ?)");
            try {
                ps.setString(1, user);
                ps.executeUpdate();
                ps.close();
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

    public void createUserChest(Player p){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT INTO web_sale_chests (user_id, type_id, date_edited) VALUE ((SELECT id FROM web_users WHERE username = ?), ?, ?)");
            try {
                ps.setString(1, p.getName());
                ps.setInt(2, 1);
                ps.setTimestamp(3, time.getTimeDay(0));
                ps.executeUpdate();
                ps.close();
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

    public int getUserChest(Player p){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM web_sale_chests chest INNER JOIN web_sale_chests_types typ ON chest.type_id = typ.id WHERE chest.user_id = (SELECT id FROM web_users WHERE username = ?)");
            try {
                ps.setString(1, p.getName());
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return rs.getInt("typ.count");
                } else {
                    return 0;
                }
            } catch (SQLException e){
                e.printStackTrace();
                return -1;
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    return -1;
                }
            }
        }
        return -1;
    }
}
