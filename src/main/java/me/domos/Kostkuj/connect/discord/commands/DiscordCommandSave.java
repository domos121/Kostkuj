package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.enums.EKostkujRole;
import me.domos.Kostkuj.server.listener.cmds.kostkuj.Kostkuj_Save;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandSave implements IDiscordCommand{

    private Kostkuj_Save s = new Kostkuj_Save();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        s.saveWorld();
        return false;
    }
}
