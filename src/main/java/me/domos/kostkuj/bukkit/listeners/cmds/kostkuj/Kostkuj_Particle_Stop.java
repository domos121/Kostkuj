package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.player.KPlayer;
import org.bukkit.command.CommandSender;

public class Kostkuj_Particle_Stop {
    private SendSystem ss = new SendSystem();

    public Kostkuj_Particle_Stop() {
    }

    public void Stop(String[] args, CommandSender sr) {
        this.ss.info(sr, "Particle jsou vypnuty.");
        KPlayer.getPlayer(sr).setParticleStart(false);
    }
}
