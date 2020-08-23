package me.domos.kostkuj.bukkit.listeners;

import me.domos.kostkuj.bukkit.listeners.commandBuilder.IPluginCommand;
import me.domos.kostkuj.bukkit.listeners.commandBuilder.PluginCommand;
import me.domos.kostkuj.bukkit.listeners.commandBuilder.PluginTabCompletor;
import me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns.ETabPatterns;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EPermission {
    KOSTKUJ("", new PluginCommand("kostkuj")),
    KOSTKUJ_JOIN_SLIENT(""),
    KOSTKUJ_RESTART("kostkuj restart", new PluginCommand("kostkuj", Arrays.asList(new String[]{"restart"}))),
    KOSTKUJ_SAVE("kostkuj save", new PluginCommand("kostkuj", Arrays.asList(new String[]{"save"}))),
    KOSTKUJ_STOP("kostkuj stop", new PluginCommand("kostkuj", Arrays.asList(new String[]{"stop"}))),
    KOSTKUJ_POZEMEK("kostkuj pozemek", new PluginCommand("kostkuj", Arrays.asList(new String[]{"pozemek"}))),
    KOSTKUJ_TOPTIME("kostkuj toptime", new PluginCommand("kostkuj",Arrays.asList(new String[]{"toptime"}), Arrays.asList(new String[]{"{player}"}))),
    KOSTKUJ_DEATH("kostkuj death", new PluginCommand("kostkuj", Arrays.asList(new String[]{"death"}))),
    KOSTKUJ_DEATH_OTHER("kostkuj death <user>", new PluginCommand("kostkuj", Arrays.asList(new String[]{"death"}), Arrays.asList(new String[]{"{player}"}))),
    KOSTKUJ_DISCORDAUTH("kostkuj discordauth", new PluginCommand("kostkuj", Arrays.asList(new String[]{"discordauth"}))),
    KOSTKUJ_COMMADBLOCKLIST("kostkuj commandblocklist", new PluginCommand("kostkuj", Arrays.asList(new String[]{"commandblocklist"}))),
    KOSTKUJ_WRITER("kostkuj writer", new PluginCommand("kostkuj", Arrays.asList(new String[]{"writer"}))),
    KOSTKUJ_NYNIJEAFK("kostkuj nynijeafk", new PluginCommand("kostkuj", Arrays.asList(new String[]{"nynijeafk"}))),
    // DOMOS
    DOMOS("", new PluginCommand("kostkuj")),
    DOMOS_JOIN_SLIENT(""),
    DOMOS_RESTART("kostkuj restart", new PluginCommand("domos", Arrays.asList(new String[]{"restart"}))),
    DOMOS_SAVE("kostkuj save", new PluginCommand("domos", Arrays.asList(new String[]{"save"}))),
    DOMOS_STOP("kostkuj stop", new PluginCommand("domos", Arrays.asList(new String[]{"stop"}))),
    DOMOS_POZEMEK("kostkuj pozemek", new PluginCommand("domos", Arrays.asList(new String[]{"pozemek"}))),
    DOMOS_TOPTIME("kostkuj toptime", new PluginCommand("domos",Arrays.asList(new String[]{"toptime"}), Arrays.asList(new String[]{ETabPatterns.ONLINEPLAYERS.getArgumentName()}), Arrays.asList(new String[]{"jde", "nejde"}))),
    DOMOS_DEATH("kostkuj death", new PluginCommand("domos", Arrays.asList(new String[]{"death"}), Arrays.asList(new String[]{ETabPatterns.CRATEKEYS.getArgumentName()}), Arrays.asList(new String[]{ETabPatterns.ONLINEPLAYERS.getArgumentName()}))),
    DOMOS_DEATH_OTHER("kostkuj death <user>", new PluginCommand("domos", Arrays.asList(new String[]{"death"}), Arrays.asList(new String[]{ETabPatterns.ONLINEPLAYERS.getArgumentName()}))),
    DOMOS_DISCORDAUTH("kostkuj discordauth", new PluginCommand("domos", Arrays.asList(new String[]{"discordauth"}))),
    DOMOS_COMMADBLOCKLIST("kostkuj commandblocklist", new PluginCommand("domos", Arrays.asList(new String[]{"commandblocklist"}))),
    DOMOS_WRITER("kostkuj writer", new PluginCommand("domos", Arrays.asList(new String[]{"writer"}))),
    DOMOS_NYNIJEAFK("kostkuj nynijeafk", new PluginCommand("domos", Arrays.asList(new String[]{"nynijeafk"}))),
    DOMOS_ITEM_BUILDER("kostkuj nynijeafk", new PluginCommand("domos", Arrays.asList(new String[]{"it"}), Arrays.asList(new String[]{"it"}), Arrays.asList(new String[]{ETabPatterns.ITEMBUILDER.getArgumentName()}), Arrays.asList(new String[]{ETabPatterns.ONLINEPLAYERS.getArgumentName()}))),
    ;

    private String description;
    private IPluginCommand command;

    EPermission(String description, @Nonnull IPluginCommand command) {
        this.description = description;
        this.command = command;
    }

    EPermission(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }


    public IPluginCommand getCommand() {
        return this.command;
    }

    public String getPerm(){
        String perm = this.toString().toLowerCase().replace("_", ".");
        if (perm.contains("kostkuj")){
            return perm;
        }
        return "kostkuj." + perm;
    }

    public static List<EPermission> getCommandList(){
        List<EPermission> commands = new ArrayList<>();
        for (EPermission permission : EPermission.values()){
            if (permission.command != null){
                commands.add(permission);
            }
        }
        return commands;
    }



    public static List<String> tabCompletor(CommandSender sr, Command cmd, String s, String[] args){
        return new PluginTabCompletor(sr, cmd, s, args).getAccessArguments();
    }

}

