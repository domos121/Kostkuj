package me.domos.kostkuj.general.forFun.gameEvents.writer;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.KeyGenerator;
import me.domos.kostkuj.general.connect.mysql.CraftCoin.MCraftCoins;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class WriterCore {

    private final SendSystem ss = new SendSystem();

    private EWriterDificluty dificluty;
    private EWriterTyps typs;
    private final MCraftCoins mCraftCoins = new MCraftCoins();

    public WriterCore(final Player player, final String dificlty, final String typs){
        if(WriteConfig.isStart()){
            return;
        }
        if (dificlty == null){
            this.dificluty = EWriterDificluty.randomDificluty();
        } else {
            this.dificluty = EWriterDificluty.getDificlutyFromName(dificlty);
        }

        if (typs == null){
            this.typs = EWriterTyps.randomQestionsTyps();
        } else {
            this.typs = EWriterTyps.getDificlutyFromName(typs);
        }

        if (this.dificluty != null && this.typs != null) {
            this.write();
        } else {
            this.ss.info(player, "Dané hodnoty nejsou platný.");
        }
    }

    private void write() {
        final int maxTime = 120*20;
        WriteConfig.setStart(createAnswer());
        ss.broadCast(WriteConfig.getQuestion());
        new BukkitRunnable(){
            int currentTime = maxTime;
            @Override
            public void run() {
                --currentTime;
                if (!WriteConfig.isStart()){
                    Player p = WriteConfig.getWinPlayer();
                    ss.broadCast("§6Vyhrál: §a" + p.getDisplayName() + "\n§6Správná odpověď: §a" + WriteConfig.getAnswer());
                    mCraftCoins.setCC(p.getName(), 10, 7, true);
                    this.cancel();
                }
                if(currentTime == 15*20){
                    ss.broadCast("§6Posledních §a15s §6pro zaslání odpovědi.");
                }
                if (currentTime <= 0){
                    WriteConfig.setStart(false);
                    ss.broadCast("§6Čas vypršel správná odpověď byla §a'" + WriteConfig.getAnswer() + "'§6.");
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.plugin, 0, 1);
    }

    private boolean createAnswer(){
        if (this.typs == EWriterTyps.QESTIONS){
            createQuestion();
            return true;
        } else if (this.typs == EWriterTyps.MATH){
            createExample();
            return true;
        } else if (this.typs == EWriterTyps.WRITE_TEXT){
            createKey();
            return true;
        }
        return false;
    }

    private String createExample (){
        CreateExample example = new CreateExample(dificluty);
        WriteConfig.setAnswer(example.getAnswer() + "", "§6Kolik se rovná: §a'" + example.getExample() + "'§6?");
        return "";
    }

    private boolean createKey(){
        KeyGenerator keyGen = new KeyGenerator();
        String key = keyGen.getSerialKey((int)(dificluty.toInt()*2.5));
        WriteConfig.setAnswer(key, "§6Napiš do chatu jako první: '§a" + key + "§6'!");
        return true;
    }

    private String createQuestion(){
        Random r = new Random();
        if (r.nextBoolean()){
            createKey();
        } else createExample();
        return "";
    }
}