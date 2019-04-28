package me.domos.kostkuj.bukkit.listeners.events;

import com.vexsoftware.votifier.model.VotifierEvent;
import me.domos.kostkuj.models.voteModel.Vote;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Event_Vote implements Listener {

    Vote v = Vote.getInstance();

    @EventHandler
    public void onPlayerVote(VotifierEvent e) {
        v.VoteIsOnline(e.getVote().getUsername());
    }
}
