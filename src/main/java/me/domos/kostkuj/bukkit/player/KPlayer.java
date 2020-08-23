package me.domos.kostkuj.bukkit.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class KPlayer{
    private static List<KPlayer> registry = new ArrayList<KPlayer>();

    private Player player;
    private String authKey;
    private String discordUserKey;

    public KPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public static void registerPlayer(KPlayer customplayer){
        registry.add(customplayer);
    }

    public static void unregisterPlayer(KPlayer customplayer){
        registry.remove(customplayer);
    }

    @Nullable
    @SuppressWarnings("rawtypes")
    public static KPlayer getPlayer(Player player){
        Iterator iterator = registry.iterator();
        while(iterator.hasNext()){
            KPlayer customplayer = (KPlayer)iterator.next();
            if(customplayer.getPlayer() == player)
                return customplayer;
        }
        return null;
    }

    public static KPlayer getPlayer(@NotNull CommandSender sr){
        Player player = Bukkit.getPlayer(sr.getName());
        return getPlayer(player);
    }

    public static KPlayer getPlayer(UUID uuid){
        Player player = Bukkit.getPlayer(uuid);
        return getPlayer(player);
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getDiscordUserKey() {
        return discordUserKey;
    }

    public void setDiscordUserKey(String discordUserKey) {
        this.discordUserKey = discordUserKey;
    }
}