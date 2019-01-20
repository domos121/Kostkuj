package by.domos.Kostkuj.server.votemute;

import java.util.ArrayList;
import java.util.List;

public class VoteMuteSettings {

    private static boolean active = false;

    private static String user;

    private static String activated;

    private static List<String> vote = new ArrayList<>();

    private static int muteId;

    public boolean addVote(String user){
        if (vote.contains(user)){
            return false;
        } else {
            vote.add(user);
            return true;
        }
    }

    public boolean clearVote(){
        vote.clear();
        return true;
    }

    public void setActiv(boolean active, String user, String aktivated, int id){
        VoteMuteSettings.active = active;
        VoteMuteSettings.user = user;
        VoteMuteSettings.activated = aktivated;
        VoteMuteSettings.muteId = id;
    }

    public boolean isActive(){
        return VoteMuteSettings.active;
    }

    public boolean getAcive(){
        return VoteMuteSettings.active;
    }

    public String getActivated() {
        return activated;
    }

    public int getMuteId() {
        return muteId;
    }

    public List<String> getList(){
        return VoteMuteSettings.vote;
    }

    public String getUser(){
        return user;
    }


}
