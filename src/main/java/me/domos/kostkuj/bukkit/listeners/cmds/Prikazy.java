package me.domos.kostkuj.bukkit.listeners.cmds;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.connect.mysql.player.MCommands;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Prikazy implements CommandExecutor {

    SendSystem ss = new SendSystem();
    MCommands mp = new MCommands();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission("kostkuj.prikazy")){
            ss.noPerm(sr);
            return false;
        }

        if (!(sr instanceof Player)){
            ss.noPlayer(sr);
            return true;
        }

        // pokud je příkaz pouze /cmd stane se mg.getGr...
        if(args.length == 0) {
            mp.getGroups(sr);
            return true;
        }

        // pokud ne skonztroluje zda je to číslo pokud ne ss.info...
        if (!StringUtils.isNumeric(args[0])){
            ss.info(sr, "Spatné čislo");
            return true;
        }

        // pokud tzo číslo je vypíše to příkazy.
        if (args.length > 0){
            mp.getCmd(sr, Integer.parseInt(args[0]));
            return true;
        }

        return false;
    }
}
