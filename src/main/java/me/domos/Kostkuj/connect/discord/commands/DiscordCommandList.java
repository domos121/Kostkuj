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
        StringBuilder msg = new StringBuilder();
        msg.append("===== **LIST** =====\n");
        msg.append("**TPS**: " + tps.getTps() + "\n");
        msg.append("**Online**: " + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + "\n");
        msg.append("**OnlinePlayers**: ");
        for (Player p : Bukkit.getOnlinePlayers()){
            msg.append(p.getName() + ", ");
        }
        DiscordConnect.sendMsg(msg.toString());
        return false;
    }
}
