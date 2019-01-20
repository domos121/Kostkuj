package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.connect.MySQL.player.MPlayerDeath;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;

public class Kostkuj_Death {

    SendSystem ss = new SendSystem();
    MPlayerDeath mpd = new MPlayerDeath();

    public boolean onCommand(CommandSender sr, String[] args) {
        String user = sr.getName();

        if (args.length == 2){
            if (!sr.hasPermission("kostkuj.death.other")){
                ss.noPerm(sr);
                return true;
            }
            user = args[1];
        }

        mpd.getDeath(user, sr);

        return false;
    }
}
