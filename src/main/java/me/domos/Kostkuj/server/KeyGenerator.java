package me.domos.Kostkuj.server;

import java.util.Random;

public class KeyGenerator {

    public String getSerialKey(int leght){
        leght = leght - 1;
        String[] key = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0"};
        Random r = new Random();
        String serialkey = "";
        for (int i = 0; i <= leght; i++){
            int word = r.nextInt(35);
            serialkey = serialkey + key[word];
        }
        return serialkey.toUpperCase();
    }

}
