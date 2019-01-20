package me.domos.Kostkuj.server.particle;

import me.domos.Kostkuj.Main;
import me.domos.Kostkuj.enums.EParticleShapes;
import me.domos.Kostkuj.server.player.KPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ParticleShapeSnow implements IParticleShape {
    public void execute(Player p){
        new BukkitRunnable(){
            int t = 0;
            public void run() {
                try {
                    Random r = new Random();
                    t++;
                    KPlayer kp = KPlayer.getPlayer(p);
                    Location loc = p.getLocation();
                    double x = r.nextInt(2) * Math.sin(t);
                    double y = 2 + r.nextInt(1);
                    double z = r.nextInt(2) * Math.cos(t);
                    loc.add(x, y, z);
                    loc.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 0, 0, 0, 0, 1);
                    loc.subtract(x, y, z);
                    if (kp.getStylparticle() != EParticleShapes.SNOW) {
                        this.cancel();
                    }
                    if (!kp.isParticleStart() || !p.isOnline()){
                        this.cancel();
                    }
                } catch (NullPointerException e){
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }
}
