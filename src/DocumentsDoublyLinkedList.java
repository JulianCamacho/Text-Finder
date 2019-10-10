import java.net.MalformedURLException;

/**
 * Lista doblemente enlazada de documentos
 */
public class DocumentsDoublyLinkedList {

    protected Documents first;
    protected Documents last;
    private int length;

    public DocumentsDoublyLinkedList() {
        this.first = null;
    }

    public void clearList(){
        this.first = null;
        this.length = 0;
    }

    public boolean isEmpty(){
        return this.first == null;
    }

    public Documents get(int index){
        if (index > this.length-1 || this.first == null || index < 0){
            System.out.println("Index out of range");
            return null;
        }
        else{
            int counter = 0;
            Documents current = this.first;
            while(counter < index){
                current = current.next;
                counter ++;
            }
            return current;
        }
    }

    public void printList(){
        if (this.first == null){
            System.out.println("[]");
        }
        else {
            Documents current = this.first;
            System.out.print("[");
            while (current.next != null) {
                System.out.print(current.getName());
                System.out.print(", ");
                current = current.next;
            }
            System.out.print(current.getName());
            System.out.println("]");
        }
    }

    public void addLast(Documents addedDoc) throws MalformedURLException {
        this.length ++;
        if (this.first == null){
            this.first = addedDoc;
            this.last = this.first;
        }
        else {
            Documents newDoc = addedDoc;
            this.last.next = newDoc;
            newDoc.prev = this.last;
            this.last = newDoc;
        }
    }

    public void reverseList(){
        Documents previous = null;
        //change reference head becomes tail in reversal
        this.last = this.first;
        Documents current = this.first;
        while(current != null){
            // swap prev and next for the current node
            previous = current.prev;
            current.prev = current.next;
            current.next = previous;
            // to move to next node current.prev has to be called
            // as that has reference to next node now
            current = current.prev;
        }
        if(previous != null){
            this.first = previous.prev;
        }
    }

    public int getLength() {
        return length;
    }
}
