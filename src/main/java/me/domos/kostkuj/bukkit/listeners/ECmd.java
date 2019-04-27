package me.domos.kostkuj.bukkit.listeners;

public enum ECmd {

    // KOTKUJ
    KOSTKUJ("", ""),
    KOSTKUJ_JOIN_SLIENT("", ""),
    KOSTKUJ_RESTART("kostkuj restart", "restart"),
    KOSTKUJ_SAVE("kostkuj save", "save"),
    KOSTKUJ_STOP("kostkuj stop", "stop"),
    KOSTKUJ_POZEMEK("kostkuj pozemek", "pozemek"),
    KOSTKUJ_TOPTIME("kostkuj toptime", "toptime"),
    KOSTKUJ_DEATH("kostkuj death", "death"),
    KOSTKUJ_DEATH_OTHER("kostkuj death <user>", "death"),
    KOSTKUJ_PARTICLE("kostkuj particle", "particle"),
    KOSTKUJ_PARTICLE_STOP("kostkuj particle stop", "stop"),
    KOSTKUJ_PARTICLE_START("kostkuj particle start", "start"),
    KOSTKUJ_PARTICLE_SHAPE("kostkuj particle shape", "shape"),
    KOSTKUJ_PARTICLE_TYP("kostkuj particle typ", "typ"),
    KOSTKUJ_DISCORDAUTH("kostkuj discordauth", "discordauth"),
    KOSTKUJ_COMMADBLOCKLIST("kostkuj commandblocklist", "commandblocklist"),
    KOSTKUJ_WRITER("kostkuj writer", "writer"),
    KOSTKUJ_NYNIJEAFK("kostkuj nynijeafk", "nynijeafk"),
    // STAVBA
    STAVBA("stavba","stavba"),
    STAVBA_HELP("stavba help","help"),
    STAVBA_GET("stavba get","get"),
    STAVBA_CREATE("stavba create","create"),
    STAVBA_ADD("stavba add","add"),
    STAVBA_START("stavba start","start"),
    STAVBA_CLOSE("stavba close","close"),
    STAVBA_VOTE("stavba","stavba"),
    // PLAYERINFO
    PLAYERINFO("playerInfo", "playerInfo"),
    PLAYERINFO_OTHER("", ""),
    // VOTEMUTE
    VOTEMUTE("voteMute", "voteMute"),
    VOTEMUTE_CREATE("voteMute <username>", ""),
    VOTEMUTE_VOTE("voteMute", "voteMute"),
    VOTEMUTE_GET("voteMute get:", "get:"),
    VOTEMUTE_HELP("voteMute help:", "help:"),
    // VOUCHER
    VOUCHER("voucher", "voucher"),
    VOUCHER_GET("voucher get", "get"),
    VOUCHER_ADD("voucher create", "create"),
    VOUCHER_USE("voucher use", "use"),
    VOUCHER_HELP("voucher help", "help"),
    VOUCHER_GIFT("voucher gift", "gift"),
    // DOMOS
    DOMOS("domos", "domos");

    private String command;
    private String lastarg;

    ECmd(String command, String lastarg){
        this.command = command;
        this.lastarg = lastarg;
    }

    public String getPerm(){
        String perm = this.toString().toLowerCase().replace("_", ".");
        if (perm.contains("kostkuj")){
            return perm;
        }
        return "kostkuj." + perm;
    }

    public String getCmd(){
        return command;
    }

    public String getLastarg(){
        return lastarg;
    }

}
