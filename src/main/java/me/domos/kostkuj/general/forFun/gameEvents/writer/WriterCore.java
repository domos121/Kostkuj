package me.domos.kostkuj.general.forFun.gameEvents.writer;

import me.domos.kostkuj.Main;
import me.domos.kostkuj.bukkit.chat.ActionBarMessage;
import me.domos.kostkuj.bukkit.chat.SendSystem;
import me.domos.kostkuj.general.KeyGenerator;
import me.domos.kostkuj.general.connect.mysql.CraftCoin.MCraftCoins;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

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
        if(!typs.getName().equalsIgnoreCase(EWriterTyps.WRITE_TEXT.getName())) {
            ss.broadCast(WriteConfig.getQuestion());
        }
        new BukkitRunnable(){
            int currentTime = maxTime;
            int current = 0;
            @Override
            public void run() {
                --currentTime;
                current++;
                if (!WriteConfig.isStart()){
                    Player p = WriteConfig.getWinPlayer();
                    ss.broadCast("§6Vyhrál: §a" + p.getDisplayName() + "\n§6Správná odpověď: §a" + WriteConfig.getAnswer());
                    mCraftCoins.setCC(p.getName(), 10, 7, true);
                    this.cancel();
                }
                if (typs.getName().equalsIgnoreCase(EWriterTyps.WRITE_TEXT.getName())){
                    if (current > 35){
                        new ActionBarMessage().sendBroadcastMsg(WriteConfig.getQuestion());
                        current = 0;
                    }
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
        /*if (this.typs == EWriterTyps.QESTIONS){
            createQuestion();
            return true;
        } else*/
        if (this.typs == EWriterTyps.MATH){
            createExample();
            return true;
        } else if (this.typs == EWriterTyps.WRITE_TEXT){
            createKey();
            return true;
        }
        return false;
    }

    @NotNull
    private String createExample (){
        CreateExample example = new CreateExample(dificluty);
        WriteConfig.setAnswer(example.getAnswer() + "", "§6Kolik se rovná: §a'" + example.getExample() + "'§6?");
        return "";
    }

    private boolean createKey(){
        KeyGenerator keyGen = new KeyGenerator();
        String key = keyGen.getSerialKey((dificluty.toInt()*3), true);
        WriteConfig.setAnswer(key, "§6§lNapiš do chatu jako první: '§a§l" + key + "§6§l'!");
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