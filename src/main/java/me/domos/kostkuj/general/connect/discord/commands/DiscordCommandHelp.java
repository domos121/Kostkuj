package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.player.EKostkujRole;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandHelp implements IDiscordCommand{
    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        String commandList = EDiscordCmd.getCommandList(role);

        DiscordConnect.sendPrivateMsg(commandList, event.getAuthor());
        return false;
    }
}
