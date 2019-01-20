package me.domos.Kostkuj.server.listener.cmds.domos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Domos implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        return true;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] agrs) {
        List<String> tab = new ArrayList<>();
        List<String> cmd = new ArrayList<>();
        return tab;
    }
}
