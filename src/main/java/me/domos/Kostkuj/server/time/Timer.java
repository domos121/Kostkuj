package me.domos.Kostkuj.server.time;
import me.domos.Kostkuj.Main;
import me.domos.Kostkuj.connect.MySQL.commandRequest.MRequestGet;
import me.domos.Kostkuj.enums.ECfg;
import me.domos.Kostkuj.server.chat.JSON.JsonBroadCast;
import me.domos.Kostkuj.server.chat.SendSystem;
import me.domos.Kostkuj.server.event.writer.WriterCore;
import me.domos.Kostkuj.server.listener.cmds.kostkuj.Kostkuj_Save;
import me.domos.Kostkuj.server.votemute.VoteMuteSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Instant;
import java.util.Random;


// Jmeno Tridy
public class Timer {

    public static int writerTime;
    // Jmeno Metody
    public static void Time() {
        // Vlatní vlákno (opakovač (50s))
            Random r = new Random();
            Timer.writerTime = r.nextInt(45) + 45;
        new BukkitRunnable() {
            // Spouštěč
            public void run() {
                --Timer.writerTime;
                if (Timer.writerTime<=0 && ECfg.isAllowAutoWriter() && Bukkit.getOnlinePlayers().size()>=5){
                    new WriterCore(null, null, null);
                    Timer.writerTime = r.nextInt(45) + 45;
                }
                // Načtení cfg
                Time time = new Time();
                // Aktuální čas
                Instant instant = Instant.now();
                long unix = instant.toEpochMilli()/1000;
                JsonBroadCast jbc = new JsonBroadCast();
                // Časy, načtené z config.yml
                String[][] currenttime = ECfg.getTimeListWithEvents();
                // Čas, který porovnávám
                String unixtime = time.getTimeForTimer(unix);
                // načte knihovny ReqestCMD
                MRequestGet mrg = new MRequestGet();
                // Přečte všechny requesty
                mrg.getRequest();
                // Přečte všechny časy z config.yml
                for(int i = 0; i < currenttime.length; i++) {
                    // pokud se některej rovná s aktuálním
                    if (currenttime[i][0].equals(unixtime)) {
                        if(currenttime[i][1].equalsIgnoreCase("msg")){
                            jbc.jsonBcKostkuj("[\n" + "  {\n" + "    \"text\":\"#info#\"\n" + "  },\n" + "  {\n" + "    \"text\":\" Nyni muzes hlasovat: \",\n" + "    \"color\":\"gray\"\n" + "  },\n" + "  {\n" + "    \"text\":\"➥ZDE.\",\n" + "    \"color\":\"dark_gray\",\n" + "    \"clickEvent\":{\n" + "      \"action\": \"open_url\",\n" + "      \"value\":\"https://czech-craft.eu/vote?id=12614&user=#sr#\"\n" + "    },\n" + "    \"hoverEvent\":{\n" + "      \"action\": \"show_text\",\n" + "      \"value\":\"§cNastala suda hodina:\\n§aNyni muzes hlasovat.\\nKliknutím se dostanes na svuj odkaz.\"\n" + "    }\n" + "  }\n" + "]");
                        }
                        // ukončí cykl
                        break;
                    }
                }
            }
        }.runTaskTimer(Main.plugin, 0, 20 * 57); // plugin, Čas, nwm
    }

    public void serverRestart(final int time, BossBar bar, String stav){
        final SendSystem ss = new SendSystem();
        final float prgs2 = time;
        new BukkitRunnable(){
            int prgs = time;

            public void run() {
                prgs--;

                float progres = prgs/prgs2;

                for (Player c : Bukkit.getOnlinePlayers()) {
                    bar.addPlayer(c);
                }

                bar.setTitle(ChatColor.GOLD + "Server bude " + stav + " za: " + ChatColor.GREEN + this.prgs+ ChatColor.GOLD + "s.");
                bar.setProgress(progres);

                if (this.prgs == 30){
                    ss.broadCast(ChatColor.GOLD + "Server bude " + stav + " za: " + ChatColor.GREEN + this.prgs + ChatColor.GOLD + "s.");
                }

                if (this.prgs < 10){
                    ss.broadCast(ChatColor.GOLD + "Server bude " + stav + " za: " + ChatColor.GREEN + this.prgs + ChatColor.GOLD + "s.");
                }

                restartingTimerSettings rts = restartingTimerSettings.getInstance();
                if (rts.isStop()){
                    bar.removeAll();
                    cancel();
                }

                if (this.prgs == 0){
                    if (stav.equalsIgnoreCase("restartovan")) {
                        Bukkit.spigot().restart();
                    } else if (stav.equalsIgnoreCase("vypnut")){
                        Bukkit.shutdown();
                    }
                    bar.removeAll();
                    this.cancel();
                }
            }

        }.runTaskTimer(Main.plugin, 20 * 1, 20*1);
    }

    public void voteMuteClose(){
        new BukkitRunnable(){
            VoteMuteSettings vms = new VoteMuteSettings();
            SendSystem ss = new SendSystem();
            int time = 300;
            public void run() {
               if (vms.getAcive()){
                   this.time = this.time - 5;
                   if (this.time == 60){
                       ss.broadCast("§bPosledních 60s pro votemute.");
                   }
                   if (this.time <= 0){
                       Bukkit.getServer().broadcastMessage("§bCas vyprsel, hlasování bylo ukončeno.");
                       vms.setActiv(false, null, null, 0);
                       cancel();
                   }
               } else {
                   cancel();
               }
            }

        }.runTaskTimer(Main.plugin, 20 * 5, 20*5);
    }

    public static void saveMap(){
        final Kostkuj_Save s = new Kostkuj_Save();
        new BukkitRunnable(){
            public void run() {
                s.saveWorld();
            }
        }.runTaskTimer(Main.plugin, 20*10, 20*60*10);
    }
}
