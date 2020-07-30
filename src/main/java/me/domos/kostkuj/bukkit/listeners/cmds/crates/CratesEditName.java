package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CratesEditName {

    private SendSystem ss = new SendSystem();

    public boolean cratesEditColor(CommandSender sr, String[] args){
        HashMap<String, String> mapkeys = ConfigCrates.getInstance().getMapNameInvert();
        if (args.length <= 1){
            ss.use(sr, "/crate editname <CRATE> <NEWCRATE>");
            return true;
        }

        if (args.length <= 2){
            ss.use(sr, "/crate editname <CRATE> <NEWCRATE>");
            return true;
        }

        if (!mapkeys.containsKey(args[1].toUpperCase())){
            ss.info(sr, "Tento kolotoč neexistuje!");
            return true;
        }

        if (mapkeys.containsKey(args[2].toUpperCase())){
            ss.info(sr, "Tento kolotoč už existuje!");
            return true;
        }

        String pathname = "chests." + mapkeys.get(args[1].toUpperCase()) + ".name";
        String pathkey = "chests." + mapkeys.get(args[1].toUpperCase()) + ".key";

        String[] oldkey = ConfigManager.CRATES.getConfig().getString(pathkey).split(",");

        String editnameFirst = args[1].toLowerCase().substring(0, 1).toUpperCase() + args[1].toLowerCase().substring(1);
        String editnameSecond = args[2].toLowerCase().substring(0, 1).toUpperCase() + args[2].toLowerCase().substring(1);

        String newkey = oldkey[0].replace(oldkey[0].replace("KEY_", ""), args[2].toUpperCase()) + "," + oldkey[1].replace(editnameFirst, editnameSecond);

        ConfigManager.CRATES.getConfig().set(pathname, args[2].toUpperCase());
        ConfigManager.CRATES.getConfig().set(pathkey, newkey);

        ConfigManager.CRATES.saveConfig();
        ConfigManager.CRATES.reloadConfig();

        ConfigCrates.getInstance().setCfg();
        ss.info(sr, "Kolotoč byl úspěšně přejmenován!");

        return true;
    }
}
