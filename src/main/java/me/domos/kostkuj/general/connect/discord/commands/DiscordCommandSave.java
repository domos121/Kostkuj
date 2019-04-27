package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.bukkit.listeners.cmds.kostkuj.Kostkuj_Save;
import me.domos.kostkuj.bukkit.player.EKostkujRole;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class DiscordCommandSave implements IDiscordCommand{

    private Kostkuj_Save s = new Kostkuj_Save();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        s.saveWorld();
        return false;
    }
}
