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
    PLAYERINFO("playerinfo", "playerinfo"),
    PLAYERINFO_OTHER("", ""),
    PLAYERINFO_MORE("playerinfo more", ""),
    // VOTEMUTE
    VOTEMUTE("votemute", "voteMute"),
    VOTEMUTE_CREATE("votemute <username>", ""),
    VOTEMUTE_VOTE("votemute", "voteMute"),
    VOTEMUTE_GET("votemute get:", "get:"),
    VOTEMUTE_HELP("votemute help:", "help:"),
    // VOUCHER
    VOUCHER("voucher", "voucher"),
    VOUCHER_GET("voucher get", "get"),
    VOUCHER_ADD("voucher create", "create"),
    VOUCHER_USE("voucher use", "use"),
    VOUCHER_HELP("voucher help", "help"),
    VOUCHER_GIFT("voucher gift", "gift"),
    // CRATE
    CRATE("crate", "crate"),
    CRATE_RELOAD("crate reload", "reload"),
    CRATE_HELP("crate help", "help"),
    CRATE_CREATEKEY("crate createkey", "createkey"),
    CRATE_EDITLOCATION("crate editlocation", "editlocation"),
    // VOTE
    VOTE("vote", "vote"),
    VOTE_TEST("vote test", "test"),
    VOTE_HELP("vote help", "help"),
    VOTE_SKLAD("vote sklad", "sklad"),
    VOTE_SKLAD_OTHER("vote sklad [user]", "sklad"),
    // USECODE
    USECODE("usecode", "usecode"),
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
