package me.domos.kostkuj.bukkit.chat.manage;

import me.domos.kostkuj.general.luckpermsData.GetUserGroup;

public class BayPass {

    GetUserGroup gup = new GetUserGroup();

    public BayPass() {}
    static BayPass instance = new BayPass();
    public static BayPass getInstance() {
        return instance;
    }

    public boolean isPovolenoWeight(String kdo, String komu){
        int ikdo = gup.getWeight(kdo);
        int ikomu = gup.getWeight(komu);
        return ikdo > ikomu;
    }
}