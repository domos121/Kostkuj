package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CratesNewCrate {

    private SendSystem ss = new SendSystem();
    public boolean cratesNewCrate(CommandSender sr, String[] args){
        if (args.length <= 1){
            ss.info(sr, "Napiš název nového kolotoče!");
            return true;
        }
        String name = args[1].toUpperCase();

        HashMap<String, String> mapkeys = ConfigCrates.getInstance().getMapNameInvert();

        if (mapkeys.containsKey(name)){
            ss.info(sr, "Kolotoč " + name + " už existuje!");
            return true;
        }

        HashMap<String, String> mapchest = ConfigCrates.getInstance().getMapName();

        Location location = Bukkit.getPlayer(sr.getName()).getLocation();
        String baselocation = location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + "," + location.getWorld().getName();
        String newpath = "chests." + baselocation;

        if (mapchest.containsKey(baselocation)){
            ss.info(sr, "Na tomto místě už kolotoč existuje");
            return true;
        }


        FileConfiguration config = ConfigManager.CRATES.getConfig();


        List<String> l = new ArrayList<String>();
        l.add("DIAMOND,3");

        String editname = args[1].toLowerCase().substring(0, 1).toUpperCase() + args[1].toLowerCase().substring(1);

        config.set(newpath + ".name", name);
        config.set(newpath + ".key", "KEY_" + name + ",§f" + editname + "§aKey");
        config.set(newpath + ".items", l.toArray());

        ConfigManager.CRATES.saveConfig();
        ConfigManager.CRATES.reloadConfig();

        ConfigCrates.getInstance().setCfg();
        ss.info(sr, "Kolotoč byl úspěšně vytvořen!");

        return true;
    }
}
