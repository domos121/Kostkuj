package me.domos.Kostkuj.connect.discord;

import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.getTps;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordCommandTranslator {

    private getTps tps = new getTps();
    private SendSystem ss = new SendSystem();

    public void CommandTranslate(String[] args, User user){
        if (args[0].equalsIgnoreCase("/lag")){
            discordCommandLag();
        } else if (args[0].equalsIgnoreCase("/bc")){
            discordCommandBroadCast(args);
        }
    }

    private void discordCommandLag(){
        DiscordConnect.sendMsg("**TPS**: " + tps.getTps());
        DiscordConnect.sendMsg("**Online**: " + Bukkit.getServer().getOnlinePlayers().size() + "/100");
        String onlineplayers = "";
        for (Player p : Bukkit.getOnlinePlayers()){
            onlineplayers = onlineplayers + p.getName() + ", ";
        }
        DiscordConnect.sendMsg("**OnlinePlayers**: " + onlineplayers);
    }

    private void discordCommandBroadCast(@NotNull String args[]){
        String msg = "";
        for (int i = 1; i < args.length; i++)
            msg = msg + args[i] + " ";
        msg = msg.trim();
        ss.broadCast(msg);
    }

}
