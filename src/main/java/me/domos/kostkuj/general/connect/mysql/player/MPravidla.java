package me.domos.kostkuj.general.connect.mysql.player;

import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MPravidla {

    MySQL m = new MySQL();
    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder cjb = new CustomJsonBuilder();

    public void getGroups(CommandSender sr) {
        PreparedStatement ps = m.getStatement("SELECT * FROM game_user_rules_groups");
        try {
            ResultSet rs = ps.executeQuery();
            sr.sendMessage("§8======§7 KOSTKUJ: §cPravidla §8======");
            while (rs.next()) {
                String groupa = rs.getString("name");
                int id = rs.getInt("id");
                jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8 [§a✔§8] §7" + groupa, "red", "§c" + groupa + ":", "run_command", "/pravidla " + id));
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getRules(CommandSender sr, int ids) {
        PreparedStatement ps = m.getStatement("SELECT * FROM game_user_rules rule INNER JOIN game_user_rules_groups groups ON rule.game_user_rules_group_id = groups.id WHERE game_user_rules_group_id = ' " + ids + "';");
        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sr.sendMessage("§8======§7 KOSTKUJ: §c" + rs.getString("groups.name") + " §8======");
                String pravidlo = rs.getString("description");
                int id = rs.getInt("id");
                jsm.jsonBcKostkuj(sr, cjb.hoverText(" §8- §7➥§c&" + id, "red", "§c&" + id + ":\n§7" + pravidlo));
            }
            while (rs.next()) {
                String pravidlo = rs.getString("description");
                int id = rs.getInt("id");
                jsm.jsonBcKostkuj(sr, cjb.hoverText(" §8- §7➥§c&" + id, "red", "§c&" + id + ":\n§7" + pravidlo));
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
