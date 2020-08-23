package me.domos.kostkuj.models.voteModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.discord.DiscordConnect;
import me.domos.kostkuj.general.connect.mysql.CraftCoin.MCraftCoins;
import me.domos.kostkuj.general.connect.mysql.vote.MysqlListener;
import me.domos.kostkuj.general.connect.urlReader.UrlRead;
import org.bukkit.block.BlockFace;

import javax.annotation.Nonnull;
import java.util.*;

public class VoteTopPlayers {

    public static String day = Time.getTimeDay(0).toString().substring(8,10);
    public static String lastMonth = Time.getTimeDay(Integer.parseInt(day)*(-1)).toString().substring(5,7).replace("-", "/");
    public static String keyLastMonth = Time.getTimeDay(Integer.parseInt(day)*(-1)).toString().substring(0,7).replace("-", "/");

    private Time time = new Time();

    private UrlRead urlRead = new UrlRead();
    private MysqlListener mysqlListener = new MysqlListener();

    public JsonObject voteTopPlayers(@Nonnull String keyLastMounth, @Nonnull int size){
        Map<String, Integer> voters = new HashMap<>();
        JsonObject jsonObject = urlRead.urlRead("https://czech-craft.eu/api/server/kostkuj/votes/" + keyLastMounth + "/");
        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();

        for (int i = 0; jsonArray.size() > i; i++) {
            String username = jsonArray.get(i).getAsJsonObject().get("username").getAsString();
            if (voters.containsKey(username)) {
                int vote = voters.get(username);
                voters.remove(username);
                voters.put(username, vote + 1);
            } else {
                voters.put(username, 1);
            }
        }
        // SORT MAP BY VALUE
        Map<String, Integer> sortVoters = new LinkedHashMap<>();
        voters.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> sortVoters.put(x.getKey(), x.getValue()));

        JsonArray jsonArrayUsers = new JsonArray();
        JsonObject jsonObjectEnd = new JsonObject();
        if (size > sortVoters.size()){
            size = sortVoters.size();
        }
        for (int i = 0; size > i; i++) {
            String username = sortVoters.keySet().toArray()[i].toString();
            String votes = sortVoters.get(sortVoters.keySet().toArray()[i]).toString();
            JsonObject JsonObjectUser = new JsonObject();
            JsonObjectUser.addProperty("username", username);
            JsonObjectUser.addProperty("votes", votes);
            jsonArrayUsers.add(JsonObjectUser);
        }
        jsonObjectEnd.add("data", jsonArrayUsers);
        jsonObjectEnd.addProperty("month", keyLastMounth.substring(5,7));
        return jsonObjectEnd;
    }

    public void setTopPlayers(String month){
        if (day.equalsIgnoreCase(month)) {
            if (!mysqlListener.isTopVotersSet()) {
                JsonObject jsonObject = voteTopPlayers(keyLastMonth, 3);
                mysqlListener.setTopVoters(jsonObject);
                setReward(jsonObject);
                VoteTopVotersBoardSettings voteTopVotersBoardSettings = new VoteTopVotersBoardSettings();
                VoteBoard board = new VoteBoard(voteTopVotersBoardSettings.getBaseLocation(), BlockFace.valueOf(voteTopVotersBoardSettings.getBlockFace().toUpperCase()));
                board.setTopVotersBoard(jsonObject);
            }
        }
    }


    public void setReward(JsonObject jsonObject){
        String msg = "Vyhlášení Top 3 Hlasujících pro server Kostkuj.cz za měsíc {month}.\n1. {username0} s {votes0} hlasy, obdržel {reward0}cc.\n2. {username1} s {votes1} hlasy, obdržel {reward1}cc.\n3. {username2} s {votes2} hlasy, obdržel {reward2}cc.\nDěkujeme všem hlasujícím, za držení {position}. pozice na\n https://czech-craft.eu/server/kostkuj/";
        JsonObject serverInfo = new UrlRead().urlRead("https://czech-craft.eu/api/server/kostkuj/");
        String position = serverInfo.get("position").getAsString();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        List<Integer> rewardCount = new ArrayList<>();
        rewardCount.add(1000);
        rewardCount.add(500);
        rewardCount.add(250);
        msg = msg.replace("{month}", time.getMonth(jsonObject.get("month").getAsInt())).replace("{position}", position);
        for (int i = 0 ; jsonArray.size() > i; i++){
            JsonObject jsonObjectUsers = jsonArray.get(i).getAsJsonObject();
            String username = jsonObjectUsers.get("username").getAsString();
            String votes = jsonObjectUsers.get("votes").getAsString();
            msg = msg.replace("{username" + i + "}", username).replace("{votes" + i + "}", votes).replace("{reward" + i + "}", rewardCount.get(i).toString());
            new MCraftCoins().setCC(username, rewardCount.get(i), 11, true);
        }
        DiscordConnect.sendMsg(msg, "text_infolinka");
    }
}
