package by.domos.Kostkuj.server.listener.cmds.bans;

import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.trests.Trest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CMDTrest implements CommandExecutor, TabCompleter {

    SendSystem ss = new SendSystem();

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission("kostkuj.set.trest")){
            ss.noPerm(sr);
            return true;
        }

        Trest trest = new Trest(sr, args);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sr, Command cmd, String s, String[] args) {
        List<String> argumenty = argumenty();
        if (args[0] == null || args[0].equalsIgnoreCase("")){
            return argumenty;
        }
        for(int ar = 0; ar < args.length; ar++){

            for (int i = 0; argumenty.size() > i; i++){

                if (args[ar].contains(argumenty.get(i))){
                    argumenty.remove(i);
                }
                if (args[ar].contains("u:")){
                    argumenty.remove("p:");
                }
                if (args[ar].contains("p:")){
                    argumenty.remove("u:");
                }
            }
        }
        return argumenty;
    }

    private List<String> argumenty(){
        List<String> argumenty = new ArrayList<>();
        argumenty.add("u:");
        argumenty.add("r:");
        argumenty.add("t:");
        argumenty.add("d:");
        argumenty.add("a:");
        return argumenty;
    }

}
