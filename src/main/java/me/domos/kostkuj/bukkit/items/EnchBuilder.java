package me.domos.kostkuj.bukkit.items;

import java.util.ArrayList;

public class EnchBuilder {

    private EnchBuilder() {}
    static EnchBuilder instance = new EnchBuilder();
    public static EnchBuilder getInstance() {
        return instance;
    }

    // mending,1;unbreaking,5;

    public String[][] ench(String ench){
        String[] radekEnch = ench.split(";");
        // mending,1
        // unbreaking,5
        ArrayList<String> arrayEnch = new ArrayList<String>();

        for (int u = 0; u < radekEnch.length; u++){
            arrayEnch.add(radekEnch[u]);
        }
        String[][] poleEnch = new String[arrayEnch.size()][];
        for (int i = 0; i < arrayEnch.size(); i++){
            poleEnch[i] = arrayEnch.get(i).split(",");
        }
        return poleEnch;
    }
}
