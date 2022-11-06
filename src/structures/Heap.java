package structures;

public class Heap<AnyType extends Comparable<AnyType>> {
    protected AnyType[] heap;
    protected int position = -1;

    public Heap() {
        heap = (AnyType[]) new Comparable[2];
    }

    public void fixUpward() {
        int index = position;
        int parentIndex = (index - 1) / 2;
        while (parentIndex >= 0 && heap[index].compareTo(heap[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public void fixDownward(int endIndex) {
        if (endIndex == -1) return;
        int index = 0;
        while (index <= endIndex) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if (leftChildIndex > endIndex) break;

            int childToSwap = rightChildIndex > endIndex
                    ? leftChildIndex
                    : heap[leftChildIndex].compareTo(heap[rightChildIndex]) < 0
                    ? leftChildIndex
                    : rightChildIndex;

            if (heap[index].compareTo(heap[childToSwap]) < 0) break;
            swap(index, childToSwap);
            index = childToSwap;
        }
    }

    public Heap<AnyType> add(AnyType data) {
        if (isFull()) {
            resize(2 * heap.length);
        }
        heap[++position] = data;
        fixUpward();
        return this;
    }

    public AnyType getRoot() {
        return heap[0];
    }

    public AnyType deleteMin() {
        if (isEmpty()) {
            return null;
        }
        AnyType result = heap[0];
        heap[0] = heap[position--];
        heap[position + 1] = null;
        fixDownward(position);
        return result;
    }

    public AnyType search(AnyType data) throws Exception {
        AnyType[] auxHeap = this.heap;

        for (AnyType aux : auxHeap) {
            if (data == aux) {
                return aux;
            }
        }
        throw new Exception("El elemento no esta en el monticulo");
    }

    public void printInOrder() {
        AnyType[] auxHeap = heap.clone();

        for (int i = 0; i <= position; i++) {
            swap(0, position - i);
            fixDownward(position - i - 1);
        }

        for (int i = heap.length - 1; i > -1; i--) {
            if (heap[i] != null) System.out.print(heap[i] + "\t");
        }
        System.out.println();

        heap = auxHeap;
    }

    protected void swap(int firstIndex, int secondIndex) {
        AnyType temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    private void resize(int capacity) {
        System.arraycopy(heap, 0, heap = (AnyType[]) new Comparable[capacity], 0, position + 1);
    }

    private boolean isFull() {
        return position == heap.length - 1;
    }

    public boolean isEmpty() {
        return heap.length == 0;
    }

    public void print() {
        for (int i = 0; i < heap.length - 1; i++) {
            System.out.print(heap[i] + "\t");
        }
    }

    public void printFancyTree() {
        System.out.println(printFancyTree(0, ""));
    }

    private String printFancyTree(int index, String prefix) {
        String outputString = "";

        if (index != 0) outputString = prefix + "└──";

        if (index <= heap.length - 1) {
            boolean isLeaf = index > heap.length / 2;

            if (heap[index] != null) outputString += heap[index] + "\n";
            else outputString += "\n";

            String _prefix = prefix;

            if (index != 0){
                if (index % 2 == 1)
                    _prefix += "│  "; // one | and two spaces
                else
                    _prefix += "   "; // three spaces
            }


            if (!isLeaf) {
                outputString += printFancyTree(2 * index + 1, _prefix);
                outputString += printFancyTree(2 * index + 2, _prefix);
            }
        } else
            outputString += "\n";

        return outputString;
    }
}

