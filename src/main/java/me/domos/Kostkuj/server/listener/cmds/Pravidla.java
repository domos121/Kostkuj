package me.domos.Kostkuj.server.listener.cmds;

import me.domos.Kostkuj.connect.MySQL.player.MPravidla;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Pravidla implements CommandExecutor {

    SendSystem ss = new SendSystem();
    MPravidla mp = new MPravidla();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission("kostkuj.pravidla")){
            ss.noPerm(sr);
            return false;
        }

        if(args.length == 0) {
            mp.getGroups(sr);
            return true;
        }

        if (!StringUtils.isNumeric(args[0])){
            ss.info(sr, "Spatné cislo");
            return true;
        }

        if (args.length > 0){
            mp.getRules(sr, Integer.parseInt(args[0]));
            return true;
        }

        return false;
    }
}
