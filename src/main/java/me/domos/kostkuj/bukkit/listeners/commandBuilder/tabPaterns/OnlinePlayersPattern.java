package me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OnlinePlayersPattern implements ITabPattern {



    @Override
    public List<String> patternBuilder(CommandSender sr, Command command, String s, String[] args) {
        List<String> tab = new ArrayList<>();
        List<String> cmd = new ArrayList<>();
        for (Player p : Bukkit.getServer().getOnlinePlayers()){
            cmd.add(p.getName());
        }
        if (args[args.length-1].equalsIgnoreCase("")){
            tab = cmd;
            return tab;
        }
        // CHAR FILTER
        for (int i = 0; i < cmd.size(); i++){
            if (cmd.get(i).contains(args[args.length-1])){
                tab.add(cmd.get(i));
            }
        }
        return tab;
    }
}
