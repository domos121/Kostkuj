package me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns;

import me.domos.kostkuj.general.fileManager.ConfigCrates;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilderPattern implements ITabPattern {
    @Override
    public List<String> patternBuilder(CommandSender sr, Command command, String s, String[] args) {
        List<String> tab = new ArrayList<String>();
        List<String> cmd = new ArrayList<String>();
        cmd.add("CRAFTCOINS_");
        cmd.add("KEY_");
        for (Material mat : Arrays.asList(Material.values())) {
            cmd.add(mat.name());
        }
        if (args[2] == null) {
            tab = cmd;
            return tab;
        }
        String[] argSize = args[2].split(",");
        String[] argSizeChar = args[2].split("");
        int nuberOfCarka = 0;
        String argSizeString = "";
        List<String> list = Arrays.asList(argSize);
        for (int i = 0; list.size() - 1 > i; i++) {
            argSizeString = argSizeString + "," + list.get(i);
        }
        for (String chars : Arrays.asList(argSizeChar)) {
            if (chars.equalsIgnoreCase(",")) {
                nuberOfCarka++;
            }
        }
        if (!args[2].contains(",")) {
            for (int i = 0; i < cmd.size(); i++) {
                if (cmd.get(i).contains(args[2])) {
                    tab.add(cmd.get(i));
                }
            }
        } else if (argSize[0].equalsIgnoreCase("KEY_")){
            tab.clear();
            cmd.clear();
            if (nuberOfCarka == 1){
                tab.clear();
                cmd.clear();
                for (int i = 64; i >= 1; i--) {
                    cmd.add(argSize[0] + "," + i);
                }
                for (int i = 0; i < cmd.size(); i++) {
                    if (cmd.get(i).contains(args[2])) {
                        tab.add(cmd.get(i));
                    }
                }
            } else if (nuberOfCarka > 1){
                tab.clear();
                cmd.clear();
                if (argSize.length == 2) {
                    for (String keys : ConfigCrates.getInstance().getKeys()) {
                        cmd.add(args[2] + keys.split(",")[1].replace("§", "&"));
                    }
                } else {
                    for (String keys : ConfigCrates.getInstance().getKeys()) {
                        cmd.add(argSizeString.substring(1, argSizeString.length()) + "," + keys.split(",")[1].replace("§", "&"));
                    }
                }
                for (int i = 0; i < cmd.size(); i++) {
                    if (cmd.get(i).contains(args[2])) {
                        tab.add(cmd.get(i));
                    }
                }
                return tab;
            }
        } else if (argSize[0].equalsIgnoreCase("CRAFTCOINS_")){

        }else if (argSize.length > 1 && args[2].contains(",") && nuberOfCarka > 1) {

            if (args[2].substring(args[2].length() - 1).equalsIgnoreCase(",")) {
                cmd.clear();
                for (Enchantment ench : Arrays.asList(Enchantment.values())) {
                    cmd.add(args[2] + ench.getName());
                }
                tab = cmd;
                return tab;
            } else if (args[2].substring(args[2].length() - 1).equalsIgnoreCase("|")) {
                tab.clear();
                String ench = argSize[argSize.length - 1].replace("|", "");
                int maxlevel = 1;
                for (Enchantment enc : Arrays.asList(Enchantment.values())) {
                    if (enc.getName().equalsIgnoreCase(ench)) {
                        maxlevel = enc.getMaxLevel();
                    }
                }
                for (int i = maxlevel; i >= 1; i--) {
                    tab.add(args[2] + i);
                }
                return tab;
            } else {
                cmd.clear();
                tab.clear();

                for (Enchantment ench : Arrays.asList(Enchantment.values())) {
                    cmd.add(argSizeString.substring(1, argSizeString.length()) + "," + ench.getName());
                }
                for (int i = 0; i < cmd.size(); i++) {
                    if (cmd.get(i).contains(args[2])) {
                        tab.add(cmd.get(i));
                    }
                }
            }

        } else {
            tab.clear();
            cmd.clear();
            for (int i = 64; i >= 1; i--) {
                cmd.add(argSize[0] + "," + i);
            }
            for (int i = 0; i < cmd.size(); i++) {
                if (cmd.get(i).contains(args[2])) {
                    tab.add(cmd.get(i));
                }
            }
        }
        return tab;
    }
}
