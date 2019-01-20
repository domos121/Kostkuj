package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.player.KPlayer;
import org.bukkit.command.CommandSender;

public class Kostkuj_Particle_Stop {

    SendSystem ss = new SendSystem();

    public void Stop(String[] args, CommandSender sr ){

        ss.info(sr, "Particle stop");
        KPlayer.getPlayer(sr).setParticleStart(false);

    }
}
