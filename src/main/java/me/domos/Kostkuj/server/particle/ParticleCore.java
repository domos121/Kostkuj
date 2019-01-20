package me.domos.Kostkuj.server.particle;

import me.domos.Kostkuj.enums.EParticleShapes;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.player.KPlayer;
import org.bukkit.entity.Player;

public class ParticleCore {

    SendSystem ss = new SendSystem();
    public void ParticleCore(Player player){
        KPlayer kp = KPlayer.getPlayer(player);
        if (kp.isParticleStart() == true){
            ss.info(player, "Particle už jsou spuštěné.");
            return;
        }
        for (EParticleShapes particle : EParticleShapes.values()){
            if (particle == kp.getStylparticle()){
                particle.particleShapeExecute(player);
                kp.setParticleStart(true);
                ss.info(player, "Particle " + kp.getStylparticle() + " jsou aktivovány.");
                break;
            }
        }
    }
}
