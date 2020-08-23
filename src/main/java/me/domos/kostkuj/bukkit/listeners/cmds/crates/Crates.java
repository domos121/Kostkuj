package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crates implements CommandExecutor, TabCompleter {

    private SendSystem ss = new SendSystem();
    private CratesReload cmdCrates_reload = new CratesReload();
    private CratesCreateKey cmdCrates_createKey = new CratesCreateKey();
    private ConfigCrates cCrates = ConfigCrates.getInstance();
    private CratesEditLocation editLocation = new CratesEditLocation();
    private CratesNewCrate cratesNewCrate = new CratesNewCrate();
    private CratesEditColor cratesEditColor = new CratesEditColor();
    private CratesEditName cratesEditName = new CratesEditName();
    private CratesRemoveItem cratesRemoveItem = new CratesRemoveItem();
    private CratesAddItem cratesAddItem = new CratesAddItem();

    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if (!sr.hasPermission(ECmd.CRATE.getPerm())){
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0){
            ss.use(sr, ECmd.CRATE_HELP.getCmd());
            return true;
        }

        if (args[0].equalsIgnoreCase(ECmd.CRATE_HELP.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_HELP.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            new PlayerManager().helpMenu(sr, "crate.");
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_RELOAD.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_RELOAD.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdCrates_reload.reload(sr);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_CREATEKEY.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_CREATEKEY.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdCrates_createKey.createKey(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_EDITLOCATION.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_EDITLOCATION.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            editLocation.editLocation(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_NEWCRATE.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_NEWCRATE.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cratesNewCrate.cratesNewCrate(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_EDITCOLOR.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_EDITCOLOR.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cratesEditColor.cratesEditColor(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_EDITNAME.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_EDITNAME.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cratesEditName.cratesEditColor(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_REMOVEITEM.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_REMOVEITEM.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cratesRemoveItem.cratesRemoveItem(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.CRATE_ADDITEM.getLastarg())){
            if (!sr.hasPermission(ECmd.CRATE_ADDITEM.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cratesAddItem.crateAddItem(sr, args);
        }
        return false;
    }

    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] args) {
        List<String> tab = new ArrayList<String>();
        List<String> cmd = new ArrayList<String>();
        if (args.length == 1){
            if (sr.hasPermission(ECmd.CRATE_HELP.getPerm())){
                cmd.add(ECmd.CRATE_HELP.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_RELOAD.getPerm())){
                cmd.add(ECmd.CRATE_RELOAD.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_CREATEKEY.getPerm())){
                cmd.add(ECmd.CRATE_CREATEKEY.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_EDITLOCATION.getPerm())){
                cmd.add(ECmd.CRATE_EDITLOCATION.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_NEWCRATE.getPerm())){
                cmd.add(ECmd.CRATE_NEWCRATE.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_EDITCOLOR.getPerm())){
                cmd.add(ECmd.CRATE_EDITCOLOR.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_EDITNAME.getPerm())){
                cmd.add(ECmd.CRATE_EDITNAME.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_REMOVEITEM.getPerm())){
                cmd.add(ECmd.CRATE_REMOVEITEM.getLastarg());
            }
            if (sr.hasPermission(ECmd.CRATE_ADDITEM.getPerm())){
                cmd.add(ECmd.CRATE_ADDITEM.getLastarg());
            }
            if (args[0] == null){
                tab = cmd;
                return tab;
            }
            for (int i = 0; i < cmd.size(); i++){
                if (cmd.get(i).contains(args[0])){
                    tab.add(cmd.get(i));
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase(ECmd.CRATE_CREATEKEY.getLastarg()) && sr.hasPermission(ECmd.CRATE_CREATEKEY.getPerm())) {
                return this.returnCestsName(args);
            } else if (args[0].equalsIgnoreCase(ECmd.CRATE_EDITLOCATION.getLastarg()) && sr.hasPermission(ECmd.CRATE_EDITLOCATION.getPerm())){
                return this.returnCestsName(args);
            } else if (args[0].equalsIgnoreCase(ECmd.CRATE_EDITCOLOR.getLastarg()) && sr.hasPermission(ECmd.CRATE_EDITCOLOR.getPerm())){
                return this.returnCestsName(args);
            } else if (args[0].equalsIgnoreCase(ECmd.CRATE_EDITNAME.getLastarg()) && sr.hasPermission(ECmd.CRATE_EDITNAME.getPerm())){
                return this.returnCestsName(args);
            } else if (args[0].equalsIgnoreCase(ECmd.CRATE_REMOVEITEM.getLastarg()) && sr.hasPermission(ECmd.CRATE_REMOVEITEM.getPerm())){
                return this.returnCestsName(args);
            } else if (args[0].equalsIgnoreCase(ECmd.CRATE_ADDITEM.getLastarg()) && sr.hasPermission(ECmd.CRATE_ADDITEM.getPerm())){
                return this.returnCestsName(args);
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase(ECmd.CRATE_ADDITEM.getLastarg()) && cCrates.getName().toString().contains(args[1])){
                return this.createItem(args);
            }
        } else if (args.length == 4){
            if (args[0].equalsIgnoreCase(ECmd.CRATE_ADDITEM.getLastarg()) && sr.hasPermission(ECmd.CRATE_ADDITEMPUT.getPerm())){
                tab.clear();
                tab.add(ECmd.CRATE_ADDITEMPUT.getLastarg());
                return tab;
            }
        }
        return tab;
    }

    private List<String> returnCestsName(String[] args){
        List<String> tab = new ArrayList<String>();
        List<String> cmd = new ArrayList<String>();
        for (int i = 0; cCrates.getName().size() > i; i++){
            cmd.add(cCrates.getName().get(i));
        }
        if (args[1] == null) {
            tab = cmd;
            return tab;
        }
        for (int i = 0; i < cmd.size(); i++) {
            if (cmd.get(i).contains(args[1])) {
                tab.add(cmd.get(i));
            }
        }
        return tab;
    }

    private List<String> createItem(String[] args){
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

    /*private List<String> returnItemName(String[] args){
        List<String> tab = new ArrayList<String>();
        List<String> cmd = new ArrayList<String>();
        for(Material mat : Arrays.asList(Material.values())){
            cmd.add(mat.name());
        }
        if (args[2] == null) {
            tab = cmd;
            return tab;
        }
        for (int i = 0; i < cmd.size(); i++) {
            if (cmd.get(i).contains(args[2])) {
                tab.add(cmd.get(i));
            }
        }
        return tab;
    }*/

}
