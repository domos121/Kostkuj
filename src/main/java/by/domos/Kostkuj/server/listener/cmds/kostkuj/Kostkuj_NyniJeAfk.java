package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.connect.discord.DiscordConnect;
import by.domos.Kostkuj.enums.ECmd;
import by.domos.Kostkuj.server.chat.JSON.CustomJsonBuilder;
import by.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_NyniJeAfk {

    JsonBroadCast jbc = new JsonBroadCast();
    CustomJsonBuilder cjb = new CustomJsonBuilder();
    SendSystem ss = new SendSystem();

    public void onCommand(CommandSender sr, String[] args){
        if (args.length == 1){
            ss.use(sr, ECmd.KOSTKUJ_NYNIJEAFK.getCmd());
            return;
        }

        OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
        if (!op.isOnline()){
            ss.info(sr, "Hrac neni online.");
            return;
        }
        Player p = Bukkit.getPlayer(op.getName());
        Location l = p.getLocation();
        String json = cjb.vetaClickHoverText("§6Hráč §r", "", p.getDisplayName(), "", p.getDisplayName() + "§7:\nLokace: §cx:" + l.getBlockX() + ", y:" + l.getBlockY() + ", z:" + l.getBlockZ() + "\n§7Svet: §c" + l.getWorld().getName().trim(), "suggest_command", "/tppos " + l.getBlockX() + " "+ l.getBlockY() + " " + l.getBlockZ() + " " + l.getWorld().getName().trim(), "§6 je afk více než 30 min.", "");
        jbc.jsonBcKostkuj(json, ECmd.KOSTKUJ_NYNIJEAFK.getPerm());
        DiscordConnect.sendMsg("```fix\n" + "Hráč " + p.getDisplayName() + " je afk více než 30 min." + "\n```");
    }
}
