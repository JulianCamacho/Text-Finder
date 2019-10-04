import java.io.File;

/**
 * Esta clase implementa un arbol binario en el que se almacenan las palabras encontradas, usa el patron Singleton
 */
public class Tree {
    /**
     * Cabeza del arbol
     */
    private Node root= null;
    /**
     * Instancia del arbol
     */
    private static Tree instance;

    /**
     * Este metodo reinicia el arbol
     */
    public void clear(){
        this.root=null;
    }

    /**
     * Agrega una palabra al arbol
     * @param word Palabra a agregar
     * @param document Documento en el que aparece la palabra
     * @param lineNumber Linea en la que aparece la palabra
     * @param linePos Posicion en la linea en la que aparece la palabra
     */
    public void add(String word,File document, int lineNumber, int linePos){
        root=this.add(word, document,  lineNumber,  linePos, this.root);
    }

    /**
     * Metodo auxiliar para agregar una palabra al arbol
     * @param word Palabra a agregar
     * @param document Documento en el que aparece la palabra
     * @param lineNumber Linea en la que aparece la palabra
     * @param linePos Posicion en la linea en la que aparece la palabra
     * @param current Nodo que esta siendo analizada
     */
    private Node add(String word, File document, int lineNumber, int linePos, Node current){
        if(current==null){
            Node node= new Node(word);
            this.addOcurrence(node.getWordOcurrences(),document,lineNumber,linePos);
            return node;
        }else if(current.getWord().equals(word)){
            this.addOcurrence(current.getWordOcurrences(),document,lineNumber,linePos);
        }else if(current.getWord().compareTo(word)>0){
            current.setLeft(this.add(word, document,  lineNumber,  linePos, current.getLeft()));
        }else{
            current.setRigth(this.add(word, document,  lineNumber,  linePos, current.getRigth()));
        }
        return current;

    }

    /**
     *Este metodo agrega una ocurrencia a las ocurrencias de una palabra
     * @param wordOcurrences Ocurrencias a donde se agregara la aparicion de la palabra
     * @param document Documento en el que aparece la palabra
     * @param lineNumber Linea en la que aparece la palabra
     * @param linePos Posicion en la linea en la que aparece la palabra
     */
    private void addOcurrence(WordOcurrences wordOcurrences,File document, int lineNumber, int linePos){
        wordOcurrences.getDocuments().add(document);
        wordOcurrences.getLineNumber().add(lineNumber);
        wordOcurrences.getLinePos().add(linePos);
    }

    /**
     * Retorna las ocurrencias de una palabra
     * @param word Palabra a buscar
     * @return Ocurrencias de la palabra
     */
    public WordOcurrences getOcurrences(String word){
        return this.getOcurrences(word, root);
    }

    /**
     * Metodo auxiliar para encontrar las ocurrencias de una palabra
     * @param word Palabra a buscar
     * @param root Nodo que esta siendo analizado
     * @return Ocurrencias de la palabra
     */
    private WordOcurrences getOcurrences(String word, Node root){
        if(root==null){
            return null;
        }else if(root.getWord().equals(word)){
            return root.getWordOcurrences();
        }else if(root.getWord().compareTo(word)>0){
            return this.getOcurrences(word, root.getLeft());
        }else{
            return this.getOcurrences(word, root.getRigth());
        }
    }

    /**
     * Retorna la instancia del arbol
     * @return La instancia del arbol
     */
    public static Tree getInstance(){
        if(instance==null){
            instance=new Tree();
        }
        return instance;
    }
}