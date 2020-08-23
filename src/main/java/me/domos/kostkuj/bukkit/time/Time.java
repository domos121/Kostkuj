package me.domos.kostkuj.bukkit.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Time {

    public String unixToTime(int intime){
        if(intime == 0){
            return "Nikdy!";
        }
        Date date = new Date(intime*1000L);
        SimpleDateFormat parsetime = new SimpleDateFormat("E, dd.MM.yyyy HH:mm:ss");
        parsetime.setTimeZone(TimeZone.getTimeZone("Europe/Prague"));
        String outtime = parsetime.format(date);
        return outtime;
    }

    public String getTimeForTimer(long intime){
        Date date = new Date(intime*1000L);
        SimpleDateFormat parsetime = new SimpleDateFormat("HH-mm");
        parsetime.setTimeZone(TimeZone.getTimeZone("Europe/Prague"));
        String outtime = parsetime.format(date);
        return outtime;
    }

    public static java.sql.Timestamp getTimeDay(int day){
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime() + 1000 * 60 * 60 * 24 * day;
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
        return sqlTimestamp;
    }

    public static java.sql.Timestamp getTimeSec(int sec){
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime() + 1000 * sec;
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
        return sqlTimestamp;
    }

    public static java.sql.Timestamp getTimeSec(long sec){
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime() + 1000 * sec;
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
        return sqlTimestamp;
    }

    public java.sql.Timestamp unixToTimeStamp(int unix){
        Date date = new Date(unix*1000L);
        long javaTime = date.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
        return sqlTimestamp;
    }

    public boolean timeValueCheck(String value){
        ArrayList<String> obj = new ArrayList<String>();
        obj.add("s");
        obj.add("m");
        obj.add("h");
        obj.add("d");
        obj.add("w");
        obj.add("mon");
        obj.add("y");
        if (obj.contains(value)){
            return true;
        }
        return false;
    }

    public java.sql.Timestamp getTimeStumpFromString(String sdate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = format.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Timestamp stamp = new java.sql.Timestamp(date.getTime());
        return stamp;
    }

    public java.sql.Timestamp getTimeStumpFromString(String sdate, String dateformat){
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
        Date date = null;
        try {
            date = format.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Timestamp stamp = new java.sql.Timestamp(date.getTime());
        return stamp;
    }

    public long getTimeForTemp(long cas, String volba){
        long vysledek = -1;
        switch (volba)
        {
            case "s":
                vysledek = cas * 1;
                break;
            case "min":
                vysledek = cas * 60;
                break;
            case "h":
                vysledek = cas * 60 * 60;
                break;
            case "d":
                vysledek = cas * 60 * 60 * 24;
                break;
            case "w":
                vysledek = cas * 60 * 60 * 24 * 7;
                break;
            case "mon":
                vysledek = cas * 60 * 60 * 24 * 30;
                break;
            case "y":
                vysledek = cas * 60 * 60 * 24 * 365;
                break;
        }
        return vysledek;
    }

    public int unixTed(){
        Instant instant = Instant.now();
        Long unix = instant.toEpochMilli() / 1000;
        return Math.toIntExact(unix);
    }

    @Deprecated
    public String getTimeFromTimeStamp(Timestamp time){
        if (time == null){
            return "Nikdy";
        }
        Date date = new Date(time.getTime());
        SimpleDateFormat parsetime = new SimpleDateFormat("E, dd.MM.yyyy HH:mm:ss");
        parsetime.setTimeZone(TimeZone.getTimeZone("Europe/Prague"));
        String outtime = parsetime.format(date);
        return outtime;
    }

    public String getDayTime(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat parsetime = new SimpleDateFormat("[HH:mm:ss]");
        parsetime.setTimeZone(TimeZone.getTimeZone("Europe/Prague"));
        String outtime = parsetime.format(date);
        return outtime;
    }

    public String minuteToTime(int minute){
        int hour = 0;
        int day = 0;
        int mounth = 0;
        int min = 0;
        int sec = 0;


        while (minute >= 20*60*60*24*30) {
            mounth++;
            minute = minute - 20*60*60*24*30;
        }

        while (minute >= 20*60*60*24){
            day++;
            minute = minute - 20*60*60*24;
        }

        while (minute >= 20*60*60){
            hour++;
            minute = minute - 20*60*60;
        }

        while (minute >= 20*60){
            min++;
            minute = minute - 20*60;
        }

        while (minute >= 20){
            sec++;
            minute = minute - 20;
        }

        return mounth + "M, " + day + "D, " + hour + "h, " + min + "m, " + sec + "s, " + minute + "t";
    }

    public String minuteToTime(long minute){
        int hour = 0;
        int day = 0;
        int mounth = 0;

        while (minute >= 60*60*24*30) {
            mounth++;
            minute = minute - 60*60*24*30;
        }

        while (minute >= 60*60*24){
            day++;
            minute = minute - 60*60*24;
        }

        while (minute >= 60*60){
            hour++;
            minute = minute - 60*60;
        }

        return mounth + "M, " + day + "D, " + hour + "h, " + minute + "m";
    }

    public String getMonth(int month){
        switch (month) {
            case 1:
                return "leden";
            case 2:
                return "únor";
            case 3:
                return "březen";
            case 4:
                return "duben";
            case 5:
                return "květen";
            case 6:
                return "červen";
            case 7:
                return "červenec";
            case 8:
                return "srpen";
            case 9:
                return "září";
            case 10:
                return "říjen";
            case 11:
                return "listopad";
            case 12:
                return "prosinec";
        }
        return null;
    }

}
