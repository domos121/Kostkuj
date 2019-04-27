package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.player.EKostkujRole;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandBroadCast implements IDiscordCommand{

    private SendSystem ss = new SendSystem();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        String msg = "";
        for (int i = 0; i < args.length; i++)
            msg = msg + args[i] + " ";
        msg = msg.trim();
        ss.broadCast(msg);
        return false;
    }
}
