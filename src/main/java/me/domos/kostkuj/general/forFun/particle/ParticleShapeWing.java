package me.domos.kostkuj.general.forFun.particle;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.player.KPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleShapeWing implements IParticleShape{


    private boolean x = true;
    private boolean o = false;
    private double vyska = 2.8;
    private double mezera = 0.20;


    @Override
    public void execute(Player p) {
        new BukkitRunnable() {
            KPlayer kp = KPlayer.getPlayer(p);
            ParticleDrawWing drawWing = new ParticleDrawWing();
            final boolean[][] shape = getShape(kp.getStylparticle());
            final EParticleShapes particleshapes = kp.getStylparticle();
            @Override
            public void run() {
                try {
                    drawWing.drawParticles(p.getLocation(), kp, shape, vyska, mezera);
                    if (!kp.isParticleStart() || !p.isOnline()){
                        this.cancel();
                    }
                    if (particleshapes != kp.getStylparticle()){
                        this.cancel();
                    }
                } catch (NullPointerException e){
                    this.cancel();
                }
            }

        }.runTaskTimer(Main.plugin, 0, 8);
    }

    private boolean[][] getShape(EParticleShapes shapes){
        if (EParticleShapes.WING_SiMPlE == shapes){
            return simpleWing;
        } else if (EParticleShapes.WING_DEMON == shapes){
            return demonWing;
        } else  if (EParticleShapes.WING_OUSKA_S_OCASKEM == shapes){
            this.mezera = 0.15;
            this.vyska = 2.20;
            return ouskasocasem;
        } else if (EParticleShapes.WING_ANGEL == shapes){
            this.vyska = 5;
            return angelWing;
        } else if (EParticleShapes.WING_V_1_15 == shapes){
            this.vyska = 3.2;
            return wing_v_1_15;
        }
        return null;
    }


    private boolean[][] ouskasocasem = {
            {o, o, o, o, o, o, x, o, o, o, x, o, o, o, o, o, o},
            {o, o, o, o, o, o, x, o, o, o, x, o, o, o, o, o, o},
            {o, o, o, o, o, x, o, x, o, x, o, x, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, x, x, o, o, o, o, x, x, o, o, o, o, o, o, o, o},
            {o, o, x, x, o, o, o, x, x, o, o, o, o, o, o, o, o},
            {o, o, x, x, x, o, x, x, o, o, o, o, o, o, o, o, o},
            {o, o, o, x, x, x, x, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, o, x, x, o, o, o, o, o, o, o, o, o, o, o},
    };

    private boolean[][] simpleWing = {
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
            {o, o, o, x, o, o, o, o, o, o, o, o, o, x, o, o, o},
            {o, o, x, x, o, o, o, o, o, o, o, o, o, x, x, o, o},
            {o, x, x, x, x, o, o, o, o, o, o, o, x, x, x, x, o},
            {o, x, x, x, x, o, o, o, o, o, o, o, x, x, x, x, o},
            {o, o, x, x, x, x, o, o, o, o, o, x, x, x, x, o, o},
            {o, o, o, x, x, x, x, o, o, o, x, x, x, x, o, o, o},
            {o, o, o, o, x, x, x, x, x, x, x, x, x, o, o, o, o},
            {o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o},
            {o, o, o, o, o, o, x, x, x, x, x, o, o, o, o, o, o},
            {o, o, o, o, o, x, x, o, o, o, x, x, o, o, o, o, o},
            {o, o, o, o, x, x, x, o, o, o, x, x, x, o, o, o, o},
            {o, o, o, x, x, x, o, o, o, o, o, x, x, x, o, o, o},
            {o, o, o, o, x, o, o, o, o, o, o, o, x, o, o, o, o},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o},
    };

    private boolean[][] demonWing = {
            {o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, o, o, o, o, o, o, o, o, o, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, o, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o, o,},
            {o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o,},
            {o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o,},
            {o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o,},
            {o, x, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, x, o,},
            {x, o, o, o, o, x, o, o, o, x, x, x, o, o, o, o, x, x, x, x, x, x, x, x, x, o, o, o, o, x, x, x, o, o, o, x, o, o, o, o, x,},
            {o, o, o, o, x, o, o, o, o, o, x, o, o, o, o, o, o, x, x, x ,x, x, x, x, o, o, o, o, o, o, x, o, o, o, o, o, x, o, o, o, o,},
            {o, o, o, x, o, o, o, o, o, o, x, o, o, o, o, o, o, o, x, x, x, x, x, o, o, o, o, o, o, o, x, o, o, o, o, o, o, x, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, x, x, x, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, x, o, o, o, o, o, o, x, x, x, o, o, o, o, o, o, x, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,},
    };

    private boolean[][] angelWing = {
            {x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x,},
            {o, o, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, o, o,},
            {o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o,},
            {o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o,},
            {o, o, x, x, x, x, o, o, o, o, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, o, o, o, o, x, x, x, x, o, o,},
            {o, o, o, o, x, x, x, x, o, o, x, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, x, o, o, x, x, x, x, o, o, o, o,},
            {o, o, o, o, o, o, x, x, x, x, x, x, o, o, o, x, x, x, x, x, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, x, x, x, x, x, o, o, o, x, x, x, x, x, x, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, x, x, x, o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o, x, x, x, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, o, o, o, x, x, x, x, x, o, o, o, o, o,   o,   o, o, o, o, o, x, x, x, x, x, o, o, o, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, x, x, o, x, x, o, x, x, x, x, o, o, o, o, x, x, x, x, x, x, x, o, o, o,   o,   o, o, o, x, x, x, x, x, x, x, o, o, o, o, x, x, x, x, o, x, x, o, x, x, o, o, o, o, o,},
            {o, o, o, o, o, o, x, x, x, x, x, x, x, x, o, o, o, x, x, x, o, o, o, x, x, x, x, o, o,   o,   o, o, x, x, x, x, o, o, o, x, x, x, o, o, o, x, x, x, x, x, x, x, x, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, x, x, o, o, o, o, x, x, x, o, o, o, o, x, x, x, x, x, o,   o,   o, x, x, x, x, x, o, o, o, o, x, x, x, o, o, o, o, x, x, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, o, o, o, x, x, x, x, o, x, x, o,   o,   o, x, x, o, x, x, x, x, o, o, o, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, x, x, o, x, x, x, x, x, x, x, o, o, x, x, x, o, o, o, x, x, o,   o,   o, x, x, o, o, o, x, x, x, o, o, x, x, x, x, x, x, x, o, x, x, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o, x, x, x, x, o, o, x, x, o, o,   o,   o, o, x, x, o, o, x, x, x, x, o, o, o, o, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, x, x, o, x, o, o, o, o, o, x, x, x, o, o, o, x, x, o, o, o,   o,   o, o, o, x, x, o, o, o, x, x, x, o, o, o, o, o, x, o, x, x, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, o, o, o, x, x, o, o, o, o,   o,   o, o, o, o, x, x, o, o, o, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, x, o, o, x, x, x, x, o, o, o,   o,   o, o, o, x, x, x, x, o, o, x, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, x, x, x, o, x, x, o, o, o, o, x, x, x, x, x, o, o, o,   o,   o, o, o, x, x, x, x, x, o, o, o, o, x, x, o, x, x, x, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, x, x, x, o, x, x, x, x, o,   o,   o, x, x, x, x, o, x, x, x, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, o, o, o, x, x, x, x,   x,   x, x, x, x, o, o, o, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, o, o, o, o, o, o, x, x, x,   x,   x, x, x, o, o, o, o, o, o, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o,   o,   o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o,},
    };

    private boolean[][] wing_v_1_15 = {
            {o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, o,   o, o, o, o,   o, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o,},
            {x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, o, o,   o, o, o, o,   o, o, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, x,},
            {o, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, o, o,   o, o, o, o,   o, o, x, x, o, o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, o,},
            {o, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o, x, x, o, o, o,   o, o, o, o,   o, o, o, x, x, o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, o,},
            {o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, x, x, o, o,   o, o, o, o,   o, o, x, x, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o,},
            {o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x, x, o,   o, o, o, o,   o, x, x, o, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, o,},
            {o, o, o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, o, x, x,   o, o, o, o,   x, x, o, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, x, x, x, x, x, x, x, x, x, x, x, x, x, o, x,   x, o, o, x,   x, o, x, x, x, x, x, x, x, x, x, x, x, x, x, o, o, o, o, o, o,},
            {o, o, o, o, o, x, o, o, o, o, x, x, x, o, x, x, x, x, x, x, x,   x, o, o, x,   x, x, x, x, x, x, x, o, x, x, x, o, o, o, o, x, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, x, x, x, x,   x, o, o, x,   x, x, x, x, o, o, o, o, o, o, o, x, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, x, x,   x, o, o, x,   x, x, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, x, x,   o, o, o, o,   x, x, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, o, x, x, o,   o, o, o, o,   o, x, x, o, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, o, o, x, x, x, x, x, x, x, x, o, x, x, o, o,   o, o, o, o,   o, o, x, x, o, x, x, x, x, x, x, x, x, o, o, o, o, o, o, o, o,},
            {o, o, o, o, o, o, x, x, x, o, o, x, o, o, o, o, x, x, o, o, o,   o, o, o, o,   o, o, o, x, x, o, o, o, o, x, o, o, x, x, x, o, o, o, o, o, o,},
            {o, o, o, o, o, x, o, o, o, o, x, o, o, o, o, x, x, o, o, o, o,   o, o, o, o,   o, o, o, o, x, x, o, o, o, o, x, o ,o, o, o, x, o, o, o, o, o,},

    };
}
