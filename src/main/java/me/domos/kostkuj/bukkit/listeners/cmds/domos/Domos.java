package me.domos.kostkuj.bukkit.listeners.cmds.domos;

import me.domos.kostkuj.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Domos implements CommandExecutor, TabCompleter {



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable(){
            @Override
            public void run() {
                sender.sendMessage("abc");
            }
        });
        return false;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] agrs) {
        List<String> tab = new ArrayList<>();
        List<String> cmd = new ArrayList<>();
        return tab;
    }
}
