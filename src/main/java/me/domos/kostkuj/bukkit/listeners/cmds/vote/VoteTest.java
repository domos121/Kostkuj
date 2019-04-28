package me.domos.kostkuj.bukkit.listeners.cmds.vote;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.models.voteModel.Vote;
import org.bukkit.command.CommandSender;

public class VoteTest {

    private Vote v = Vote.getInstance();
    private SendSystem ss = new SendSystem();

    public void test(CommandSender sr, String[] args) {

        String user = sr.getName();

        if (args.length > 1){
            user = args[1];
        }

        ss.info(sr, "Hlasovals pro " + user + "");

        v.VoteIsOnline(user);
    }

}
