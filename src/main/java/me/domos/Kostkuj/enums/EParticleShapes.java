package me.domos.Kostkuj.enums;

import me.domos.Kostkuj.server.particle.*;
import org.bukkit.entity.Player;

public enum EParticleShapes {

    SPIRAL("spiral", new ParticleShapeSpiral()),
    BALL("ball", new ParticleShapeBall()),
    WAVE("wave", new ParticleShapeWave()),
    RING("ring", new ParticleShapeRing()),
    TRAIL("trail", new ParticleShapeTrail()),
    TEST("test", new ParticleShapeTest()),
    SNOW("snow", new ParticleShapeSnow()),
    WING_SiMPlE("simplewing", new ParticleShapeWing()),
    WING_DEMON("demonwing", new ParticleShapeWing()),
    WING_OUSKA_S_OCASKEM("ouskasocasemwing", new ParticleShapeWing()),
    WING_ANGEL("angelwing", new ParticleShapeWing()),
    WING_V_1_15("v1_15wing", new ParticleShapeWing()),
    STOP("Stop", null);

    private final String name;
    private IParticleShape particleShape;

    EParticleShapes(String name, IParticleShape particleShape) {
        this.name = name;
        this.particleShape = particleShape;
    }

    public String getName(){
        return this.name;
    }

    public void particleShapeExecute(Player p){
        if (this.particleShape == null){
            return;
        }
        this.particleShape.execute(p);
    }

    public String getPerm(){
        return "kostkuj.particle.set.shape." + this.name;
    }

    public static EParticleShapes getStylFromName(String name){
        for (EParticleShapes shape : EParticleShapes.values()) {
            if (shape.getName().equalsIgnoreCase(name)) {
                return shape;
            }
        }
        return null;
    }

    public static boolean isShapeExist(String name){
        for (EParticleShapes shape : EParticleShapes.values()){
            if (shape.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

}
