package me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ITabPattern {
    List<String> patternBuilder(CommandSender sr, Command command, String s, String[] args);
}
