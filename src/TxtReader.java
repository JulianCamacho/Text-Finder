import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Esta clase extrae el teto de documentos txt
 */
public class TxtReader {

    /**
     * Este metodo extrae el texto de archivos txt
     * @param path La ruta del archivo
     * @return Array de las lineas de texto
     */
    public static String[] txtReader(String path) {
        BufferedReader reader;
        ArrayList<String> lines= new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line.replaceAll("\n",""));
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toArray(new String[lines.size()]);
    }
}

