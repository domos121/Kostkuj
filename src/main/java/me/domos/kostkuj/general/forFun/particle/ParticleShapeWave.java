package me.domos.kostkuj.general.forFun.particle;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.player.KPlayer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ParticleShapeWave implements IParticleShape {
    public void execute(Player player) {
        new BukkitRunnable() {
            double t = 0;
            KPlayer kp = KPlayer.getPlayer(player);
            @Override
            public void run() {
                Location loc = player.getLocation();
                t = t + 0.1 * Math.PI;

                for (double theta = 0; theta <= 2 * Math.PI; theta = theta + Math.PI / 32) {
                    double x = t * cos(theta);
                    double y = Math.exp(-0.1 * t) * sin(t) + 1.5;
                    double z = t * sin(theta);
                    loc.add(x, y, z);
                    loc.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 0, 0, 0, 0, 1);
                    loc.subtract(x, y, z);

                    theta = theta + Math.PI / 64;

                    x = t * cos(theta);
                    y = Math.exp(-0.1 * t) * sin(t) + 1.5;
                    z = t * sin(theta);
                    loc.add(x, y, z);
                    loc.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 0, 0, 0, 0, 1);
                    loc.subtract(x, y, z);
                }
                if (t > 30) {
                    kp.setParticleStart(false);
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }
}
