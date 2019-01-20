package me.domos.Kostkuj.server.particle;

import me.domos.Kostkuj.Main;
import me.domos.Kostkuj.enums.EParticleShapes;
import me.domos.Kostkuj.server.player.KPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;

public class ParticleShapeRing implements IParticleShape {
    public void execute(Player player){
        new BukkitRunnable(){
            double t = 0;
            double r = 0.75;
            @Override
            public void run() {
                try {
                    KPlayer p = KPlayer.getPlayer(player);
                    Location loc = player.getLocation();
                    t = t + 0.2;
                    double x = r * sin(t);
                    double y = 2;
                    double z = r * cos(t);
                    loc.add(x, y, z);
                    p.spawnParticle(loc);
                    loc.subtract(x, y, z);
                    if (t > Math.PI * 4) {
                        t = 0;
                    }
                    if (!p.isParticleStart() || !player.isOnline()){
                        this.cancel();
                    }
                    if (p.getStylparticle() != EParticleShapes.RING) {
                        this.cancel();
                    }
                } catch (NullPointerException e){
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }
}
