package by.domos.Kostkuj.server.listener.cmds;

import by.domos.Kostkuj.connect.MySQL.player.MCommands;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Prikazy implements CommandExecutor {

    SendSystem ss = new SendSystem();
    MCommands mp = new MCommands();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission("kostkuj.prikazy")){
            ss.noPerm(sr);
            return false;
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
