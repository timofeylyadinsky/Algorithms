package lt.timofey.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reader {
    public static ArrayList<String> reader(String filePath){
        try {

            List<String> listOfStrings = new ArrayList<String>();

            Scanner sc = new Scanner(
                    new FileReader( "src/main/resources/" + filePath)
            );

            String str;

            while (sc.hasNext()) {
                str = sc.next();
                listOfStrings.add(str);
            }

            ArrayList<String> res = new ArrayList<>(Arrays.stream(listOfStrings.get(0).split("")).toList());
            res.add("L");
            res.add(0,"L");
//            System.out.println(res);
//            res.set(0,"1");
//            System.out.println(res);
            return res;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
