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

public class Kostkuj_Stop {
    private Timer t = new Timer();
    private SendSystem ss = new SendSystem();
    private TimerSettings rts = TimerSettings.getInstance();

    public boolean onCommand(CommandSender sr, String[] args) {
        if (!sr.hasPermission("kostkuj.stop")) {
            this.ss.info(sr, "Pristup odepren");
            return true;
        } else if (args.length == 1) {
            this.ss.info(sr, "Use: /kostkuj stop <time(s)>|<stop> <message:{time}>");
            return true;
        } else if (!StringUtils.isNumeric(args[1])) {
            if (args[1].equalsIgnoreCase("stop")) {
                this.ss.info(sr, "Vypl si odpocet pro vypnuti serveru!");
                this.rts.setStop(true);
                return true;
            } else {
                this.ss.info(sr, "Use: /kostkuj stop <time(s)>|<stop> <message:{time}>");
                return true;
            }
        } else if (!this.rts.isStop()) {
            this.ss.info(sr, "Odpočet už běží.");
            return true;
        } else if (Integer.parseInt(args[1]) < 7200 && Integer.parseInt(args[1]) >= 15) {
            if(args[2].contains("message:")){
                String desc = "";
                for (int i = 2;  args.length > i; i++){
                    desc = desc + " " + args[i];
                }
                desc = desc.replace("message:", "");
                ss.broadCast(desc);
                BossBar bar = Bukkit.createBossBar(desc.replace("{time}", args[1] + "s"), BarColor.PINK, BarStyle.SOLID, new BarFlag[0]);
                this.t.serverRestart(Integer.parseInt(args[1]), bar, desc, "vypnut");
                return false;
            }
            BossBar bar = Bukkit.createBossBar(ChatColor.GOLD + "Server bude vypnut za: " + ChatColor.GREEN + args[1] + ChatColor.GOLD + "s.", BarColor.PINK, BarStyle.SOLID, new BarFlag[0]);
            this.rts.setStop(false);
            this.t.serverRestart(Integer.parseInt(args[1]), bar, ChatColor.GOLD + "Server bude vypnut za: " + ChatColor.GREEN + "{time}" + ChatColor.GOLD + "s.", "vypnut");
            return false;
        } else {
            this.ss.info(sr, "Muzes zadat interval od 15s - 2h");
            return true;
        }
    }
}
