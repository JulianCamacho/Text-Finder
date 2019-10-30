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

    public DocumentsDoublyLinkedList(int quantity) {
        this.first = null;
        createVoidDocs(quantity);
    }

    private void createVoidDocs(int quantity){
        for (int i = 0; i < quantity; i++){
            this.addLast(new Documents("", "", "1", ""));
        }
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

    public void set(int index, Documents newDoc){
        if (index > this.length-1 || this.first == null || index < 0){
            System.out.println("Index out of range...");
        }
        else{
            int counter = 0;
            Documents current = this.first;
            while(counter < index){
                current = current.next;
                counter ++;
            }
            current.setText(newDoc.getText());
            current.setName(newDoc.getName());
            current.setSize(newDoc.getSize());
            current.setDate(newDoc.getDate());
            current.setRealSize(newDoc.getRealSize());
        }
    }

    public void deleteAt(int index){
        if (index > this.length-1 || this.first == null || index < 0){
            System.out.println("Index out of range");
        }
        else{
            int counter = 0;
            Documents current = this.first;
            while(counter < index){
                current = current.next;
                counter ++;
            }
            if (current == this.first){
                Documents after = current.next;
                after.prev = null;
                this.first = after;
            }
            else if (current == this.last){
                Documents before = current.prev;
                before.next = null;
                this.last = before;
            } else {
                Documents before = current.prev;
                Documents after = current.next;
                before.next = after;
                after.prev = before;

            }
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
                System.out.print(current.getRealSize());
                System.out.print(", ");
                current = current.next;
            }
            System.out.print(current.getRealSize());
            System.out.println("]");
        }
    }

    public void addLast(Documents addedDoc) {
        this.length ++;
        if (this.first == null){
            this.first = addedDoc;
            this.last = this.first;
        }
        else {
            this.last.next = addedDoc;
            addedDoc.prev = this.last;
            this.last = addedDoc;
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

    public int[] toIntArray(){
        int[] result = new int[this.length];
        for(int i = 0; i < length; i++){
            result[i] = this.get(i).getRealSize();
        }
        return result;
    }
}
