package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.EKostkujRole;
import me.domos.Kostkuj.server.getTps;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DiscordCommandList implements IDiscordCommand{

    private getTps tps = new getTps();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        DiscordConnect.sendMsg("**TPS**: " + tps.getTps());
        DiscordConnect.sendMsg("**Online**: " + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
        String onlineplayers = "";
        for (Player p : Bukkit.getOnlinePlayers()){
            onlineplayers = onlineplayers + p.getName() + ", ";
        }
        DiscordConnect.sendMsg("**OnlinePlayers**: " + onlineplayers);
        return false;
    }
}
