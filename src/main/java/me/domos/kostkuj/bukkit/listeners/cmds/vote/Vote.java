package me.domos.kostkuj.bukkit.listeners.cmds.vote;

import com.google.gson.JsonObject;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.PlayerManager;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.vote.MysqlListener;
import me.domos.kostkuj.general.connect.urlReader.UrlRead;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Vote implements CommandExecutor, TabCompleter {
    
    private SendSystem ss = new SendSystem();
    private VoteTest cmdVote_test = new VoteTest();
    private VoteSklad cmdVote_sklad = new VoteSklad();
    private VoteScoreBoard voteScoreBoard = new VoteScoreBoard();
    private VoteTopVotersBoard voteTopVotersBoard = new VoteTopVotersBoard();


    public boolean onCommand(CommandSender sr, Command cmd, String s, String[] args) {
        if(!sr.hasPermission(ECmd.VOTE.getPerm())){
            ss.noPerm(sr);
            return true;
        }

        if (args.length == 0){
            UrlRead urlRead = new UrlRead();
            String keyMonth = Time.getTimeDay(0).toString().substring(0,7).replace("-", "/");
            JsonObject activeVotes = urlRead.urlRead("https://czech-craft.eu/api/server/kostkuj/player/" + sr.getName().trim() + "/");
            JsonObject monthVotes = urlRead.urlRead("https://czech-craft.eu/api/server/kostkuj/player/" + sr.getName().trim() + "/" + keyMonth + "/");
            int votes = activeVotes.get("vote_count").getAsInt();
            String nextVote = activeVotes.get("next_vote").getAsString();
            int monthVoteCount = monthVotes.get("vote_count").getAsInt();

            String msg = "§6========[§aVote Kostkuj.cz§6]========\n"
                    + "§7Celkem hlasů: §a" + new MysqlListener().getVotes(sr.getName().trim()) + "§7.\n"
                    + "§7Nyní máš: §a" + votes + " §7aktivních hlasů.\n"
                    + "§7Hlasů za tento měsíc: §a " + monthVoteCount + "§7.\n"
                    + "§7Znovu můžeš hlasovat: §a" + nextVote + "§7.\n";
            sr.sendMessage(msg);
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
        } else if (args[0].equalsIgnoreCase(ECmd.VOTE_SCOREBOARD.getLastarg())){
            if(!sr.hasPermission(ECmd.VOTE_SCOREBOARD.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            voteScoreBoard.voteScoreBoard(sr,args);
        } else if (args[0].equalsIgnoreCase(ECmd.VOTE_TOPVOTERSBOARD.getLastarg())){
            if(!sr.hasPermission(ECmd.VOTE_TOPVOTERSBOARD.getPerm())){
                ss.noPerm(sr);
                return true;
            }
            voteTopVotersBoard.voteTopVotersBoard(sr, args);
        }
        
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sr, Command command, String s, String[] args) {
        List<String> tab = new ArrayList<String>();
        List<String> cmd = new ArrayList<String>();
        if (args.length == 1){
            if (sr.hasPermission(ECmd.VOTE_SKLAD.getPerm())){
                cmd.add(ECmd.VOTE_SKLAD.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOTE_HELP.getPerm())){
                cmd.add(ECmd.VOTE_HELP.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOTE_SCOREBOARD.getPerm())){
                cmd.add(ECmd.VOTE_SCOREBOARD.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOTE_TOPVOTERSBOARD.getPerm())){
                cmd.add(ECmd.VOTE_TOPVOTERSBOARD.getLastarg());
            }
            if (sr.hasPermission(ECmd.VOTE_TEST.getPerm())){
                cmd.add(ECmd.VOTE_TEST.getLastarg());
            }
            if (args[0] == null){
                tab = cmd;
                return tab;
            }
            for (int i = 0; i < cmd.size(); i++){
                if (cmd.get(i).contains(args[0])){
                    tab.add(cmd.get(i));
                }
            }
        }
        return tab;
    }
}
