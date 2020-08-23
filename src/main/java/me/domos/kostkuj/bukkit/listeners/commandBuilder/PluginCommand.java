package me.domos.kostkuj.bukkit.listeners.commandBuilder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class PluginCommand implements IPluginCommand {

    private String baseArgument;
    private List<List<String>> arguments;

    public PluginCommand(@Nonnull String baseArgument, List<String>... arguments) {
        this.baseArgument = baseArgument;
        this.arguments = Arrays.asList(arguments);
    }

    public PluginCommand(@Nonnull String baseArgument) {
        this.baseArgument = baseArgument;
    }

    public String getBaseArgument() {
        return baseArgument;
    }

    public List<List<String>> getArguments() {
        return arguments;
    }

    @Override
    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        return false;
    }
}
