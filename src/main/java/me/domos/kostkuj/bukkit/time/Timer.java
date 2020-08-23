package me.domos.kostkuj.bukkit.time;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.bukkit.chat.json.JsonBroadCast;
import me.domos.kostkuj.bukkit.chat.json.JsonSendMessage;
import me.domos.kostkuj.bukkit.chat.manage.voteMute.VoteMuteSettings;
import me.domos.kostkuj.bukkit.listeners.cmds.kostkuj.Kostkuj_Save;
import me.domos.kostkuj.general.connect.mysql.commandRequest.MRequestGet;
import me.domos.kostkuj.general.fileManager.ECfg;
import me.domos.kostkuj.general.forFun.gameEvents.writer.WriterCore;
import me.domos.kostkuj.models.voteModel.Vote;
import me.domos.kostkuj.models.voteModel.VoteBoard;
import me.domos.kostkuj.models.voteModel.VoteScoreBoardSettings;
import me.domos.kostkuj.models.voteModel.VoteTopPlayers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
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
            }
        }.runTaskTimer(Main.plugin, 0, 20 * 57); // plugin, Čas, nwm
    }

    public void serverRestart(final int time, BossBar bar, String desc, String stav){
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

                String sprgs = String.valueOf(this.prgs);
                bar.setTitle(desc.replace("{time}", sprgs));
                bar.setProgress(progres);

                if (this.prgs == 30){
                    ss.broadCast(desc.replace("{time}", sprgs));
                }

                if (this.prgs < 10){
                    ss.broadCast(desc.replace("{time}", sprgs));
                }

                TimerSettings rts = TimerSettings.getInstance();
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
                       ss.broadCast("§bPosledních 60s pro voteMute.");
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

    public static void isUserVote(){
        final Kostkuj_Save s = new Kostkuj_Save();
        new BukkitRunnable(){
            public void run() {
                JsonSendMessage jsm = new JsonSendMessage();
                VoteScoreBoardSettings voteScoreBoardSettings = new VoteScoreBoardSettings();
                new VoteTopPlayers().setTopPlayers("01");
                if (voteScoreBoardSettings.isAllow()){
                    String[] loc = voteScoreBoardSettings.getLocation().split(",");
                    VoteBoard voteScoreBoard = new VoteBoard(new Location(Bukkit.getWorld(loc[0]), Integer.parseInt(loc[1]), Integer.parseInt(loc[2]), Integer.parseInt(loc[3])), BlockFace.valueOf(voteScoreBoardSettings.getBlockFace().toUpperCase()));
                    voteScoreBoard.setScoreBoard();
                }
                for (Player c : Bukkit.getServer().getOnlinePlayers()){
                    if (Vote.getInstance().isVote(c.getName())){
                        jsm.jsonBcKostkuj(c, "[\n" + "  {\n" + "    \"text\":\"#prefix#\"\n" + "  },\n" + "  {\n" + "    \"text\":\" Nyni muzes hlasovat: \",\n" + "    \"color\":\"gray\"\n" + "  },\n" + "  {\n" + "    \"text\":\"➥ZDE.\",\n" + "    \"color\":\"dark_gray\",\n" + "    \"clickEvent\":{\n" + "      \"action\": \"open_url\",\n" + "      \"value\":\"https://czech-craft.eu/server/kostkuj/vote/?user=" + c.getName() + "\"\n" + "    },\n" + "    \"hoverEvent\":{\n" + "      \"action\": \"show_text\",\n" + "      \"value\":\"§cNastal tvůj čas:\\n§aNyni muzes hlasovat.\\nKliknutím se dostanes na svuj odkaz.\"\n" + "    }\n" + "  }\n" + "]");
                    }
                }
            }
        }.runTaskTimer(Main.plugin, 20*2*60, 20*60*15);
    }
}
