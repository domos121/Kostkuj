package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.enums.EParticle;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.player.KPlayer;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;

public class Kostkuj_Particle_Typ {
    private int colorR;
    private int colorG;
    private int colorB;
    private SendSystem ss = new SendSystem();

    public Kostkuj_Particle_Typ() {
    }

    public void Type(String[] args, CommandSender sr) {
        KPlayer kp = KPlayer.getPlayer(sr);
        if (args.length == 2) {
            this.ss.info(sr, "§7Zadej existující particle.");
        } else if (!EParticle.isParticleExist(args[2])) {
            this.ss.info(sr, "§7Particle §a" + args[2] + " §7nebyl nalezen.");
        } else {
            kp.setParticle(args[2]);
            this.ss.info(sr, "§7Particle §a" + args[2] + "§7 byl aktualizovan");
            if (args[2].equalsIgnoreCase(Particle.REDSTONE.toString()) && args.length >= 4 && (args[3].contains("r:") || args[3].contains("g:") || args[3].contains("b:"))) {
                for(int i = 3; args.length >= i; ++i) {
                    if (args[i - 1].contains("r:")) {
                        try {
                            this.colorR = Integer.parseInt(args[i - 1].replace("r:", ""));
                            if (-1 <= this.colorR && this.colorR <= 255) {
                                kp.setColorR(this.colorR);
                            }
                        } catch (NumberFormatException var8) {
                            this.ss.info(sr, "Červená barva byla napsána ve špatném vormátu.");
                        }
                    } else if (args[i - 1].contains("g:")) {
                        try {
                            this.colorG = Integer.parseInt(args[i - 1].replace("g:", ""));
                            if (-1 <= this.colorG && this.colorG <= 255) {
                                kp.setColorG(this.colorG);
                            }
                        } catch (NumberFormatException var7) {
                            this.ss.info(sr, "Zelená barva byla napsána ve špatném vormátu.");
                        }
                    } else if (args[i - 1].contains("b:")) {
                        try {
                            this.colorB = Integer.parseInt(args[i - 1].replace("b:", ""));
                            if (-1 <= this.colorB && this.colorB <= 255) {
                                kp.setColorB(this.colorB);
                            }
                        } catch (NumberFormatException var6) {
                            this.ss.info(sr, "Modrá barva byla napsána ve špatném vormátu.");
                        }
                    }
                }
            }

        }
    }
}
