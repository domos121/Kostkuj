package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.server.player.KPlayer;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;

public class Kostkuj_Particle_Stop {

    SendSystem ss = new SendSystem();

    public void Stop(String[] args, CommandSender sr ){

        ss.info(sr, "Particle stop");
        KPlayer.getPlayer(sr).setParticleStart(false);

    }
}
