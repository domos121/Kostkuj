package me.domos.kostkuj.general.connect.mysql.shop;

import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GetItem {
    private MySQL mySQL = new MySQL();

    public HashMap<Integer, String> getItem(String key, Player p){
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "NENALEZEN");
        if(mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM shop_purchases pur INNER JOIN shop_products prod on pur.product_id = prod.id WHERE pur.serial_key = ? AND pur.is_complete = 0");
            PreparedStatement ps2 = mySQL.getStatement("UPDATE shop_purchases set is_complete = 1, used_user_id = (SELECT id FROM web_users WHERE username = ?), used_date = ? WHERE serial_key = ?");
            try {
                ps.setString(1, key);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    map.clear();
                    String item = rs.getString("prod.item_id");
                    int amount = rs.getInt("prod.amount");
                    String enchant = rs.getString("prod.enchant");
                    String nbt = rs.getString("prod.item_nbt_tag");
                    if (item == null) {
                        map.put(1, "ITEM_ERROR");
                    } else if (item.contains("PREMIUM_")) {
                        map.put(1, "PREMIUM");
                        map.put(2, item.replace("PREMIUM_", ""));
                    } else if (item.contains("COMMAND_")) {
                        map.put(1, "COMMAND");
                        map.put(2, item.replace("COMMAND_", ""));
                    } else if (nbt != null) {
                        map.put(1, "NBT");
                        map.put(2, item);
                        map.put(3, amount + "");
                        map.put(4, nbt);
                    } else if (item.contains("ENCHANTED_BOOK")) {
                        map.put(1, "BOOK");
                        map.put(2, amount + "");
                        map.put(3, enchant);
                    } else if (item.contains("KEY_S_")) {
                        map.put(1, "KEY");
                        map.put(2, item.replace("KEY_S_", "KEY_"));
                        map.put(3, amount + "");
                    }  else {
                        map.put(1, "ITEM");
                        map.put(2, item);
                        map.put(3, amount + "");
                        map.put(4, enchant);
                    }
                    ps2.setString(1, p.getName());
                    ps2.setTimestamp(2, Time.getTimeDay(0));
                    ps2.setString(3, key);
                    ps2.executeUpdate();
                    ps2.close();
                }
            } catch (SQLException e){
                map.put(1, "ITEM_ERROR");
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
