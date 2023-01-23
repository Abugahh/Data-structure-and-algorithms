package sample;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    // A dynamic list to track the elements inside the heap
    private List<T> heap = null;

    // Construct and initially empty priority queue
    public BinaryHeap() {
        this(1);
    }

    // Construct a priority queue with an initial capacity
    public BinaryHeap(int sz) {
        heap = new ArrayList<>(sz);
    }


    public BinaryHeap(T[] elems) {

        int heapSize = elems.length;
        heap = new ArrayList<T>(heapSize);


        for (int i = 0; i < heapSize; i++) heap.add(elems[i]);

        // Heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }

    // Priority queue construction, O(n)
    public BinaryHeap(Collection<T> elems) {

        int heapSize = elems.size();
        heap = new ArrayList<T>(heapSize);

        // Add all elements of the given collection to the heap
        heap.addAll(elems);

        // Heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }

    // Returns true/false depending on if the priority queue is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Clears everything inside the heap, O(n)
    public void clear() {
        heap.clear();
    }

    // Return the size of the heap
    public int size() {
        return heap.size();
    }


    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }


    public T poll() {
        return removeAt(0);
    }


    public boolean contains(T elem) {
        // Linear scan to check containment
        for (int i = 0; i < size(); i++) if (heap.get(i).equals(elem)) return true;
        return false;
    }


    public void add(T elem) {

        if (elem == null) throw new IllegalArgumentException();

        heap.add(elem);

        int indexOfLastElem = size() - 1;
        swim(indexOfLastElem);
    }

    // Tests if the value of node i <= node j
    // This method assumes i & j are valid indices, O(1)
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }


    private void swim(int k) {


        int parent = (k - 1) / 2;


        while (k > 0 && less(k, parent)) {
            // Exchange k with the parent
            swap(parent, k);
            k = parent;

            // Grab the index of the next parent node WRT to k
            parent = (k - 1) / 2;
        }
    }


    private void sink(int k) {
        int heapSize = size();
        while (true) {
            int left = 2 * k + 1; // Left  node
            int right = 2 * k + 2; // Right node
            int smallest = left;


            if (right < heapSize && less(right, left)) smallest = right;


            if (left >= heapSize || less(k, smallest)) break;

           
            swap(smallest, k);
            k = smallest;
        }
    }


    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }


    public boolean remove(T element) {
        if (element == null) return false;
        // Linear removal via search, O(n)
        for (int i = 0; i < size(); i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // Removes a node at particular index, O(log(n))
    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removed_data = heap.get(i);
        swap(i, indexOfLastElem);

        // Obliterate the value
        heap.remove(indexOfLastElem);

        // Check if the last element was removed
        if (i == indexOfLastElem) return removed_data;
        T elem = heap.get(i);

        // Try sinking element
        sink(i);

        // If sinking did not work try swimming
        if (heap.get(i).equals(elem)) swim(i);
        return removed_data;
    }


    public boolean isMinHeap(int k) {
        // If we are outside the bounds of the heap return true
        int heapSize = size();
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;


        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}



public class Ticket {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of customer takes the ticket:");
        int num= sc.nextInt();

        if (num>0){
            int[] tickets=new int[num];

            System.out.println("The tickets in the system are: ");
            for (int i=0;i<num;i++){
                tickets[i]=i+1;
                System.out.print(tickets[i]+" ");
            }
            System.out.println();
            System.out.println("Enter the number of tickets served");
            int served=sc.nextInt();

            if (served>0 && served<=num){
                System.out.println("The tickets served are:");
                for (int i=0; i<served;i++){
                    System.out.print(tickets[i]+" ");
                }
                System.out.println();

                if (served!=num){
                    System.out.println("The unserved tickets are: ");
                    for (int i=served;i<num;i++){
                        System.out.print(tickets[i]+" ");
                    }
                }else{
                    System.out.println("No more tickets to be served");
                }

            }

        }
    }
}
