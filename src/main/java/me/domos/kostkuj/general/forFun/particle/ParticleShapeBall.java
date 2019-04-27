package me.domos.kostkuj.general.forFun.particle;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.player.KPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ParticleShapeBall implements IParticleShape {
    public void execute(Player player) {
        new BukkitRunnable() {
            double phi = 0;

            @Override
            public void run() {
                try {
                    KPlayer p = KPlayer.getPlayer(player);
                    Location loc = player.getLocation();
                    phi += Math.PI / 15;
                    for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 50) {
                        double r = 1.75;
                        double x = r * cos(theta) * sin(phi);
                        double y = r * cos(phi) + r;
                        double z = r * sin(theta) * sin(phi);

                        loc.add(x, y, z);
                        p.spawnParticle(loc);
                        loc.subtract(x, y, z);


                    }

                    if (!p.isParticleStart() || !player.isOnline()){
                        this.cancel();
                    }

                    if (p.getStylparticle() != EParticleShapes.BALL) {
                        this.cancel();
                    }

                } catch (NullPointerException e){
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.plugin, 0, 3);
    }
}
