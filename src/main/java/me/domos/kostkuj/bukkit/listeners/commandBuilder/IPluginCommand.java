package me.domos.kostkuj.bukkit.listeners.commandBuilder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface IPluginCommand {
    List<List<String>> getArguments();
    String getBaseArgument();
    boolean onCommand(CommandSender sr, Command cmd, String s, String[] args);

}
