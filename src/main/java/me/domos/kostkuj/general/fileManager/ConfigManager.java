package me.domos.kostkuj.general.fileManager;

import me.domos.kostkuj.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public enum ConfigManager {
    CONFIG(new File(Main.plugin.getDataFolder(), "config.yml")),
    DISCORD(new File(Main.plugin.getDataFolder(), "discordAuth.yml")),
    MESSAGES(new File(Main.plugin.getDataFolder(), "messages.yml")),
    SKLAD(new File(Main.plugin.getDataFolder(), "sklad.yml")),
    CRATES(new File(Main.plugin.getDataFolder(), "crates.yml"));

    private File file;
    private FileConfiguration fileConfiguration;

    ConfigManager(File file){
        FileConfiguration fileConfiguration;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage("Kontrola existujícího souboru.");
        }
        if(!file.getName().equalsIgnoreCase("config.yml")){
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
        } else {
            fileConfiguration = Main.plugin.getConfig();
        }

        fileConfiguration.options().copyHeader(true);

        this.fileConfiguration = fileConfiguration;
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return fileConfiguration;
    }

    public void reloadConfig() {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.options().copyHeader(true);
        this.fileConfiguration = fileConfiguration;
    }

    public void saveConfig(){
        try {
            getConfig().save(getFile());
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public static void setup(Plugin p){
        for (ConfigManager cfgManager : ConfigManager.values()) {
            Bukkit.getServer().getConsoleSender().sendMessage(cfgManager.getFile().getPath() + " file loaded!");
        }
    }
}
