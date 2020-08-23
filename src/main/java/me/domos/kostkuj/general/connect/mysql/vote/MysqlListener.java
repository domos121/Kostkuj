package me.domos.kostkuj.general.connect.mysql.vote;

import com.google.gson.JsonObject;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import me.domos.kostkuj.models.voteModel.VoteTopPlayers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MysqlListener {

    private MySQL mysql = new MySQL();
    private ItBuilder it = ItBuilder.getInstance();
    private Time time = new Time();

    public void setVote(String user){
        try {
            PreparedStatement ps = mysql.getStatement("INSERT INTO game_gift_votes (user_id, time) VALUE ((SELECT id FROM web_users WHERE username = ?), ?)");
            ps.setString(1, user);
            ps.setTimestamp(2, time.getTimeDay(0));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public void setStorrage(String user, String product){
        try {
            PreparedStatement ps = mysql.getStatement("INSERT INTO game_gift_key_storrage (produkt, create_time, user_id) VALUE (? , ?, (SELECT id FROM web_users WHERE username = ?))");
            ps.setString(1, product);
            ps.setTimestamp(2, time.getTimeDay(0));
            ps.setString(3, user);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public void deleteStorrage(){
        try {
        PreparedStatement ps = mysql.getStatement("DELETE FROM game_gift_key_storrage WHERE create_time < ?");
        ps.setTimestamp(1, time.getTimeDay(-2));
        ps.executeUpdate();
        ps.close();
        } catch (SQLException e) {
        }
    }

    public List<ItemStack> getSkladForInv(String user){
        List<ItemStack> items = new ArrayList<ItemStack>();
        try {
            PreparedStatement ps = mysql.getStatement("SELECT * FROM game_gift_key_storrage WHERE user_id = (SELECT id FROM web_users WHERE username = ?) LIMIT 27");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String[] produkt = rs.getString("produkt").split(",");
                ItemStack item = it.key("TRIPWIRE_HOOK", Integer.parseInt(produkt[0]), produkt[1]);
                ItemMeta im = item.getItemMeta();
                List<String> lore = Arrays.asList(id + "");
                im.setLore(lore);
                item.setItemMeta(im);
                items.add(item);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void deleteStorrageId(int id){
        try {
            PreparedStatement ps = mysql.getStatement("DELETE FROM game_gift_key_storrage WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getVotes(String user){
        try {
            PreparedStatement ps = mysql.getStatement("SELECT COUNT(*) FROM game_gift_votes gf WHERE user_id = (SELECT id FROM web_users WHERE username = ?) AND time < ?");
            ps.setString(1, user);
            ps.setTimestamp(2, time.getTimeDay(-30));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public boolean isTopVotersSet(){
        try {
            PreparedStatement ps = mysql.getStatement("SELECT * FROM game_gift_top_voters WHERE month_of_year = ?");
            ps.setString(1, VoteTopPlayers.keyLastMonth);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return true;
        }
        return false;
    }

    public void setTopVoters(JsonObject users){
        try {
            PreparedStatement ps = mysql.getStatement("INSERT INTO game_gift_top_voters (month_of_year, users) VALUE (? , ?)");
            ps.setString(1, VoteTopPlayers.keyLastMonth);
            ps.setString(2, users.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

}
