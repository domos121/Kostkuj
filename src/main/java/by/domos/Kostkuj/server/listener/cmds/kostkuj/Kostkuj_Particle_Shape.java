package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.enums.EParticleShapes;
import by.domos.Kostkuj.server.player.KPlayer;
import by.domos.Kostkuj.server.chat.SendSystem;
import by.domos.Kostkuj.server.particle.ParticleCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_Particle_Shape {

    SendSystem ss = new SendSystem();
    ParticleCore pc = new ParticleCore();

    public void Styl(String[] args, CommandSender sr ){

        Player p = Bukkit.getPlayer(sr.getName());
        if (args.length == 2){
            ss.info(sr, "Zadej existující obrzazec.");
            return;
        }

        if (!EParticleShapes.isShapeExist(args[2])){
            ss.info(sr, "Obrazec " + args[2] + " nebyl nalezen.");
            return;
        }
        if (!sr.hasPermission(EParticleShapes.getStylFromName(args[2]).getPerm())){
            ss.noPerm(sr);
            return;
        }
        KPlayer kp = KPlayer.getPlayer(sr);
        kp.setStylparticle(EParticleShapes.getStylFromName(args[2]));
        if (kp.isParticleStart()){
            kp.setParticleStart(false);
            pc.ParticleCore(p);
        }
        ss.info(sr, "Obrazec §a" + EParticleShapes.getStylFromName(args[2]).getName() + "§7 aktualizovan.");

    }
}
