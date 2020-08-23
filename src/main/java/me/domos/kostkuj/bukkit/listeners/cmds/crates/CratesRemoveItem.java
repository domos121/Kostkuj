package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;

public class CratesRemoveItem {

    private SendSystem ss = new SendSystem();

    public boolean cratesRemoveItem(CommandSender sr, String[] args){
        HashMap<String, String> mapkeys = ConfigCrates.getInstance().getMapNameInvert();
        if (args.length <= 1){
            ss.info(sr, "Vyber existující kolotoč!");
            return true;
        }
        String name = args[1].toUpperCase();
        if (!mapkeys.containsKey(args[1])){
            ss.info(sr, "Tento kolotoč neexistuje!");
            return true;
        }
        List<?> list = ConfigCrates.getInstance().getMapItems().get(mapkeys.get(name));
        if(args.length == 2) {
            String itemSeznam = "";
            for (int i = 0; list.size() > i; i++) {
                itemSeznam = itemSeznam + "§2[" + i + "] §7" + list.get(i).toString() + "§a, ";
            }
            ss.info(sr, itemSeznam);
        }
        if (args.length == 3){
            if (!StringUtils.isNumeric(args[2])){
                this.ss.info(sr, "Zadej vhodné číslo.");
                return true;
            }
            int i = Integer.parseInt(args[2]);
            if (!(list.size() > i)){
                this.ss.info(sr, "Zadej vhodné číslo.");
                return true;
            }
            list.remove(i);
            ConfigManager.CRATES.getConfig().set("chests." + mapkeys.get(name) + ".items", list.toArray());

            ConfigManager.CRATES.saveConfig();
            ConfigManager.CRATES.reloadConfig();

            ConfigCrates.getInstance().setCfg();
            ss.info(sr, "Item " + i + " byl odstraněn");
        }
        return true;
    }
}
