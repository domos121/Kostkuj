package me.domos.kostkuj.general.connect.mysql.player;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.json.CustomJsonBuilder;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.general.connect.mysql.MySQL;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MCommands {

    MySQL m = new MySQL();
    JsonSendMessage jsm = new JsonSendMessage();
    CustomJsonBuilder cjb = new CustomJsonBuilder();
    SendSystem ss = new SendSystem();

    public void getGroups(CommandSender sr) {
        PreparedStatement ps = m.getStatement("SELECT * FROM game_user_groups");
        try {
            ResultSet rs = ps.executeQuery();
            sr.sendMessage("§8======§7 KOSTKUJ: §cHELP §8======");
            while (rs.next()) {
                String groupa = rs.getString("name");
                String desc = rs.getString("description");
                if (desc == null || desc.equals("")) {
                    desc = "allow";
                }
                int id = rs.getInt("id");
                if (desc.equals("allow") || sr.hasPermission(desc)) {
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText("§8 [§a✔§8] §7" + groupa, "red", "§c" + groupa + ":", "run_command", "/prikazy " + id));
                } else {
                    jsm.jsonBcKostkuj(sr, cjb.hoverText("§8 [§c✖§8] §7" + groupa, "red", "§c" + groupa + ":"));
                }
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

    public void getCmd(CommandSender sr, int ids) {
        PreparedStatement ps = m.getStatement("SELECT gug.name, gug.description, g.command, g.description FROM game_user_command_group_relations relation INNER JOIN game_user_commands g on relation.game_user_command_id = g.id INNER JOIN game_user_groups gug on relation.game_user_group_id = gug.id WHERE relation.game_user_group_id = '" + ids + "';");
        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String desc = rs.getString("gug.description");
                if (desc == null || desc.equals("")) {
                    desc = "allow";
                }
                String popis = rs.getString("g.description");
                String cmd = rs.getString("g.command");
                if (desc.equals("allow") || sr.hasPermission(desc)) {
                    sr.sendMessage("§8======§7 KOSTKUJ: §cHELP-" + rs.getString("gug.name") + " §8======");
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- §7➥§c" + cmd, "red", "§c" + cmd + ":\n§7" + popis, "suggest_command", cmd));
                } else {
                    ss.info(sr, "K tomuto nemáš přístup!");
                }
            }
            while (rs.next()) {
                String popis = rs.getString("g.description");
                String cmd = rs.getString("g.command");
                String desc = rs.getString("gug.description");
                if (desc == null || desc.equals("")) {
                    desc = "allow";
                }
                if (desc.equals("allow") || sr.hasPermission(desc)) {
                    jsm.jsonBcKostkuj(sr, cjb.clickhoverText(" §8- §7➥§c" + cmd, "red", "§c" + cmd + ":\n§7" + popis, "suggest_command", cmd));
                }
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
