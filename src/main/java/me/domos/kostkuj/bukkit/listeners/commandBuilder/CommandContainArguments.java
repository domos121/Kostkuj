package me.domos.kostkuj.bukkit.listeners.commandBuilder;


import me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns.ETabPatterns;

import java.util.ArrayList;
import java.util.List;

public class CommandContainArguments {


    private boolean nextArgs = false;
    private boolean isPattern = false;
    private ETabPatterns patterns = ETabPatterns.ANYTHING;
    private List<Integer> patternPosition = new ArrayList<>();
    private List<Boolean> patternBoolean = new ArrayList<>();

    public CommandContainArguments(String[] argsExecute, String[] argsExist){

        int argsLenght = (argsExecute.length > argsExist.length) ? argsExist.length : argsExecute.length;
        boolean isCoontain = false;
        for (int p = 0; argsLenght > p; p++){
            for (int i = 0; ETabPatterns.values().length > i; i++){
                if (argsExist[p].equalsIgnoreCase(ETabPatterns.values()[i].getArgumentName())){
                    isCoontain = true;
                    break;
                }
            }
            if (isCoontain){
                isCoontain = false;
                patternBoolean.add(true);
                isPattern = true;
            } else {
                patternBoolean.add(false);
            }

        }

        List<String> list = new ArrayList<>();

        String exist = this.argsToString(argsExist);
        String execute;
        if (isPattern){
            for (int i = 0; argsLenght > i; i++){
                if (patternBoolean.get(i)){
                    // DODELAT ROZPOZNAVANI PATERNU
                    patterns = ETabPatterns.getPaternFromArgument(argsExist[i]);
                    list.add(patterns.getArgumentName());
                } else {
                    list.add(argsExecute[i]);
                }
            }
            execute = this.argsToString(list);
        } else {
            execute = this.argsToString(argsExecute);
        }
        if (isPattern){
            if (!patternBoolean.get(argsLenght-1)) {
                isPattern = false;
            } else {
            }
            if (argsExecute.length > argsLenght){
                nextArgs = false;
                return;
            }
        }


        int executeLenght = (execute.length() >= exist.length()) ? exist.length() : execute.length();

        exist = exist.substring(0, executeLenght);

        if (exist.equalsIgnoreCase(execute)){
            nextArgs = true;
        }
    }

    public String argsToString(String[] args){
        String returnArg = "";
        for (String arg : args){
            returnArg = returnArg + " " + arg;
        }
        returnArg = returnArg.substring(1);
        return returnArg;
    }

    public String argsToString(List<String> args){
        String returnArg = "";
        for (String arg : args){
            returnArg = returnArg + " " + arg;
        }
        returnArg = returnArg.substring(1);
        return returnArg;
    }

    public List<Boolean> getPatternBoolean() {
        return patternBoolean;
    }

    public boolean isNextArgs() {
        return nextArgs;
    }

    public boolean isPattern() {
        return isPattern;
    }

    public List<Integer> getPatternPosition() {
        return patternPosition;
    }

    public ETabPatterns getPatterns() {
        return patterns;
    }
}
