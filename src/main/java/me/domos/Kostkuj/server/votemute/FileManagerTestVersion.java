package me.domos.Kostkuj.server.votemute;

import me.domos.Kostkuj.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class FileManagerTestVersion {

        private String fileName;
        private String fileRoad;
        private boolean copyDefault;
        private FileConfiguration fileConfiguration;
        private File file;

        private Plugin plug = Main.plugin;


        public FileManagerTestVersion(String name, String road, boolean copyDefault){
            this.fileName = name + ".yml";
            this.fileRoad = road;
            this.copyDefault = copyDefault;
            this.setup();
        }

        private void setup(){
            this.file = new File(this.plug.getDataFolder(), this.fileName);
            this.fileConfiguration = this.plug.getConfig();
            this.fileConfiguration.options().copyDefaults(copyDefault);
            this.plug.saveDefaultConfig();
            if (!this.plug.getDataFolder().exists()) {
                this.plug.getDataFolder().mkdir();
            }
        }


}
