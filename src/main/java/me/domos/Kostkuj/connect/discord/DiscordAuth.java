package me.domos.Kostkuj.connect.discord;

import java.util.ArrayList;
import java.util.List;

public class DiscordAuth {

        public static List<String> discordIdList = new ArrayList<>();

        public static boolean isIdExist(String id){
            try {
                if (discordIdList.contains(id)) return true;
            } catch (Exception e){

            }
            return false;
        }
}
