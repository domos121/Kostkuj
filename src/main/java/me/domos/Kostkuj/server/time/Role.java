package me.domos.Kostkuj.server.time;

public class Role {

    public static int getPriority(String role){
        switch (role){
            case "console":
                return 1000;
            case "majitel":
                return 1000;
            case "admin":
                return 100;
            case "helper":
                return 90;
            case "hlmoderator":
                return 80;
            case "moderator":
                return 60;
            case "testmoderator":
                return 40;
            case "eventer":
                return 30;
            case "dozvip":
                return 20;
            case "vip":
                return 10;
            case "default":
                return 0;
        }
        return 0;
    }
}
