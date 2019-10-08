public class BubbleSort {

    public static void bubbleSort(DocumentsDoublyLinkedList dl) {
        int n = dl.getLength();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (dl.get(j).getDate().compareTo(dl.get(j+1).getDate()) > 0) {
                    QuickSort.swap(dl.get(j), dl.get(j+1), dl);
                }
            }
        }
    }
}
