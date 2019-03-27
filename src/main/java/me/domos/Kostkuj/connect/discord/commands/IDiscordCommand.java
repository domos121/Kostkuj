package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.enums.EKostkujRole;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public interface IDiscordCommand {
    boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role);
}
