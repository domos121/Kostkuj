package by.domos.Kostkuj.server;

import net.minecraft.server.v1_13_R2.MinecraftServer;

import java.text.DecimalFormat;

public class getTps {

    public String getTps() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return(decimalFormat.format(MinecraftServer.getServer().recentTps[0]));
    }
}
