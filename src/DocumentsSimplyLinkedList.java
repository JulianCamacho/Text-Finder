
public class DocumentsSimplyLinkedList {

    protected Documents first;
    private int length;

    public DocumentsSimplyLinkedList() {
        this.first = null;
    }

    public void clearList(){
        this.first = null;
        this.length = 0;
    }

    public boolean isEmpty(){
        return this.first == null;
    }

    public void addLast(Documents docu){
        this.length ++;
        if (this.first == null){
            this.first = docu;
        }
        else{
            Documents current = this.first;
            while (current.next != null){
                current = current.next;
            }
            current.next = docu;
        }
    }

    public void deleteAt(int index) {
        if (index > this.length-1){
            AlertBoxes.displayResultAlertBox("", "");
        }
        else {
            this.length--;
            if (index == 0){
                this.first = this.first.next;
            }
            else if (this.first != null){
                Documents current = this.first;
                int i = 0;
                while (i < index-1) {
                    current = current.next;
                    i++;
                }
                current.next = current.next.next;
            }
        }
    }

    public void printL(){
        if (this.first == null){
            System.out.print("[]");
        }
        else{
            Documents current = this.first;
            System.out.print("[");
            while (current.next != null){
                System.out.print(current.getName());
                System.out.print(", ");
                current = current.next;
            }
            System.out.print(current.getName());
            System.out.print("]");
        }
    }
}
