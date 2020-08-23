package me.domos.kostkuj.general.connect.urlReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlRead {

    public JsonObject urlRead(String surl){
        JsonObject jsonObject = new JsonObject();
        JsonParser parser = new JsonParser();

        try {
            // get URL content
            URL url = new URL(surl);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                jsonObject = (JsonObject) parser.parse(inputLine);
            }
            br.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





        return jsonObject;
    }
}
