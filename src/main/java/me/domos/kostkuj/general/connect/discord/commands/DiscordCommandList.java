package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.GetTps;
import me.domos.kostkuj.bukkit.player.EKostkujRole;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DiscordCommandList implements IDiscordCommand{

    private GetTps tps = new GetTps();

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
