package me.domos.kostkuj.bukkit.listeners.cmds.vote;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.fileManager.ConfigManager;
import me.domos.kostkuj.models.voteModel.VoteScoreBoardSettings;
import org.bukkit.command.CommandSender;

public class VoteScoreBoard {

    SendSystem ss = new SendSystem();

    public boolean voteScoreBoard(CommandSender sr, String[] args) {

        ConfigManager config = ConfigManager.CONFIG;

        if (args.length < 2){
            ss.info(sr, "Musíš zadat nějaké hodnoty.");
            return true;
        }

        if (args[1].equalsIgnoreCase("on")){
            config.getConfig().set("ScoreBoard.allow", true);
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Scoreboard on.");
        } else if (args[1].equalsIgnoreCase("off")){
            config.getConfig().set("ScoreBoard.allow", false);
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Scoreboard off.");
        } else if (args[1].equalsIgnoreCase("create")){
            config.getConfig().set("ScoreBoard.allow", false);
            config.getConfig().set("ScoreBoard.position", "world,75514,66,184");
            config.getConfig().set("ScoreBoard.BlockFace", "west");
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Pozice vytvorena.");
        } else if (args[1].equalsIgnoreCase("setposition")){
            config.getConfig().set("ScoreBoard.position", args[2]);
            config.saveConfig();
            config.reloadConfig();
            new VoteScoreBoardSettings();
            ss.info(sr, "Pozice zmenena.");
        } else if (args[1].equalsIgnoreCase("blockface")){
            config.getConfig().set("ScoreBoard.BlockFace", args[2]);
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
