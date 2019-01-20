package by.domos.Kostkuj.server.particle;

import by.domos.Kostkuj.Main;
import by.domos.Kostkuj.enums.EParticleShapes;
import by.domos.Kostkuj.server.player.KPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class ParticleShapeTrail implements IParticleShape {
    public void execute(Player p) {
        new BukkitRunnable(){
            @Override
            public void run() {
                try {
                    KPlayer kp = KPlayer.getPlayer(p);
                    Location loc = p.getLocation();
                    double x = 0;
                    double y = 0;
                    double z = 0;
                    loc.add(x, y, z);
                    kp.spawnParticle(loc);
                    loc.subtract(x, y, z);
                    if (kp.getStylparticle() != EParticleShapes.TRAIL) {
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
