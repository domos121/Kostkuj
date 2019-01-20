package me.domos.Kostkuj.server.player.ipmanager;

public class IpHasher {

    private IpHasher() {}
    static IpHasher instance = new IpHasher();
    public static IpHasher getInstance() {
        return instance;
    }

    public String hashIp(String ip){
        String hasip = "";
        //155.456.465.458
        //2001:0db8:0000:0000:0000:0000:1428:57ab
        String[] poleIP = ip.split("");
        String[] onlyNumber = nuberReplace(poleIP);

         //  x   a   v    /   /  s    b   s   }   r   h    t   t
        //   9,  2,  18,  4,  4, 8,  22,  8, 14,  16, 26,  20, 20

         // ?    x   $   #   z   e   $   ř   e   }   d    í     }    c   v
         // -0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14
        //   1,  9,  7,  19, 5   10  7   23, 10 14   13   27   14   13   18

        // 185.151.253.204
        // 91.103.167.98
        for (int i = 0; onlyNumber.length > i; i++){
            hasip = hasip + onlyNumber[i].replace(onlyNumber[i], hashMap(Integer.parseInt(onlyNumber[i]) + i));
            //hasip = hasip + onlyNumber[i];
        }

        return hasip;
    }

    public String hashMap(int id){
        int[]  pozice = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        String[] map = {"q","?","a","]","/","z","w","$","s","x","e","(","d","c","}","[","r","f","v","#","t","g","b","ř","y","ě","h","í","n","u","%","^","&","*","j","m","i","k","!","@",")","{","+","-","ň","ů","ú","š","č","ž","ý","á","é","ĺ","|",";","_","=","<",">"};
        return map[id];
    }

    public String[] nuberReplace(String[] poleip){
        for(int i = 0; poleip.length > i; i++){
            poleip[i] = poleip[i]
                    .replace("a", "10")
                    .replace("b", "11")
                    .replace("c", "12")
                    .replace("d", "13")
                    .replace("e", "14")
                    .replace("f", "15")
                    .replace(".", "16")
                    .replace(":","17");
        }
        return poleip;
    }

}
