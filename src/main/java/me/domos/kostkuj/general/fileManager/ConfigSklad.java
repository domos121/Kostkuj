package me.domos.kostkuj.general.fileManager;


import java.util.List;

public class ConfigSklad {

    private static List<String> sklads;

    public void setCfg(){
        ConfigSklad.sklads = ConfigManager.SKLAD.getConfig().getStringList("chests");
    }

    public List<String> getSklads(){
        return sklads;
    }
}
