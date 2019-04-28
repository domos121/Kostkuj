package me.domos.kostkuj.bukkit.listeners.cmds.vote;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Vote implements CommandExecutor {
    
    private SendSystem ss = new SendSystem();
    private VoteTest cmdVote_test = new VoteTest();
    private VoteSklad cmdVote_sklad = new VoteSklad();


    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if(!sr.hasPermission(ECmd.VOTE.getPerm())){
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0){
            ss.use(sr, ECmd.VOTE_HELP.getCmd());
            return true;
        }
        
        if (args[0].equalsIgnoreCase(ECmd.VOTE_HELP.getLastarg())){
            if(!sr.hasPermission(ECmd.VOTE_HELP.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            new PlayerManager().helpMenu(sr, "vote.");
        } else if (args[0].equalsIgnoreCase(ECmd.VOTE_TEST.getLastarg())){
            if(!sr.hasPermission(ECmd.VOTE_TEST.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdVote_test.test(sr, args);
        } else if (args[0].equalsIgnoreCase(ECmd.VOTE_SKLAD.getLastarg())){
            if(!sr.hasPermission(ECmd.VOTE_SKLAD.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            cmdVote_sklad.getSklad(sr, args);
        }
        
        return false;
    }
}
