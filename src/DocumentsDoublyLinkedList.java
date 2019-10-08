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
            Documents newGate = addedDoc;
            this.last.next = newGate;
            newGate.prev = this.last;
            this.last = newGate;
        }
    }


    public int getLength() {
        return length;
    }
}
