package me.domos.kostkuj.models.voteModel;

import me.domos.kostkuj.general.fileManager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class VoteTopVotersBoardSettings {

    private boolean allow;
    private String location;
    private String blockFace;

    public VoteTopVotersBoardSettings() {
        ConfigManager config = ConfigManager.CONFIG;
        this.allow = config.getConfig().getBoolean("TopVotersBoard.allow");
        this.location = config.getConfig().getString("TopVotersBoard.position");
        this.blockFace = config.getConfig().getString("TopVotersBoard.BlockFace");
        if (this.location == null ||  this.blockFace == null){
            this.allow = false;
        }
    }

    public Location getBaseLocation(){
        String[] loc = this.location.split(",");
        return new Location(Bukkit.getWorld(loc[0]), Integer.parseInt(loc[1]), Integer.parseInt(loc[2]), Integer.parseInt(loc[3]));
    }

    public String getBlockFace() {
        return blockFace;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAllow() {
        return allow;
    }

}
