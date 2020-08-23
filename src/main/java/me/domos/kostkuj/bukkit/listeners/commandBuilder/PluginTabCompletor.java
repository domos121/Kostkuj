package me.domos.kostkuj.bukkit.listeners.commandBuilder;

import me.domos.kostkuj.bukkit.listeners.EPermission;
import me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns.ETabPatterns;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class PluginTabCompletor {

    private CommandSender sr;
    private Command cmd;
    private String executeString;
    private String[] executeArguments;

    private int cursorPosition;

    List<EPermission> commandsList = new ArrayList<>();

    private List<String> accessArguments = new ArrayList<>();


    public PluginTabCompletor(CommandSender sr, Command cmd, String s, String[] args){
        this.sr = sr;
        this.cmd = cmd;
        this.executeString = s;
        this.executeArguments = args;
        this.cursorPosition = args.length;

        this.generateCommnadList();
    }

    private void generateCommnadList(){
        for (EPermission permission : EPermission.getCommandList()){
            if (permission.getCommand().getBaseArgument().equalsIgnoreCase(this.cmd.getName())){
                if (sr.hasPermission(permission.getPerm())) {
                    commandsList.add(permission);
                }
            }
        }
    }

    public List<String> getAccessArguments(){
        generateAccessArguments();
        return accessArguments;
    }

    private void generateAccessArguments(){
        // projede všechny dostupné commnady
        Bukkit.getServer().broadcastMessage("============================");
        for (int existCommandPosition = 0; commandsList.size() > existCommandPosition; existCommandPosition++){
            EPermission existCommand = commandsList.get(existCommandPosition);
            Bukkit.getServer().broadcastMessage(existCommand.toString());
            List<CommandArgument> argumentContains = new ArrayList<>();
            // projede včechny dostupné argumenty v commandu
            for (int existCommandArgumentPosition = 0; existCommand.getCommand().getArguments().size() > existCommandArgumentPosition; existCommandArgumentPosition++) {
                // list zda command obsahuje argumenty.
                List<String> existCommandArgument = existCommand.getCommand().getArguments().get(existCommandArgumentPosition);
                if (cursorPosition - 1 >= existCommandArgumentPosition) {
                    CommandArgument commandArgument = new CommandArgument(false, null);
                    // projede všechny dostupné možnosti v argumentu
                    for (int existCommandArgumentVariantPosition = 0; existCommandArgument.size() > existCommandArgumentVariantPosition; existCommandArgumentVariantPosition++) {
                        String existCommandArgumentVariant = existCommandArgument.get(existCommandArgumentVariantPosition);

                        // kontrola zda to je poslední akrgument
                        // pokud ano
                        if (cursorPosition - 1 == existCommandArgumentPosition) {
                            // kontrola zda je to pattern
                            if (ETabPatterns.isPattern(existCommandArgumentVariant)) {
                                ETabPatterns pattern = ETabPatterns.getPaternFromArgument(existCommandArgumentVariant);
                                boolean isAccesPattern = false;
                                for (String arg : pattern.getTabPattern().patternBuilder(this.sr, this.cmd, this.executeString, this.executeArguments)){
                                    if (arg.contains(executeArguments[existCommandArgumentPosition])){
                                        isAccesPattern = true;
                                        break;
                                    }
                                }
                                if (isAccesPattern){
                                    commandArgument = new CommandArgument(true, pattern);
                                } else {
                                    commandArgument = new CommandArgument(false, pattern);
                                }
                            } else {
                                if (existCommandArgumentVariant.contains(executeArguments[existCommandArgumentPosition])) {
                                    commandArgument = new CommandArgument(true, null);
                                }
                            }
                        //pokud ne
                        } else {
                            if (existCommandArgumentVariant.equalsIgnoreCase(executeArguments[existCommandArgumentPosition])) {
                                commandArgument = new CommandArgument(true, null);
                            } else {
                                for (ETabPatterns eTabPattern : ETabPatterns.values()) {
                                    if (existCommandArgumentVariant.equalsIgnoreCase(eTabPattern.getArgumentName())) {
                                        commandArgument = new CommandArgument(true, eTabPattern);
                                        break;
                                    }
                                }
                            }
                        }
                        argumentContains.add(commandArgument);
                        //Bukkit.getServer().broadcastMessage(commandArgument.isAccess() + "");
                    }
                }
                for (CommandArgument commandArgument : argumentContains){
                    Bukkit.getServer().broadcastMessage("IsAccess: " + commandArgument.isAccess() + ",IsPattern: " + commandArgument.isPattern() + ",Pattern: " + commandArgument.getPattern());
                }





























                // přidání možnosti argumentu.
                /*if (cursorPosition <= existCommand.getCommand().getArguments().size()) {
                    Boolean isAccess = true;
                    for (CommandArgument arg : argumentContains){
                        if (!arg.isAccess()){
                            isAccess = false;
                        }
                    }
                    if (isAccess) {
                        Boolean isPattern = false;
                        String patternArgument = null;
                        for (String arg : existCommandArgument){
                            for (ETabPatterns pattern : ETabPatterns.values()){
                                if (arg.equalsIgnoreCase(pattern.getArgumentName())){
                                    isPattern = true;
                                    patternArgument = pattern.getArgumentName();
                                }
                            }
                        }
                        Bukkit.getServer().broadcastMessage(existCommand.toString());
                        if (isPattern) {
                            accessArguments.addAll(ETabPatterns.getPaternFromArgument(patternArgument).getTabPattern().patternBuilder(this.sr, this.cmd, this.executeString, this.executeArguments));
                        } else {
                            accessArguments.addAll(existCommand.getCommand().getArguments().get(cursorPosition - 1));
                        }
                    }
                }*/
            }
            Bukkit.getServer().broadcastMessage("---");
        }
    }


    public class CommandArgument{
        private ETabPatterns pattern = null;
        private boolean isPattern = false;
        private boolean access;

        public CommandArgument(boolean access, ETabPatterns pattern){
            this.access = access;

            if (pattern != null){
                this.pattern = pattern;
                isPattern = true;
            }
        }

        public ETabPatterns getPattern() {
            return pattern;
        }

        public boolean isPattern() {
            return isPattern;
        }

        public boolean isAccess() {
            return access;
        }
    }
}
