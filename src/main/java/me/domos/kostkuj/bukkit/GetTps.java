package me.domos.kostkuj.bukkit;

import net.minecraft.server.v1_16_R1.MinecraftServer;

import java.text.DecimalFormat;

public class GetTps {

    public String getTps() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return (decimalFormat.format(MinecraftServer.getServer().recentTps[0]));
    }
}
