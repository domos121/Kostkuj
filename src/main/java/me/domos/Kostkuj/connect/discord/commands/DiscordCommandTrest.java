package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.enums.EKostkujRole;
import me.domos.Kostkuj.server.trests.Trest;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandTrest implements IDiscordCommand{

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        new Trest(args, event, role);
        return false;
    }
}
