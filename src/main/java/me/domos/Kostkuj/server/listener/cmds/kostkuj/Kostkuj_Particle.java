package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;

public class Kostkuj_Particle {

    Kostkuj_Particle_Start start = new Kostkuj_Particle_Start();
    Kostkuj_Particle_Stop stop = new Kostkuj_Particle_Stop();
    Kostkuj_Particle_Shape styl = new Kostkuj_Particle_Shape();
    Kostkuj_Particle_Typ typ = new Kostkuj_Particle_Typ();
    SendSystem ss = new SendSystem();

    public void Particle(String[] args, CommandSender sr ){
        if (args.length == 1){
            ss.info(sr, "Vyber úkon.");
            return;
        }

        if (args[1].equalsIgnoreCase("start")){
            start.Start(args, sr);
        } else if (args[1].equalsIgnoreCase("stop")){
            stop.Stop(args, sr);
        } else if (args[1].equalsIgnoreCase("shape")){
            styl.Styl(args, sr);
        } else if (args[1].equalsIgnoreCase("typ")){
            typ.Type(args, sr);
        }
    }
}
