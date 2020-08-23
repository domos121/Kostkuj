package me.domos.kostkuj.bukkit.listeners.cmds.vote;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import me.domos.kostkuj.models.voteModel.VoteScoreBoardSettings;
import me.domos.kostkuj.models.voteModel.VoteTopPlayers;
import org.bukkit.command.CommandSender;

public class VoteTopVotersBoard {

    SendSystem ss = new SendSystem();

    public boolean voteTopVotersBoard(CommandSender sr, String[] args) {

        ConfigManager config = ConfigManager.CONFIG;

        if (args.length < 2){
            ss.info(sr, "Musíš zadat nějaké hodnoty.");
            return true;
        }

        if (args[1].equalsIgnoreCase("on")){
            config.getConfig().set("TopVotersBoard.allow", true);
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Scoreboard on.");
        } else if (args[1].equalsIgnoreCase("off")){
            config.getConfig().set("TopVotersBoard.allow", false);
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Scoreboard off.");
        } else if (args[1].equalsIgnoreCase("create")){
            config.getConfig().set("TopVotersBoard.allow", true);
            config.getConfig().set("TopVotersBoard.position", "world,75514,66,168");
            config.getConfig().set("TopVotersBoard.BlockFace", "west");
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Pozice vytvorena.");
        } else if (args[1].equalsIgnoreCase("update")){
            new VoteTopPlayers().setTopPlayers(args[2]);
            ss.info(sr, "Vyhra byla p5id2lena!");
        } else if (args[1].equalsIgnoreCase("setposition")){
            config.getConfig().set("TopVotersBoard.position", args[2]);
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Pozice zmenena.");
        } else if (args[1].equalsIgnoreCase("blockface")){
            config.getConfig().set("TopVotersBoard.BlockFace", args[2]);
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "BlockFace zmenen.");
        } else {
            ss.info(sr, "Tento příkaz neexistuje.");
        }

        return true;
    }
}
