package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.ECmd;
import me.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import me.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_NyniJeAfk {

    private JsonBroadCast jbc = new JsonBroadCast();
    private CustomJsonBuilder cjb = new CustomJsonBuilder();
    private SendSystem ss = new SendSystem();

    public Kostkuj_NyniJeAfk() {
    }

    public void onCommand(CommandSender sr, String[] args) {
        if (args.length == 1) {
            this.ss.use(sr, ECmd.KOSTKUJ_NYNIJEAFK.getCmd());
        } else {
            OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
            if (!op.isOnline()) {
                this.ss.info(sr, "Hrac neni online.");
            } else {
                Player p = (Player) op;
                Location l = p.getLocation();
                String json = this.cjb.vetaClickHoverText("§6Hráč §r", "", p.getDisplayName(), "", p.getDisplayName() + "§7:\nLokace: §cx:" + l.getBlockX() + ", y:" + l.getBlockY() + ", z:" + l.getBlockZ() + "\n§7Svet: §c" + l.getWorld().getName().trim(), "suggest_command", "/tppos " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ() + " " + l.getWorld().getName().trim(), "§6 je afk více než 30 min.", "");
                this.jbc.jsonBcKostkuj(json, ECmd.KOSTKUJ_NYNIJEAFK.getPerm());
                DiscordConnect.sendOrangeMsg("Hráč " + p.getDisplayName() + " je afk více než 30 min.");
            }
        }
    }
}
