import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.text.Normalizer;

public class Documents {

    private File file;
    private String absPath;
    private String text;
    private String name;
    private String size;
    private String date;
    private int realSize;
    private TextFlow textFlow;

    protected Documents next = null;
    protected Documents prev = null;

    public Documents(String absPath, String text, String name, String size, String date, String phrase) {
        this.absPath = absPath;
        toFile(absPath);
        this.text = text;
        this.name = name;
        this.size = size;
        this.date = date;
        this.realSize = toInt(this.size);
        this.textFlow = createTexts(phrase);
    }

    /**
     * Metodo para crear Texts para subrayar las palabras encontradas
     * @param phrase frase buscada
     * @return TextFlow con el texto modificado
     */
    public TextFlow createTexts(String phrase){
        String completeText = clearAccent(this.text);
        String target = clearAccent(phrase);
        String[] words = completeText.split("\\W+");

        TextFlow result = new TextFlow();
        result.setTextAlignment(TextAlignment.JUSTIFY);
        result.setPrefSize(295, 80);
        ObservableList list = result.getChildren();

        for (String word : words) {
            if (target.contains(word)) {
                Text newText = new Text(word + " ");
                newText.setUnderline(true);
                newText.setFill(Color.DARKGOLDENROD);
                list.add(newText);
            } else if (!target.contains(word)){
                list.add(new Text(word + " "));
            }
        }
        return result;
    }

    /**
     * Metodo para eliminar tildes de las palabras
     * @param cadena texto a modificar
     * @return texto modificado
     */
    public static String clearAccent(String cadena) {
        String limpio =null;
        if (cadena !=null) {
            String valor = cadena;
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
            limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }

    public void toFile(String path){
        try{
            this.file = new File(path);
        } catch (NullPointerException e){
            AlertBoxes.displayAlertBox("Exception", "Invalid File");
        }
    }

    public static int toInt(String size){
        String[] subString = size.split(" ");
        String reallyRealSize = subString[0];
        return Integer.parseInt(reallyRealSize);
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRealSize() { return realSize; }

    public void setRealSize(int realSize) { this.realSize = realSize; }

    public File getFile() { return file; }

    public String getAbsPath() { return absPath; }

    public void setAbsPath(String absPath) { this.absPath = absPath; }

    public TextFlow getTextFlow() {
        return textFlow;
    }

    public void setTextFlow(TextFlow textFlow) {
        this.textFlow = textFlow;
    }
}
