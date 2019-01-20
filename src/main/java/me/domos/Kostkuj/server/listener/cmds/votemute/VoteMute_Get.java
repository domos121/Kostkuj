package me.domos.Kostkuj.server.listener.cmds.votemute;

import me.domos.Kostkuj.connect.MySQL.MVoteMute;
import org.bukkit.command.CommandSender;

public class VoteMute_Get {


    MVoteMute mvm = new MVoteMute();

    public void get(CommandSender sr, String[] args){

        mvm.getVoteMute(sr);

    }
}