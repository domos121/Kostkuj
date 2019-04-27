package me.domos.kostkuj.general.connect.mysql.player;

import me.domos.kostkuj.bukkit.time.Time;
import me.domos.kostkuj.general.connect.mysql.CraftCoin.MCraftCoins;
import me.domos.kostkuj.general.connect.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MPlayerInfo {

    private MySQL mySQL = new MySQL();
    private Time time = new Time();
    private MCraftCoins mCraftCoins = new MCraftCoins();

    private int user_id;
    private int cc;
    private String registerdate;
    private String lastlogin;
    private int enabled;
    private double position_x;
    private double posotion_y;
    private double position_z;
    private String world;

    public MPlayerInfo(String user){
        if (mySQL.isConected()){
            PreparedStatement ps = mySQL.getStatement("SELECT * FROM web_users WHERE username = '" + user + "'");
            try {
                this.enabled = 0;
                this.position_x = 0;
                this.posotion_y = 0;
                this.position_z = 0;
                this.world = "world";
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    this.user_id = rs.getInt("id");
                    this.enabled = rs.getInt("enabled");
                    if (rs.getTimestamp("register_date") == null){
                        this.registerdate = "Nikdy";
                    } else {
                        this.registerdate = time.getTimeFromTimeStamp(rs.getTimestamp("register_date"));
                    }
                    if (rs.getTimestamp("last_login") == null){
                        this.lastlogin = "Nikdy";
                    } else {
                        this.lastlogin = time.getTimeFromTimeStamp(rs.getTimestamp("last_login"));
                    }
                    this.position_x = rs.getFloat("position_x");
                    this.posotion_y = rs.getFloat("position_y");
                    this.position_z = rs.getFloat("position_z");
                    this.world = rs.getString("world");
                }
                this.cc = mCraftCoins.getCC(user);
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public int getUser_id() {
        return user_id;
    }

    public int getEnabled() {
        return enabled;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public int getCc() {
        return cc;
    }

    public double getPosition_x() {
        return position_x;
    }

    public double getPosotion_y() {
        return posotion_y;
    }

    public double getPosition_z() {
        return position_z;
    }

    public String getWorld() {
        return world;
    }
}
