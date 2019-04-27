package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.connect.mysql.player.MPlayerDeath;
import org.bukkit.command.CommandSender;

public class Kostkuj_Death {

    private SendSystem ss = new SendSystem();
    private MPlayerDeath mpd = new MPlayerDeath();

    public Kostkuj_Death() {
    }

    public boolean onCommand(CommandSender sr, String[] args) {
        String user = sr.getName();
        if (args.length == 2) {
            if (!sr.hasPermission("kostkuj.death.other")) {
                this.ss.noPerm(sr);
                return true;
            }

            user = args[1];
        }

        this.mpd.getDeath(user, sr);
        return false;
    }
}
