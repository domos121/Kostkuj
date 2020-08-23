package me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CrateKeysPattern implements ITabPattern {
    @Override
    public List<String> patternBuilder(CommandSender sr, Command command, String s, String[] args) {
        List<String> keys = new ArrayList<>();
        keys.add("1");
        keys.add("2");
        keys.add("3");
        return keys;
    }
}
