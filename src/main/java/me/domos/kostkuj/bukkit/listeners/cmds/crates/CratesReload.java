package me.domos.kostkuj.bukkit.listeners.cmds.crates;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigCrates;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import me.domos.kostkuj.general.fileManager.ConfigVote;
import org.bukkit.command.CommandSender;

public class CratesReload {


    private SendSystem ss = new SendSystem();
    private ConfigCrates cCrates = ConfigCrates.getInstance();
    private ConfigVote cvote = ConfigVote.getInstance();

    public void reload(CommandSender sr){
        ConfigManager.CRATES.reloadConfig();
        ConfigManager.CONFIG.reloadConfig();
        cCrates.setCfg();
        cvote.setCfg();
        ss.info(sr, "§7Crates reloaded.");
    }

}
