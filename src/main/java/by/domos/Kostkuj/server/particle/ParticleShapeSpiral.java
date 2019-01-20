package by.domos.Kostkuj.server.particle;

import by.domos.Kostkuj.Main;
import by.domos.Kostkuj.enums.EParticleShapes;
import by.domos.Kostkuj.server.player.KPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;

public class ParticleShapeSpiral implements IParticleShape {

    public void execute(Player player){
        new BukkitRunnable(){
            double t = 0;
            double r = 1;
            @Override
            public void run() {
                try {
                    KPlayer p = KPlayer.getPlayer(player);
                    if (p.getStylparticle() != EParticleShapes.SPIRAL) {
                        this.cancel();
                    }
                    Location loc = player.getLocation();
                    t = t + Math.PI / 20;
                    double x = r * cos(t);
                    double y = t / 4;
                    double z = r * sin(t);
                    loc.add(x, y, z);
                    p.spawnParticle(loc);
                    loc.subtract(x, y, z);
                    if (t > Math.PI * 4) {
                        t = 0;
                    }
                    if (!p.isParticleStart() || !player.isOnline()){
                        this.cancel();
                    }
                } catch (NullPointerException e){
                    this.cancel();
                }

            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }
}
