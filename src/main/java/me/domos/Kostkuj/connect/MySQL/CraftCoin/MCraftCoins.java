package me.domos.Kostkuj.connect.MySQL.CraftCoin;

import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.connect.MySQL.MySQLlistener.MySQLget;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.time.Time;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MCraftCoins {

    private MySQL mySQL = new MySQL();
    private MySQLget mySQLget = new MySQLget();
    SendSystem ss = new SendSystem();


    public int setCC(String name, int pocet, int typ, boolean sendMsg){
        PreparedStatement ps = mySQL.getStatement("INSERT INTO shop_transactions (user_id, created_date, amount, type_id) value ((SELECT web_users.id FROM web_users WHERE username = ?),?,?,?);", Statement.RETURN_GENERATED_KEYS);
        try {
            ps.setString(1, name);
            ps.setTimestamp(2, Time.getTimeDay(0));
            ps.setInt(3, pocet);
            ps.setInt(4, typ);
            int rowAffected = ps.executeUpdate();
            if (rowAffected == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    OfflinePlayer op = Bukkit.getOfflinePlayer(name);
                    if (sendMsg && op.isOnline()){
                        ss.info(Bukkit.getPlayer(op.getName()), "§7Na tvůj účet bylo přičteno §a'" + pocet + "cc'§7." );
                    }
                    return id;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getCC(String name) {
        return mySQLget.getCount("SELECT SUM(amount) FROM shop_transactions where user_id = (SELECT id FROM web_users where username = '" + name +"')");
    }

}
