package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
public class CratesEditLocation {

    SendSystem ss = new SendSystem();

    public boolean editLocation(CommandSender sr, String[] args){
        HashMap<String, String> mapkeys = ConfigCrates.getInstance().getMapNameInvert();
        if (args.length <= 1){
            ss.info(sr, "Vyber existující kolotoč!");
            return true;
        }
        if (!mapkeys.containsKey(args[1])){
            ss.info(sr, "Tento kolotoč neexistuje!");
            return true;
        }

        FileConfiguration config = ConfigManager.CRATES.getConfig();
        String oldpath = "chests." + mapkeys.get(args[1]);
        Location location = Bukkit.getPlayer(sr.getName()).getLocation();
        String newpath = "chests." + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + "," + location.getWorld().getName();

        String name = config.getString(oldpath + ".name");
        String key = config.getString(oldpath + ".key");

        List<String> l = (List<String>)config.getList(oldpath + ".items");

        config.set(newpath + ".name", name);
        config.set(newpath + ".key", key);
        config.set(newpath + ".items", l.toArray());

        config.set(oldpath, null);

        ConfigManager.CRATES.saveConfig();
        ConfigManager.CRATES.reloadConfig();

        ConfigCrates.getInstance().setCfg();
        ss.info(sr, "Kolotoč byl úspěšně přemístěn!");
        return true;
        }
}
