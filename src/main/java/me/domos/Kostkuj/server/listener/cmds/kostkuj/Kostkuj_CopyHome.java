package me.domos.Kostkuj.server.listener.cmds.kostkuj;

import me.domos.Kostkuj.FileManager;
import me.domos.Kostkuj.server.chat.SendSystem;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Kostkuj_CopyHome {
    FileManager sett = FileManager.getInstance();
    SendSystem ss = new SendSystem();

    private static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    private static void deleteFile(File dest) throws IOException {
        Files.delete(dest.toPath());
    }

    private static boolean existFile(File source) {
        return Files.exists(source.toPath());
    }

    public boolean onCommand(CommandSender sr, String[] args) {

        if (args.length == 1){
            ss.info(sr, "Use: /kostkuj Kostkuj_CopyHome <jmeno hrace>");
            return false;
        }

        OfflinePlayer op = Bukkit.getServer().getOfflinePlayer(args[1]);
        String suid = op.getUniqueId().toString();

        File source = new File("C:\\Users\\Kostkuj\\Desktop\\server - znovuzrozen\\plugins\\userdata\\" + suid + ".yml");
        File dest = new File("C:\\Users\\Kostkuj\\Desktop\\server - znovuzrozen\\plugins\\Essentials\\userdata\\" + suid + ".yml");


        if (!existFile(source)){
            ss.info(sr, "Hrac neexistuje");
            return true;
        }

        if (!existFile(dest)){
            try {
                copyFile(source, dest);
                ss.info(sr, "Soubor presunut");
                return true;
            } catch (IOException e) {
                ss.info(sr, e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                deleteFile(dest);
                ss.info(sr, "Soubor byl vymazan.");
            } catch (IOException e) {
                ss.info(sr, e.getMessage());
                e.printStackTrace();
            }
            try {
                copyFile(source, dest);
                ss.info(sr, "Soubor presunut");
                return true;
            } catch (IOException e) {
                ss.info(sr, e.getMessage());
                e.printStackTrace();
            }
        }

        return false;
    }
}
