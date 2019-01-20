package by.domos.Kostkuj.server.player;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import by.domos.Kostkuj.Main;
import by.domos.Kostkuj.enums.EParticle;
import by.domos.Kostkuj.enums.EParticleShapes;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KPlayer{
    private static List<KPlayer> registry = new ArrayList<KPlayer>();

    private Player player;
    private Particle particle;
    private EParticleShapes stylparticle;
    private boolean isParticleStart = false;
    private String authKey;
    private String discordUserKey;
    private int colorR = 0;
    private int colorG = 0;
    private int colorB = 0;

    public KPlayer(Player player, EParticleShapes partkreyba, Particle part){
        this.player = player;
        this.stylparticle = partkreyba;
        this.particle = part;
    }

    public Player getPlayer() {
        return player;
    }

    public Particle getParticle(){
        return this.particle;
    }

    public EParticleShapes getStylparticle() {
        return stylparticle;
    }

    public void setStylparticle(EParticleShapes stylparticle) {
        this.stylparticle = stylparticle;
    }

    public void setParticle(String particle){
        this.particle = EParticle.getParticleFromName(particle);
    }

    public static void registerPlayer(KPlayer customplayer){
        registry.add(customplayer);
    }

    public static void unregisterPlayer(KPlayer customplayer){
        registry.remove(customplayer);
    }

    public void spawnParticle(Location location){
        if(this.particle == Particle.REDSTONE){
            if (colorB < 0 || colorG < 0 || colorR < 0){
                Particle.REDSTONE.builder().color(Main.random.nextInt(255), Main.random.nextInt(255), Main.random.nextInt(255)).allPlayers().location(location).count(2).spawn();
            } else {
                Particle.REDSTONE.builder().color(colorR, colorG, colorB).allPlayers().location(location).count(2).spawn();
            }
        } else {
            location.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, 1);
        }
    }

    @SuppressWarnings("rawtypes")
    public static KPlayer getPlayer(Player player){
        Iterator iterator = registry.iterator();
        while(iterator.hasNext()){
            KPlayer customplayer = (KPlayer)iterator.next();
            if(customplayer.getPlayer() == player)
                return customplayer;
        }
        return null;
    }

    public static KPlayer getPlayer(CommandSender sr){
        Player player = Bukkit.getPlayer(sr.getName());
        return getPlayer(player);
    }

    public static KPlayer getPlayer(UUID uuid){
        Player player = Bukkit.getPlayer(uuid);
        return getPlayer(player);
    }

    public boolean isParticleStart() {
        return isParticleStart;
    }

    public void setParticleStart(boolean particleStart) {
        isParticleStart = particleStart;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getDiscordUserKey() {
        return discordUserKey;
    }

    public void setDiscordUserKey(String discordUserKey) {
        this.discordUserKey = discordUserKey;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }
}