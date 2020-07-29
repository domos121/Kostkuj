package me.domos.kostkuj.general.connect.discord.commands;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.GetTps;
import me.domos.kostkuj.bukkit.player.EKostkujRole;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;


public class DiscordCommandLag implements IDiscordCommand{

    private GetTps tps = new GetTps();
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
            msg.append("**" + world.getName() + "** (Entity: " + world.getEntities().size() + " ,Chunks: " + world.getLoadedChunks().length + " ,Players: " + world.getPlayers().size() + ")\n");
        }

        msg.append("**RAM**: " + (runtime.totalMemory() - runtime.freeMemory()) / mb + "/" + runtime.maxMemory() / mb + "\n");
        msg.append("**Přidělená RAM**: " + runtime.totalMemory() / mb + "\n");
        msg.append("**Volná RAM**: " + runtime.freeMemory() / mb + "\n");

        DiscordConnect.sendMsg(msg.toString());
        return false;
    }
}