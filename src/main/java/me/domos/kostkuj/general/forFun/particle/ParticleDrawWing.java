package me.domos.kostkuj.general.forFun.particle;

import me.domos.kostkuj.bukkit.player.KPlayer;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;


public class ParticleDrawWing {

    public void drawParticles(Location location, KPlayer kp, boolean[][] shape, double vyska, double mezera) {
        if (shape == null){
            kp.setParticleStart(false);
            return;
        }
        // velikost mezery.
        double space = mezera;
        // umístění obrazce na střed (mezera * (velikost obrazce-1)/2)
        double defX = location.getX() - (space * (shape[0].length-1) / 2);
        double x = defX;
        double y = location.clone().getY() + vyska;
        Random random = new Random();
        double fire = -((location.getYaw() + 180) / 60);
        fire += (location.getYaw() < -180 ? 3.25 : 2.985);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {

                    Location target = location.clone();
                    target.setX(x);
                    target.setY(y);

                    Vector v = target.toVector().subtract(location.toVector());
                    Vector v2 = getBackVector(location);
                    v = rotateAroundAxisY(v, fire);
                    v2.setY(0).multiply(-0.5);

                    location.add(v);
                    location.add(v2);
                    kp.spawnParticle(location);
                    location.subtract(v2);
                    location.subtract(v);
                }
                x += space;
            }
            y -= space;
            x = defX;
        }
    }

    public static Vector rotateAroundAxisY(Vector v, double fire) {
        double x, z, cos, sin;
        cos = Math.cos(fire);
        sin = Math.sin(fire);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static Vector getBackVector(Location loc) {
        final float newZ = (float) (loc.getZ() + (1 * Math.sin(Math.toRadians(loc.getYaw() + 90 * 1))));
        final float newX = (float) (loc.getX() + (1 * Math.cos(Math.toRadians(loc.getYaw() + 90 * 1))));
        return new Vector(newX - loc.getX(), 0, newZ - loc.getZ());
    }
}
