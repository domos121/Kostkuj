package me.domos.Kostkuj.connect.discord.commands;

import me.domos.Kostkuj.connect.discord.DiscordConnect;
import me.domos.Kostkuj.enums.EKostkujRole;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.time.Timer;
import me.domos.Kostkuj.server.time.restartingTimerSettings;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class DiscordCommandRestart implements IDiscordCommand{

    private Timer t = new Timer();
    private restartingTimerSettings rts = restartingTimerSettings.getInstance();
    private SendSystem ss = new SendSystem();

    @Override
    public boolean onCommand(String[] args, GuildMessageReceivedEvent event, EKostkujRole role) {
        if (args.length == 0) {
            DiscordConnect.sendInfoMsg("Use: /restart <time(s)>|<stop>");
            return true;
        } else if (!StringUtils.isNumeric(args[0])) {
            if (args[0].equalsIgnoreCase("stop")) {
                DiscordConnect.sendInfoMsg("Vypl si odpocet pro restart serveru!");
                this.rts.setStop(true);
                return true;
            } else {
                DiscordConnect.sendInfoMsg("Use: /restart <time(s)>|<stop>");
                return true;
            }
        } else if (!this.rts.isStop()) {
            DiscordConnect.sendInfoMsg("Odpočet už běží.");
            return true;
        } else if (Integer.parseInt(args[0]) < 7200 && Integer.parseInt(args[0]) >= 15) {
            BossBar bar = Bukkit.createBossBar(ChatColor.GOLD + "Server bude restartovan za: " + ChatColor.GREEN + args[0] + ChatColor.GOLD + "s.", BarColor.PINK, BarStyle.SOLID, new BarFlag[0]);
            this.rts.setStop(false);
            this.t.serverRestart(Integer.parseInt(args[0]), bar, "restartovan");
            ss.broadCast(ChatColor.GOLD + "Server bude " + "restartovan" + " za: " + ChatColor.GREEN + args[0] + ChatColor.GOLD + "s.");
            return false;
        } else {
            DiscordConnect.sendInfoMsg("Muzes zadat interval od 15s - 2h");
            return true;
        }
    }
}
