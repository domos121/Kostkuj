package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.time.Timer;
import me.domos.kostkuj.bukkit.time.TimerSettings;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;

public class Kostkuj_Restart {

    private Timer t = new Timer();
    private SendSystem ss = new SendSystem();
    private TimerSettings rts = TimerSettings.getInstance();

    public Kostkuj_Restart() {
    }

    public boolean onCommand(CommandSender sr, String[] args) {
        if (!sr.hasPermission("kostkuj.restart")) {
            this.ss.info(sr, "Pristup odepren");
            return true;
        } else if (args.length == 1) {
            this.ss.info(sr, "Use: /kostkuj restart <time(s)>|<stop>");
            return true;
        } else if (!StringUtils.isNumeric(args[1])) {
            if (args[1].equalsIgnoreCase("stop")) {
                this.ss.info(sr, "Vypl si odpocet pro restart serveru!");
                this.rts.setStop(true);
                return true;
            } else {
                this.ss.info(sr, "Use: /kostkuj restart <time(s)>|<stop>");
                return true;
            }
        } else if (!this.rts.isStop()) {
            this.ss.info(sr, "Odpočet už běží.");
            return true;
        } else if (Integer.parseInt(args[1]) < 7200 && Integer.parseInt(args[1]) >= 15) {
            BossBar bar = Bukkit.createBossBar(ChatColor.GOLD + "Server bude restartovan za: " + ChatColor.GREEN + args[1] + ChatColor.GOLD + "s.", BarColor.PINK, BarStyle.SOLID, new BarFlag[0]);
            this.rts.setStop(false);
            this.t.serverRestart(Integer.parseInt(args[1]), bar, "restartovan");
            ss.broadCast(ChatColor.GOLD + "Server bude " + "restartovan" + " za: " + ChatColor.GREEN + args[1] + ChatColor.GOLD + "s.");
            return false;
        } else {
            this.ss.info(sr, "Muzes zadat interval od 15s - 2h");
            return true;
        }
    }
}
