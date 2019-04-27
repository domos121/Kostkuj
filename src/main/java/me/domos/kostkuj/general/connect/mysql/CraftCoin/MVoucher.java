package me.domos.kostkuj.general.connect.mysql.CraftCoin;

import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import net.minecraft.server.v1_13_R2.ChatClickable;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MVoucher {

    MySQL mySQL = new MySQL();
    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder cjb = new CustomJsonBuilder();
    Time time = new Time();

    public boolean createVoucher(int pocet, String key, String user){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("INSERT INTO shop_voucher (created_date, cost, serial_key, is_complete) value (?, ?, ?, ?)");
            try {
                ps.setTimestamp(1, Time.getTimeDay(0));
                ps.setInt(2, pocet);
                ps.setString(3, key);
                ps.setInt(4, 0);
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

        return true;
    }

    public boolean voucherUpdate(CommandSender sr, String key){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("UPDATE shop_voucher SET is_complete = ?, user_activated = (SELECT id FROM web_users WHERE username = ?), activated_date = ? WHERE serial_key = ?");
            try {
                ps.setInt(1, 1);
                ps.setString(2, sr.getName().trim());
                ps.setTimestamp(3, Time.getTimeDay(0));
                ps.setString(4, key);
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
        return true;
    }

    public ResAddCC addCC(CommandSender sr, String key){
        int cost = 0;
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT cost FROM shop_voucher WHERE serial_key = ?");
            PreparedStatement ps2 = mySQL.getStatement("INSERT INTO shop_transactions (user_id, created_date, amount, type_id) value ((SELECT id FROM web_users where username = ?), ?, ?, 1)");
            try {
                ps.setString(1, key);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    cost = rs.getInt("cost");
                }
                ps2.setString(1, sr.getName().trim());
                ps2.setTimestamp(2, Time.getTimeDay(0));
                ps2.setInt(3, cost);
                ps2.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
                return new ResAddCC(cost, false);
            } finally {
                try {
                    ps.close();
                    ps2.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return new ResAddCC(cost, true);
    }

    public void getVouchers(CommandSender sr, int complete, int limit){
        String limits = " LIMIT 10" + " OFFSET " + limit;
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM shop_voucher voucher LEFT JOIN web_users user on voucher.user_activated = user.id WHERE is_complete = " + complete + " ORDER BY voucher.id DESC " + limits);
            try {
                ResultSet rs = ps.executeQuery();
                sr.sendMessage("§8======= §7VOUCHERS §6[§a" + limit + "§8-§a" + (limit+10) + "§6] §8=======");
                while (rs.next()){
                    int id = rs.getInt("voucher.id");
                    String key = rs.getString("voucher.serial_key");
                    int cost = rs.getInt("voucher.cost");
                    String create_date =  time.getTimeFromTimeStamp(rs.getTimestamp("voucher.created_date"));
                    String aktivatedstr = "";
                    if (complete == 1){
                        String user = rs.getString("user.username");
                        String aktiv_date = time.getTimeFromTimeStamp(rs.getTimestamp("voucher.activated_date"));
                        aktivatedstr = "\n §7Aktivoval: §c" + user + "\n §7Aktivovano: §c" + aktiv_date;
                    }
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- " + "[§a" + id + "§8] " + "§7" + key + " §8(§a" + cost + "§8)", "", "§c" + key + "\n" + " §7Vytvoreno: §c" + create_date + "\n §7Pocet: §c" + cost + aktivatedstr, ChatClickable.EnumClickAction.SUGGEST_COMMAND.b(), key));
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
    }

    public int getVoucher(String key){
        ArrayList<String> list = new ArrayList<>();
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM shop_voucher WHERE serial_key = '" + key + "' AND is_complete = 0");
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return rs.getInt("cost");
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

    public final class ResAddCC {
        private final int cost;
        private final boolean isComplete;

        public ResAddCC(int cost, boolean isComplete) {
            this.cost = cost;
            this.isComplete = isComplete;
        }

        public int getCost() {
            return cost;
        }

        public boolean isComplete() {
            return isComplete;
        }
    }
}
