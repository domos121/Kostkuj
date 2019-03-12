package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kostkuj_Particle {

    private Kostkuj_Particle_Start start = new Kostkuj_Particle_Start();
    private Kostkuj_Particle_Stop stop = new Kostkuj_Particle_Stop();
    private Kostkuj_Particle_Shape styl = new Kostkuj_Particle_Shape();
    private Kostkuj_Particle_Typ typ = new Kostkuj_Particle_Typ();
    private SendSystem ss = new SendSystem();

    public Kostkuj_Particle() {
    }

    public void Particle(String[] args, CommandSender sr) {
        if (!(sr instanceof Player)){
            ss.noPlayer(sr);
            return;
        }

        if (args.length == 1) {
            this.ss.info(sr, "Vyber úkon.");
        } else {
            if (args[1].equalsIgnoreCase("start")) {
                this.start.Start(args, sr);
            } else if (args[1].equalsIgnoreCase("stop")) {
                this.stop.Stop(args, sr);
            } else if (args[1].equalsIgnoreCase("shape")) {
                this.styl.Styl(args, sr);
            } else if (args[1].equalsIgnoreCase("typ")) {
                this.typ.Type(args, sr);
            }

        }
    }
}
