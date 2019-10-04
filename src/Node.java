
/**
 * Esta clase contiene los punteros y el contenido del arbol
 */
public class Node {
    /**
     * Nodo izquierdo
     */
    private Node left;
    /**
     * Nodo derecho
     */
    private Node rigth;
    /**
     * Ocurrencias de la palabra en los documentos
     */
    private WordOcurrences wordOcurrences;
    /**
     * Palabra almacenda
     */
    private String word;

    /**
     * Clase constructor
     * @param word palabra a almacenar
     */
    public Node(String word){
        this.word=word;
        wordOcurrences=new WordOcurrences();
    }

    /**
     * Retorna el nodo izquierdo
     * @return Nodo izquierdo
     */
    public Node getLeft() {
        return left;
    }


    /**
     * Establece el nodo izquierdo
     * @param left Nodo izquierdo
     */
    public void setLeft(Node left) {
        this.left = left;
    }
    /**
     * Retorna el nodo derecho
     * @return Nodo derecho
     */
    public Node getRigth() {
        return rigth;
    }

    /**
     * Establece el nodo derecho
     * @param rigth Nodo derecho
     */
    public void setRigth(Node rigth) {
        this.rigth = rigth;
    }

    /**
     * Retorna las ocurrencias
     * @return Las ocurrencias de la palabra
     */
    public WordOcurrences getWordOcurrences() {
        return wordOcurrences;
    }

    /**
     * Retorna la palabra almacenada en el nodo
     * @return La palabra almacenada en el nodo
     */
    public String getWord() {
        return word;
    }
}
