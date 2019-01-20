package me.domos.Kostkuj.server.chat.BanManager;

import me.domos.Kostkuj.server.time.Time;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;

public class SendMessgeIsBanned {

    Time time = new Time();

    public void banMessage(CommandSender sr, String name, String banner, String reason, int setunix, int outunix, String typbanu, String styp) {
        TextComponent prefix = new TextComponent("");
        prefix.addExtra("§8[§cKostkuj§8]:");
        TextComponent text = new TextComponent("");
        text.addExtra("§7 " + styp + " ");
        TextComponent text2 = new TextComponent("");
        text2.addExtra("§7 uz je zabanovan/a!");
        TextComponent player = new TextComponent("▶" + name);
        player.setColor(ChatColor.RED);
        player.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/unbanall " + name));
        player.setHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(name + ":\n")
                                .color(ChatColor.RED)
                                .append("Typ banu: ")
                                .color(ChatColor.RED)
                                .append(typbanu + "\n")
                                .color(ChatColor.GRAY)
                                .append("Datum banu: ")
                                .color(ChatColor.RED)
                                .append(time.unixToTime(setunix) + "\n")
                                .color(ChatColor.GRAY)
                                .append("Datum expirace: ")
                                .color(ChatColor.RED)
                                .append(time.unixToTime(outunix) + "\n")
                                .color(ChatColor.GRAY)
                                .append("Banoval: ")
                                .color(ChatColor.RED)
                                .append(banner + "\n")
                                .color(ChatColor.GRAY)
                                .append("Duvod: ")
                                .color(ChatColor.RED)
                                .append(reason + "")
                                .color(ChatColor.GRAY)
                                .create()));
        sr.spigot().sendMessage(prefix, text, player, text2);
    }
}
