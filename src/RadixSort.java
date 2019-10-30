import java.net.MalformedURLException;
import java.util.Arrays;

public class RadixSort {
    // A utility function to get maximum value in arr[]
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }
    // A utility function to get maximum value in arr[]
    static int myGetMax(DocumentsDoublyLinkedList dl, int n) {
        int mx = dl.get(0).getRealSize();
        for (int i = 1; i < n; i++)
            if (dl.get(i).getRealSize() > mx)
                mx = dl.get(i).getRealSize();
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    public static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    public static void myCountSort(DocumentsDoublyLinkedList dl, int n, int exp) {
        DocumentsDoublyLinkedList output = new DocumentsDoublyLinkedList(n); // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (dl.get(i).getRealSize()/exp)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output.set(count[ (dl.get(i).getRealSize()/exp)%10 ] - 1, dl.get(i));
            count[ (dl.get(i).getRealSize()/exp)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            dl.set(i, output.get(i));
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public static int[] radixsort(int arr[], int n) {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);
        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10){
            countSort(arr, n, exp);
        }
        return arr;
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public static DocumentsDoublyLinkedList myRadixsort(DocumentsDoublyLinkedList dl, int n) {
        // Find the maximum number to know number of digits
        int m = myGetMax(dl, n);
        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10){
            myCountSort(dl, n, exp);
        }
        return dl;
    }

    public static DocumentsDoublyLinkedList backToDoublyList(int[] result, DocumentsDoublyLinkedList dl) {
        //DocumentsDoublyLinkedList tmp = dl;
        DocumentsDoublyLinkedList tmp2 = new DocumentsDoublyLinkedList();
        System.out.println(result.length);
        System.out.println(dl.getLength());
        System.out.println(Arrays.toString(result));
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < dl.getLength(); j++) {
                if (result[i] == dl.get(j).getRealSize()) {
                    //dl.set(i, tmp.get(j));
                    tmp2.printList();
                    tmp2.addLast(dl.get(j));
                    tmp2.printList();
                }
            }
        }
        for (int k = 0; k < tmp2.getLength(); k++){
            dl.set(k, tmp2.get(k));
        }
        return dl;
    }
}
