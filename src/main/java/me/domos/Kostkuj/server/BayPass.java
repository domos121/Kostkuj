package me.domos.Kostkuj.server;

import me.domos.Kostkuj.server.luckperms.GetUserGroup;

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