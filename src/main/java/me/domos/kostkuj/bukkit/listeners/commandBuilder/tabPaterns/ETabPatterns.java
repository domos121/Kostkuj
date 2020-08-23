package me.domos.kostkuj.bukkit.listeners.commandBuilder.tabPaterns;

public enum ETabPatterns {
    ONLINEPLAYERS("{online_players}", new OnlinePlayersPattern()),
    CRATEKEYS("{crate_keys}", new CrateKeysPattern()),
    ITEMBUILDER("{item_builder}", new ItemBuilderPattern()),
    ANYTHING("{anything}", new ItemBuilderPattern());

    private String argumentName;
    private ITabPattern tabPattern;

    ETabPatterns(String argumentName, ITabPattern tabPattern){
        this.argumentName = argumentName;
        this.tabPattern = tabPattern;
    }

    public String getArgumentName() {
        return argumentName;
    }

    public ITabPattern getTabPattern() {
        return tabPattern;
    }

    public static ETabPatterns getPaternFromArgument(String arg){
        for (ETabPatterns patern : ETabPatterns.values()){
            if (patern.getArgumentName().equalsIgnoreCase(arg)){
                return patern;
            }
        }
        return null;
    }

    public static boolean isPattern(String argument){
        boolean isPattern = false;
        for (ETabPatterns pattern : ETabPatterns.values()){
            if (argument.equalsIgnoreCase(pattern.getArgumentName())){
                isPattern = true;
            }
        }
        return isPattern;
    }
}
