package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.enums.EKostkujRole;
import me.domos.Kostkuj.server.trests.Trest;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandTrest implements IDiscordCommand{

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        String[] args2 = new String[args.length-1];
        for (int i = 0; args.length > i; i++){
            if (i != 0){
                args2[i-1] = args[i];
            }
        }
        new Trest(args2, event, role);
        return false;
    }
}
