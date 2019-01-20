package by.domos.Kostkuj.server.chat.BanManager;

import by.domos.Kostkuj.connect.discord.DiscordConnect;
import by.domos.Kostkuj.server.time.Time;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SendBanMessage {

    public void sendBanMessage(String name, String admin, String typbanu, String settime, String expiretime, int idtrstu, String dis, String rule) {
        List<Player> onlinePlayers = new ArrayList<Player>();
        for (Player c : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(c);
            TextComponent prefix = new TextComponent("");
            prefix.addExtra("§8[§cKostkuj§8]:");
            TextComponent text = new TextComponent("");
            text.addExtra("§7 Hrisnik ");
            text.setColor(ChatColor.GRAY);
            TextComponent text2 = new TextComponent("");
            text2.addExtra(" byl potrestan!");
            text2.setColor(ChatColor.GRAY);
            TextComponent player = new TextComponent("➥" + name);
            player.setColor(ChatColor.RED);
            player.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/pi " + name));
            player.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("TREST:" + "\n").color(ChatColor.GRAY).append("ID trestu:").color(ChatColor.RED).append(" #" + idtrstu + "\n").color(ChatColor.GRAY).append("Uzivatel: ").color(ChatColor.RED).append(name + "\n").color(ChatColor.GRAY).append("Typ trestu: ").color(ChatColor.RED).append(typbanu + "\n").color(ChatColor.GRAY).append("Datum trestu: ").color(ChatColor.RED).append(settime + "\n").color(ChatColor.GRAY).append("Datum expirace: ").color(ChatColor.RED).append(expiretime + "\n").color(ChatColor.GRAY).append("Trestal: ").color(ChatColor.RED).append(admin + "\n").color(ChatColor.GRAY).append("Porusena pravidla: ").color(ChatColor.RED).append(rule + "\n").color(ChatColor.GRAY).append("Popis: ").color(ChatColor.RED).append(dis + "").color(ChatColor.GRAY).create()));
            c.spigot().sendMessage(prefix, text, player, text2);
        }
        DiscordConnect.sendMsg("```yaml\n" +
                "TREST: #" + idtrstu + ".\n" +
                "Hrisnik: " + name + ".\n" +
                "Obdrzel: " + typbanu + ".\n" +
                "Admin: " + admin + ".\n" +
                "Datum: " + settime + ".\n" +
                "Expirace: " + expiretime + ".\n" +
                "Popis: " + dis + ".\n" +
                "Rule: " + rule + ".\n" +
                "```");

    }
}
