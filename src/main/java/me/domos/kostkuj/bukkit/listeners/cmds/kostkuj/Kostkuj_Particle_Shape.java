package me.domos.kostkuj.bukkit.listeners.cmds.kostkuj;

import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.player.KPlayer;
import me.domos.kostkuj.general.forFun.particle.EParticleShapes;
import me.domos.kostkuj.general.forFun.particle.ParticleCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_Particle_Shape {

    private SendSystem ss = new SendSystem();
    private ParticleCore pc = new ParticleCore();

    public Kostkuj_Particle_Shape() {
    }

    public void Styl(String[] args, CommandSender sr) {
        Player p = (Player)sr;
        if (args.length == 2) {
            this.ss.info(sr, "Zadej existující obrzazec.");
        } else if (!EParticleShapes.isShapeExist(args[2])) {
            this.ss.info(sr, "Obrazec " + args[2] + " nebyl nalezen.");
        } else if (!sr.hasPermission(EParticleShapes.getStylFromName(args[2]).getPerm())) {
            this.ss.noPerm(sr);
        } else {
            KPlayer kp = KPlayer.getPlayer(sr);
            kp.setStylparticle(EParticleShapes.getStylFromName(args[2]));
            if (kp.isParticleStart()) {
                kp.setParticleStart(false);
                this.pc.ParticleCore(p);
            }

            this.ss.info(sr, "Obrazec §a" + EParticleShapes.getStylFromName(args[2]).getName() + "§7 aktualizován.");
        }
    }
}
