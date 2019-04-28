package me.domos.kostkuj.general.fileManager;

import java.util.ArrayList;
import java.util.List;

public class ConfigVote {

    private ConfigVote() {}

    static ConfigVote instance = new ConfigVote();

    public static ConfigVote getInstance() {
        return instance;
    }

    private static List<String> items;

    public void setCfg(){
        List<String> preklad = ConfigManager.CONFIG.getConfig().getStringList("vote-items");
        List<String> get_items = new ArrayList<String>();
        for (int i = 0; preklad.size() > i; i++){
            String[] percenta = preklad.get(i).split("%");
            for (int pe = 0; pe < Integer.parseInt(percenta[1]); pe++){
                get_items.add(percenta[0]);
            }
        }
        ConfigVote.items = get_items;
    }

    public List<String> getItems() {
        return items;
    }
}
