package me.domos.kostkuj.general.forFun.particle;

import org.bukkit.Particle;

public enum EParticle {

    //ITEM_CRACK(Particle.ITEM_CRACK), - nejde
    //FALLING_DUST(Particle.FALLING_DUST),
    FLAME(Particle.FLAME),
    FIREWORKS_SPARK(Particle.FIREWORKS_SPARK),
    ENCHANTMENT_TABLE(Particle.ENCHANTMENT_TABLE),
    HEARTH(Particle.HEART),
    CRIT_MAGIC(Particle.CRIT_MAGIC),
    BUBBLE_COLUMN_UP(Particle.BUBBLE_COLUMN_UP),
    DRAGON_BREATH(Particle.DRAGON_BREATH),
    DRIP_WATER(Particle.DRIP_WATER),
    SQUID_INK(Particle.SQUID_INK),
    BUBBLE_POP(Particle.BUBBLE_POP),
    SPELL_MOB(Particle.SPELL_MOB),
    SMOKE_NORMAL(Particle.SMOKE_NORMAL),
    SMOKE_LARGE(Particle.SMOKE_LARGE),
    REDSTONE(Particle.REDSTONE),
    SPELL(Particle.SPELL),
    CRIT(Particle.CRIT);

    Particle particle;

    EParticle(Particle particle){
        this.particle = particle;
    }

    public String getPerm(){
        return "kostkuj.particle.set.typ." + getName();
    }

    public String getName(){
        return this.particle.toString().toLowerCase();
    }

    public Particle getParticle(){
        return particle;
    }

    public static Particle getParticleFromName(String name){
        for (EParticle particle : EParticle.values()) {
            if (particle.getName().equalsIgnoreCase(name)) {
                return particle.getParticle();
            }
        }
        return null;
    }

    public static boolean isParticleExist(String name){
        for(EParticle particle : EParticle.values()){
            if (particle.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }


}
