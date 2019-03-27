package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.Main;
import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.EKostkujRole;
import me.domos.Kostkuj.server.getTps;
import me.domos.Kostkuj.server.time.Time;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;


public class DiscordCommandLag implements IDiscordCommand{

    private getTps tps = new getTps();
    private int mb = 1024 * 1024;
    private Runtime runtime = Runtime.getRuntime();
    private Time time = new Time();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        StringBuilder msg = new StringBuilder();
        msg.append("===== **LAG** =====\n");
        msg.append("**TPS**: " + tps.getTps() + "\n");
        msg.append("**Uptime:** " + time.minuteToTime((int)(System.currentTimeMillis()- Main.startTime)/50) + "\n");
        for (World world : Bukkit.getWorlds()){
            msg.append("**" + world.getName() + "** (Entity: " + world.getEntityCount() + " ,Chunks: " + world.getChunkCount() + " ,Players: " + world.getPlayerCount() + ")\n");
        }

        msg.append("**RAM**: " + (runtime.totalMemory() - runtime.freeMemory()) / mb + "/" + runtime.maxMemory() / mb + "\n");
        msg.append("**Přidělená RAM**: " + runtime.totalMemory() / mb + "\n");
        msg.append("**Volná RAM**: " + runtime.freeMemory() / mb + "\n");

        DiscordConnect.sendMsg(msg.toString());
        return false;
    }
}