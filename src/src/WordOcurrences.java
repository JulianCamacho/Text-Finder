import java.io.File;
import java.util.ArrayList;

public class WordOcurrences {
    private ArrayList<File> documents;
    private ArrayList<Integer> lineNumber;
    private ArrayList<Integer> linePos;

    public WordOcurrences(){
        this.documents = new ArrayList<>();
        this.lineNumber = new ArrayList<>();
        this.linePos = new ArrayList<>();
    }


    public ArrayList<File> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<File> documents) {
        this.documents = documents;
    }

    public ArrayList<Integer> getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(ArrayList<Integer> lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ArrayList<Integer> getLinePos() {
        return linePos;
    }

    public void setLinePos(ArrayList<Integer> linePos) {
        this.linePos = linePos;
    }
}
