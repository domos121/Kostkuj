package me.domos.kostkuj.models.voteModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.domos.kostkuj.bukkit.sign.WriteOnSign;
import me.domos.kostkuj.bukkit.skull.SetSkull;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.urlReader.UrlRead;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

public class VoteBoard {

    private Location baseLocation;
    private BlockFace blockFace;

    private List<Location> sign = new ArrayList<>();
    private List<Location> head = new ArrayList<>();
    private Time time = new Time();

    public VoteBoard(Location baseLocation, BlockFace blockFace){
        this.baseLocation = baseLocation;
        this.blockFace = blockFace;
        this.setLocation();
    }

    public void setScoreBoard(){
        JsonObject jsonServerInfo = new UrlRead().urlRead("https://czech-craft.eu/api/server/kostkuj/");

        JsonObject jsonVoters = new UrlRead().urlRead("https://czech-craft.eu/api/server/kostkuj/voters/");
        JsonArray jsonArray = jsonVoters.getAsJsonArray("data");
        WriteOnSign writeOnSign = new WriteOnSign();
        writeOnSign.writeOnSign(baseLocation, "§6TabuleHlasujících","§7Pozice: §a" + jsonServerInfo.get("position").getAsString(),"§7Celkem hlasů: ", "§a" + jsonServerInfo.get("votes").getAsString());
        SetSkull setSkull = new SetSkull();
        for (int i = 0; this.sign.size() > i; i++){
            JsonObject jsonVoters2 = jsonArray.get(i).getAsJsonObject();
            String username = jsonVoters2.get("username").getAsString();
            String hlasu = jsonVoters2.get("votes").getAsString();
            writeOnSign.writeOnSign(sign.get(i), "§7---- §a(" +  Math.addExact(i, 1) + ") §7----", "§a" + username,"§7počet hlasů:", "§a" + hlasu);
            setSkull.setSkull(head.get(i), username, this.blockFace);
        }
    }

    public void setTopVotersBoard(JsonObject json){
        WriteOnSign writeOnSign = new WriteOnSign();
        SetSkull setSkull = new SetSkull();
        String month = time.getMonth(json.get("month").getAsInt());
        writeOnSign.writeOnSign(baseLocation, "§6Kostkuj.CZ", "§7Top hlasující", "§7za měsíc:","§a" + month);
        JsonArray jsonArray = json.getAsJsonArray("data");
        for (int i = 0; this.sign.size() > i; i++){
            JsonObject jsonVoters2 = jsonArray.get(i).getAsJsonObject();
            String username = jsonVoters2.get("username").getAsString();
            String hlasu = jsonVoters2.get("votes").getAsString();
            writeOnSign.writeOnSign(sign.get(i), "§7---- §a(" +  Math.addExact(i, 1) + ") §7----", "§a" + username,"§7počet hlasů:", "§a" + hlasu);
            setSkull.setSkull(head.get(i), username, this.blockFace);
        }
    }

    private void setLocation(){
        if (this.blockFace == BlockFace.WEST){
            this.faceOnWest();
        } else if (this.blockFace == BlockFace.EAST){
            this.faceOnEast();
        } else if (this.blockFace == BlockFace.NORTH){
            this.faceOnNorth();
        } else if (this.blockFace == BlockFace.SOUTH){
            this.faceOnSouth();
        }
    }

    private void faceOnWest(){
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()-1));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()+1));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()-1));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()+1));

    }

    private void faceOnEast(){
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()+1));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()-1));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()+1));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()-1));

    }

    private void faceOnNorth(){
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()+1, baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()-1, baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()+1, baseLocation.getBlockY() -1, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()-1, baseLocation.getBlockY() -1, baseLocation.getBlockZ()));

    }

    private void faceOnSouth(){
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()-1, baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.sign.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()+1, baseLocation.getBlockY() -2, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()-1, baseLocation.getBlockY() -1, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX(), baseLocation.getBlockY() -1, baseLocation.getBlockZ()));
        this.head.add(new Location(baseLocation.getWorld(), baseLocation.getBlockX()+1, baseLocation.getBlockY() -1, baseLocation.getBlockZ()));
    }


}
