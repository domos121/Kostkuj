package me.domos.Kostkuj.server.projecty;

import me.domos.Kostkuj.connect.MySQL.MySQL;
import me.domos.Kostkuj.server.time.Time;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Projekt {

    MySQL mySQL = new MySQL();
    Time time = new Time();

    private int id;
    private String jmeno;
    private String create_user;
    private String allow_user;
    private String create_date = "Nikdy";
    private String allow_date = "Nikdy";
    private int complete;

    public Projekt(String user, String projekt){
        if (mySQL.isConected()) {
            try {
                PreparedStatement ps = mySQL.getStatement("SELECT project.id, project.jmeno, project.create_date, project.allow_date, project.complete, user.username, user2.username FROM game_kostkuj_projects project INNER JOIN web_users user ON project.user_id = user.id LEFT JOIN web_users user2 ON project.allow_user_id = user2.id WHERE user.username = ? AND project.jmeno = ?");
                ps.setString(1, user);
                ps.setString(2, projekt);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this.id = rs.getInt("project.id");
                    this.jmeno = projekt;
                    this.create_user = user;
                    this.allow_user = rs.getString("user2.username");
                    if (rs.getTimestamp("project.create_date") != null){
                        Timestamp cdate = rs.getTimestamp("project.create_date");
                        this.allow_date = time.getTimeFromTimeStamp(cdate);
                    } else {
                        this.allow_date = "Nikdy";
                    }
                    if (rs.getTimestamp("project.allow_date") != null){
                        Timestamp adate = rs.getTimestamp("project.allow_date");
                        this.create_date = time.getTimeFromTimeStamp(adate);
                    } else {
                        this.allow_date = "Nikdy";
                    }
                    this.complete = rs.getInt("project.complete");
                }
            } catch (SQLException e) {
                Bukkit.getServer().broadcastMessage(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getAllow_date() {
        return allow_date;
    }

    public String getAllow_user() {
        return allow_user;
    }

    public String getCreate_user() {
        return create_user;
    }

    public String getJmeno() {
        return jmeno;
    }

    public int getComplete() {
        return complete;
    }
}
