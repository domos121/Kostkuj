package me.domos.kostkuj.bukkit.listeners.cmds.vote;

import me.domos.kostkuj.bukkit.listeners.ECmd;
import me.domos.kostkuj.bukkit.player.inventory.GetIn;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class VoteSklad {

    private GetIn getIn = new GetIn();

    public void getSklad(CommandSender sr, String[] args){

        String user = sr.getName();

        if (args.length > 1 && sr.hasPermission(ECmd.VOTE_SKLAD_OTHER.getPerm())){
            user = args[1];
        }

        getIn.inv(Bukkit.getPlayer(sr.getName()), user);
    }

}
