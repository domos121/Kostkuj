package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.player.EKostkujRole;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public interface IDiscordCommand {
    boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role);
}
