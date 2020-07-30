package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CratesEditColor {

    SendSystem ss = new SendSystem();

    public boolean cratesEditColor(CommandSender sr, String[] args){
        HashMap<String, String> mapkeys = ConfigCrates.getInstance().getMapNameInvert();
        if (args.length <= 1){
            ss.use(sr, "/crate editcolor <CRATE> <COLOR>(0-9,a-f,r)");
            return true;
        }

        if (args.length <= 2){
            ss.info(sr, "Vyber barvu kterou chceš použít!");
            return true;
        }

        String name = args[1].toUpperCase();
        if (!mapkeys.containsKey(name)){
            ss.info(sr, "Tento kolotoč neexistuje!");
            return true;
        }

        try{
            if (ChatColor.getByChar(args[2]).isColor()){
                //ss.info(sr, ChatColor.getByChar(args[2]) + "Barva existuje");
            } else {
                ss.info(sr, "Tato barva neexistuje");
                return true;
            }
        } catch (NullPointerException e){
            ss.info(sr, "Tato barva neexistuje");
            return true;
        }

        String[] key = ConfigCrates.getInstance().getMapKeys().get(mapkeys.get(name)).split(",");

        String path = "chests." + mapkeys.get(name) + ".key";

        ConfigManager.CRATES.getConfig().set(path, key[0] + ",§" + ChatColor.getByChar(args[2]).getChar() + key[1].substring(2));

        ConfigManager.CRATES.saveConfig();
        ConfigManager.CRATES.reloadConfig();

        ConfigCrates.getInstance().setCfg();
        ss.info(sr, "Barva kolotoče " + ChatColor.getByChar(args[2]) + name + " §7byla změněna!");
        return true;
    }
}
