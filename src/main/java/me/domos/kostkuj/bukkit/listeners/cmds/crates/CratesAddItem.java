package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.items.AddItem;
import me.domos.kostkuj.bukkit.items.ItBuilder;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CratesAddItem {

    private SendSystem ss = new SendSystem();
    private ItBuilder ib = ItBuilder.getInstance();
    private AddItem ai = new AddItem();

    public boolean crateAddItem(CommandSender sr, String[] args){
        HashMap<String, String> mapkeys = ConfigCrates.getInstance().getMapNameInvert();
        if (args.length <= 1){
            ss.info(sr, "Vyber existující kolotoč!");
            return true;
        }
        String name = args[1].toUpperCase();
        if (!mapkeys.containsKey(name)){
            ss.info(sr, "Tento kolotoč neexistuje!");
            return true;
        }

        String koloItem = args[2];
        String[] koloItemSplit = koloItem.split("%");
        String[] itemSplit = koloItemSplit[0].split(",");
        if (koloItemSplit.length != 2){
            ss.info(sr, "V itemu ti chybí % možnost výhry!");
            return true;
        }
        if (!StringUtils.isNumeric(koloItemSplit[1])){
            this.ss.info(sr, "Procentuální možnost musí být číslo.");
            return true;
        }
        if (Integer.parseInt(koloItemSplit[1]) > 250){
            this.ss.info(sr, "Maximimální možnost je 250.");
            return true;
        }

        if (itemSplit.length > 2 && !itemSplit[0].equalsIgnoreCase("ENCHANTED_BOOK") && !itemSplit[0].equalsIgnoreCase("KEY_") && !itemSplit[0].equalsIgnoreCase("CRAFTCOINS_")){
            koloItem = "EITEM_," + args[2];
        } else if (itemSplit[0].equalsIgnoreCase("CRAFTCOINS_")){
            if (!(itemSplit.length >= 1)){
                ss.info(sr,"Není zadána hodnota craftcoinu");
                return true;
            }
        } else if (itemSplit[0].equalsIgnoreCase("KEY_")){
            if (!(itemSplit.length >= 2)){
                ss.info(sr,"Není vybrán počet klíčů a který klíč.");
                return true;
            } else {
                if (!StringUtils.isNumeric(itemSplit[1])){
                    ss.info(sr, "Není zadaná správna hodnota poččtu klíčů!");
                    return true;
                }
                String key = itemSplit[2];

                boolean isExistKey = false;
                for (String keys : ConfigCrates.getInstance().getKeys()){
                    if (keys.contains(key.replace("&","§"))){
                        isExistKey = true;
                        break;
                    }
                }
                if (!isExistKey){
                    ss.info(sr, "Tento Klíč neexistuje!");
                    return true;
                }
                koloItem = koloItem.replace("&","§");
            }
        }

        List<String> checkItem = new ArrayList<>();
        checkItem.add(koloItem);

        ItemStack item = ib.koloItems(checkItem).getListItems().get(0);
        try {
            ai.add(item, Bukkit.getPlayer(sr.getName()));
        } catch (NumberFormatException e){
            ss.info(sr, "Nevhodný formát, prosím zkontroluj správnost.");
            return false;
        }

        try {
            if (args[3] != null) {
                if (args[3].equalsIgnoreCase("put")) {
                    List<String> list = ConfigCrates.getInstance().getMapItems().get(mapkeys.get(name));
                    list.add(koloItem);
                    ConfigManager.CRATES.getConfig().set("chests." + mapkeys.get(name) + ".items", list.toArray());

                    ConfigManager.CRATES.saveConfig();
                    ConfigManager.CRATES.reloadConfig();

                    ConfigCrates.getInstance().setCfg();
                    ss.info(sr, "Item byl přidán do kolotoče.");
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        return true;
    }
}
