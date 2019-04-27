package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.player.EKostkujRole;
import me.domos.kostkuj.models.banModel.Trest;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandTrest implements IDiscordCommand{

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        new Trest(args, event, role);
        return false;
    }
}
