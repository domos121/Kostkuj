package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.player.EKostkujRole;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandRouter {



    public void CommandRoute(String[] args, GuildMessageReceivedEvent event, EKostkujRole role){
        EDiscordCmd command = EDiscordCmd.getCmd(args[0]);
        command.executeCommand(args, event, role);
    }
}
