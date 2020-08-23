package me.domos.kostkuj.general.connect.email;

import me.domos.kostkuj.general.connect.urlReader.UrlRead;

public class BanEmail {

    public static void send(int ban_id, int user_id) {

        String a = "https://api.kostkuj.cz/game-request/ban-email/" + ban_id + "/user/" + user_id;

        new UrlRead().urlRead(a);

    }


}
