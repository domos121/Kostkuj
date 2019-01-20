package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.server.particle.ParticleCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_Particle_Start {

    ParticleCore core =  new ParticleCore();

    public void Start(String[] args, CommandSender sr ){
        Player p = Bukkit.getPlayer(sr.getName());

        core.ParticleCore(p);
    }

}
