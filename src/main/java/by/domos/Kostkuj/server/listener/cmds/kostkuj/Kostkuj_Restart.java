package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.time.Timer;
import by.domos.Kostkuj.server.time.restartingTimerSettings;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;

public class Kostkuj_Restart {

    Timer t = new Timer();
    SendSystem ss = new SendSystem();
    restartingTimerSettings rts = restartingTimerSettings.getInstance();

    public boolean onCommand(CommandSender sr, String[] args) {
        if (!sr.hasPermission("kostkuj.restart")){
            ss.info(sr, "Pristup odepren");
            return true;
        }


        if (args.length == 1){
            ss.info(sr, "Use: /kostkuj restart <time(s)>|<stop>");
            return true;
        }

        if (!StringUtils.isNumeric(args[1])) {
            if (args[1].equalsIgnoreCase("stop")){
                ss.info(sr,"Vypl si odpocet pro restart serveru!");
                rts.setStop(true);
                return true;
            }
            ss.info(sr,"Use: /kostkuj restart <time(s)>|<stop>");
            return true;
        }

        if (rts.isStop() == false){
            ss.info(sr, "Odpočet už běží.");
            return true;
        }

        if ((Integer.parseInt(args[1]) >= 60 * 60 * 2) || (Integer.parseInt(args[1]) < 15)){
            ss.info(sr, "Muzes zadat interval od 15s - 2h");
            return true;
        }

        BossBar bar = Bukkit.createBossBar(ChatColor.GOLD + "Server bude restartovan za: " + ChatColor.GREEN + args[1] + ChatColor.GOLD + "s.", BarColor.PINK, BarStyle.SOLID);

        rts.setStop(false);
        t.serverRestart(Integer.parseInt(args[1]), bar, "restartovan");
        return false;
    }
}
