package me.domos.kostkuj.bukkit.listeners.cmds.bans;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.models.banModel.Trest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

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

        new Trest(sr, args);
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
        if (args[args.length-1].contains("u:")){
            argumenty.clear();
            for (Player  p: Bukkit.getServer().getOnlinePlayers()){
                String editPlayer = "u:" + p.getName();
                if (editPlayer.contains(args[args.length-1])){
                    argumenty.add(editPlayer);
                }
            }
            return argumenty;
        }
        if (args[args.length-1].contains("a:")){
            argumenty.clear();
            for (String  akce: this.akce()){
                if (akce.contains(args[args.length-1])){
                    argumenty.add(akce);
                }
            }
            return argumenty;
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

    private List<String> akce(){
     List<String> akce = new ArrayList<>();
     akce.add("a:ban");
     akce.add("a:tempban");
     akce.add("a:ipban");
     akce.add("a:tempipban");
     akce.add("a:kick");
     akce.add("a:mute");
     return akce;
    }

}
