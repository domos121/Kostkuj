package me.domos.kostkuj.bukkit.listeners.cmds.votemute;

import me.domos.kostkuj.general.connect.mysql.MVoteMute;
import org.bukkit.command.CommandSender;

public class VoteMute_Get {


    MVoteMute mvm = new MVoteMute();

    public void get(CommandSender sr, String[] args){

        mvm.getVoteMute(sr);

    }
}