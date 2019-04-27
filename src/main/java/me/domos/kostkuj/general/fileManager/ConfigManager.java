package me.domos.kostkuj.general.fileManager;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private ConfigManager() {}
    private static ConfigManager instance = new ConfigManager();
    public static ConfigManager getInstance() {
        return instance;
    }

    FileConfiguration config;
    FileConfiguration mute;
    FileConfiguration messages;
    File cfile;
    File mfile;
    File messagesFile;

    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "config.yml");
        config = p.getConfig();
        config.options().copyDefaults(true);
        p.saveDefaultConfig();
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }

        mfile = new File(p.getDataFolder(), "discordAuth.yml");

        if (!mfile.exists()) {
            try {
                mfile.createNewFile();
            }
            catch (IOException e) {
            }
        }
        mute = YamlConfiguration.loadConfiguration(mfile);

        messagesFile = new File(p.getDataFolder(), "messages.yml");

        if (!messagesFile.exists()) {
            try {
                messagesFile.createNewFile();
            }
            catch (IOException e) {
            }
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public FileConfiguration getDiscordAuth() {
        return mute;
    }

    public FileConfiguration getMessages(){
        return messages;
    }

    public void saveConfig() {
        try {
            config.save(cfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save Config.yml!");
        }
    }

    public void saveDiscordAuth() {
        try {
            mute.save(mfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save Config.yml!");
        }
    }

    public void saveMessages() {
        try {
            messages.save(messagesFile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save message.yml!");
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
        mute = YamlConfiguration.loadConfiguration(mfile);
    }

    public void reloadMessage() {
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public void reloadcfg() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }
}
