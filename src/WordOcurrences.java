import java.io.File;
import java.util.ArrayList;

/**
 * Esta clase almacena las ocurrencias de una palabra en los documentos de la biblioteca
 */
public class WordOcurrences {
    /**
     * Lista de los archivos
     */
    private ArrayList<File> documents;
    /**
     * Lista de numero de linea de la ocurrencia
     */
    private ArrayList<Integer> lineNumber;
    /**
     * Lista de posiciones en la linea de las ocurrencias
     */
    private ArrayList<Integer> linePos;

    /**
     * Constructor
     */
    public WordOcurrences(){
        this.documents = new ArrayList<>();
        this.lineNumber = new ArrayList<>();
        this.linePos = new ArrayList<>();
    }

    /**
     * Retorna la lista de documentos
     * @return Lista de documentos
     */
    public ArrayList<File> getDocuments() {
        return documents;
    }

    /**
     * Establece los documentos
     * @param documents Lista de documentos
     */
    public void setDocuments(ArrayList<File> documents) {
        this.documents = documents;
    }
    /**
     * Retorna la lista de numeros de linea
     * @return Lista de numeros de linea
     */
    public ArrayList<Integer> getLineNumber() {
        return lineNumber;
    }
    /**
     * Establece los numeros de linea
     * @param lineNumber Lista de numeros de linea
     */
    public void setLineNumber(ArrayList<Integer> lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Retorna la lista de posiciones en la linea
     * @return Lista de posiciones en la linea
     */
    public ArrayList<Integer> getLinePos() {
        return linePos;
    }

    /**
     * Establece la lista de posiciones en la linea
     * @param linePos Lista de posiciones en la linea
     */
    public void setLinePos(ArrayList<Integer> linePos) {
        this.linePos = linePos;
    }
}
