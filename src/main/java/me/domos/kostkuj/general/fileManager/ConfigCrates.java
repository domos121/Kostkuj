package me.domos.kostkuj.general.fileManager;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigCrates {

    private ConfigCrates() {}

    static ConfigCrates instance = new ConfigCrates();

    public static ConfigCrates getInstance() {
        return instance;
    }

    private static List<String> key;
    private static List<String> chests;
    private static List<String> name;
    private static HashMap<String, String> mapKeys;
    private static HashMap<String, String> mapName;
    private static HashMap<String, String> mapNameInvert;
    private static HashMap<String,List<String>> mapItems;

    public void setCfg() {
            List<String> get_chests = new ArrayList<String>();
            List<String> get_keys = new ArrayList<String>();
            List<String> get_name = new ArrayList<String>();
            HashMap<String, String> get_mapKeys = new HashMap();
            HashMap<String, String> get_mapName = new HashMap();
            HashMap<String, String> get_mapNameInvert = new HashMap();
            HashMap<String, List<String>> get_mapItems = new HashMap();
            for (String key : ConfigManager.CRATES.getConfig().getConfigurationSection("chests").getKeys(false)) {
                Bukkit.getServer().getConsoleSender().sendMessage("§4" + key);
                get_keys.add(ConfigManager.CRATES.getConfig().getString("chests." + key + ".key"));
                get_name.add(ConfigManager.CRATES.getConfig().getString("chests." + key + ".name"));
                get_chests.add(key);
                get_mapKeys.put(key, ConfigManager.CRATES.getConfig().getString("chests." + key + ".key"));
                get_mapName.put(key, ConfigManager.CRATES.getConfig().getString("chests." + key + ".name"));
                get_mapNameInvert.put(ConfigManager.CRATES.getConfig().getString("chests." + key + ".name"), key);
                get_mapItems.put(key, ConfigManager.CRATES.getConfig().getStringList("chests." + key + ".items"));
            }

            ConfigCrates.name = get_name;
            ConfigCrates.key = get_keys;
            ConfigCrates.chests = get_chests;
            ConfigCrates.mapKeys = get_mapKeys;
            ConfigCrates.mapName = get_mapName;
            ConfigCrates.mapNameInvert = get_mapNameInvert;
            ConfigCrates.mapItems = get_mapItems;
    }

    public List<String> getName() {
        return ConfigCrates.name;
    }

    public List<String> getKeys(){
        return ConfigCrates.key;
    }

    public List<String> getChests(){
        return ConfigCrates.chests;
    }

    public HashMap<String, String> getMapKeys() {
        return mapKeys;
    }

    public HashMap<String, String> getMapName() {
        return mapName;
    }

    public HashMap<String, String> getMapNameInvert() {
        return mapNameInvert;
    }

    public HashMap<String, List<String>> getMapItems() {
        return mapItems;
    }
}
