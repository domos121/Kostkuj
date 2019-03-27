package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.EKostkujRole;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscorfCommandHelp implements IDiscordCommand{
    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        String commandList = EDiscordCmd.getCommandList(role);

        DiscordConnect.sendPrivateMsg(commandList, event.getAuthor());
        return false;
    }
}
