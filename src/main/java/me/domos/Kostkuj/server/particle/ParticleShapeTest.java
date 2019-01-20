package me.domos.Kostkuj.server.particle;

import me.domos.Kostkuj.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class ParticleShapeTest implements IParticleShape {
    public void execute(Player p){
        new BukkitRunnable(){
            int t = 0;

            public void run() {
                this.cancel();
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }

}
