package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.general.forFun.particle.ParticleCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_Particle_Start {

    private ParticleCore core =  new ParticleCore();

    public Kostkuj_Particle_Start() {
    }

    public void Start(String[] args, CommandSender sr) {
        Player p = (Player)sr;
        this.core.ParticleCore(p);
    }

}
