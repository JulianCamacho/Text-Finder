import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtReader {

    public static String current;

    public static void txtReader(String path) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                current += line;
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
            System.out.println(current);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

