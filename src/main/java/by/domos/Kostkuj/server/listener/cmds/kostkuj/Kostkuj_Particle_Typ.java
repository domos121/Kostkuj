package by.domos.Kostkuj.server.listener.cmds.kostkuj;

import by.domos.Kostkuj.enums.EParticle;
import by.domos.Kostkuj.server.player.KPlayer;
import by.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;

public class Kostkuj_Particle_Typ {

    private int colorR;
    private int colorG;
    private int colorB;

    SendSystem ss = new SendSystem();

    public void Type(String[] args, CommandSender sr ) {
        KPlayer kp = KPlayer.getPlayer(sr);
        if (args.length == 2) {
            ss.info(sr, "§7Zadej existující particle.");
            return;
        }

        if (!EParticle.isParticleExist(args[2])) {
            ss.info(sr, "§7Particle §a" + args[2] + " §7nebyl nalezen.");
            return;
        }

        kp.setParticle(args[2]);
        sr.sendMessage("§7Particle §a" + args[2] + "§7 byl aktualizovan");

        if (args[2].equalsIgnoreCase(Particle.REDSTONE.toString())) {
            if (args.length >= 4) {
                if (args[3].contains("r:") || args[3].contains("g:") || args[3].contains("b:")) {
                    for (int i = 3; args.length >= i; i++) {
                        if (args[i - 1].contains("r:")) {
                            try {
                                colorR = Integer.parseInt(args[i - 1].replace("r:", ""));
                                if (-1 <= colorR && colorR <= 255) {
                                    kp.setColorR(colorR);
                                }
                            }catch (NumberFormatException e){
                                ss.info(sr, "Červená barva byla napsána ve špatném vormátu.");
                            }
                        } else if (args[i - 1].contains("g:")) {
                            try {
                                colorG = Integer.parseInt(args[i - 1].replace("g:", ""));
                                if (-1 <= colorG && colorG <= 255) {
                                    kp.setColorG(colorG);
                                }
                            } catch (NumberFormatException e){
                                ss.info(sr, "Zelená barva byla napsána ve špatném vormátu.");
                            }
                        } else if (args[i - 1].contains("b:")) {
                            try {
                                colorB = Integer.parseInt(args[i - 1].replace("b:", ""));
                                if (-1 <= colorB && colorB <= 255) {
                                    kp.setColorB(colorB);
                                }
                            }catch (NumberFormatException e){
                                ss.info(sr, "Modrá barva byla napsána ve špatném vormátu.");
                            }
                        }
                    }
                }
            }
        }
    }
}
