package me.domos.kostkuj.bukkit.sign;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;

public class WriteOnSign {

    public void writeOnSign(Location loc, String... args) {
        World world = Bukkit.getServer().getWorld(loc.getWorld().getName());
        if (world == null) {
            return; // no world found by that name
        }
        Block block = world.getBlockAt(loc);
        BlockState state = block.getState();
        if (!(state instanceof Sign)) {
            return; // block is not a sign
        }
        Sign sign = (Sign) state;
        if (args.length > 4){
            return;
        }
        for (int i = 0; args.length > i; i++) {
            sign.setLine(i, args[i]);
        }
        sign.update();
    }
}
