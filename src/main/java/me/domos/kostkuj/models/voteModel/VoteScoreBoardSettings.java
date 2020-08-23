package me.domos.kostkuj.models.voteModel;

import me.domos.kostkuj.general.fileManager.ConfigManager;

public class VoteScoreBoardSettings {

    private boolean allow;
    private String location;
    private String blockFace;

    public VoteScoreBoardSettings(){
        ConfigManager config = ConfigManager.CONFIG;
        this.allow = config.getConfig().getBoolean("ScoreBoard.allow");
        this.location = config.getConfig().getString("ScoreBoard.position");
        this.blockFace = config.getConfig().getString("ScoreBoard.BlockFace");
        if (this.location == null ||  this.blockFace == null){
            this.allow = false;
        }
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
