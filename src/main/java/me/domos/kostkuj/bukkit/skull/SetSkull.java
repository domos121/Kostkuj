package me.domos.kostkuj.bukkit.skull;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;

import java.util.UUID;

public class SetSkull {

    public boolean setSkull(Location loc, String name, BlockFace blockFace){
        Block skullBlock = loc.getBlock();
        skullBlock.setType(Material.PLAYER_WALL_HEAD);
        BlockState state = skullBlock.getState();
        Skull skull = (Skull) state;
        UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
        skull.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(uuid));
        skull.setRotation(blockFace);
        skull.update();
        return true;
    }
}
