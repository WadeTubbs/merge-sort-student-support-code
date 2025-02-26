import java.util.ArrayList;

public class MergeSort {

    /**
     * Sorts the range [begin, end) using the Merge Sort algorithm.
     * @param begin The iterator to the start of the range.
     * @param end The iterator to the end of the range.
     */
    public static <E extends Comparable<? super E>> void sort(Iterator<E> begin, Iterator<E> end) {
        int size = distance(begin, end);
        if (size <= 1) return;  // Base case: Already sorted

        // Find the midpoint
        int mid = size / 2;
        Iterator<E> midIter = begin.clone();
        midIter.advance(mid);

        // Recursively sort the two halves
        sort(begin, midIter);
        sort(midIter, end);

        // Merge sorted halves
        ArrayList<E> tempArray = make_array(size);
        ArrayListIterator<E> tempIter = new ArrayListIterator<>(tempArray, 0);

        merge(begin, midIter, midIter, end, tempIter);

        // Copy sorted elements back to original
        copy(new ArrayListIterator<>(tempArray, 0),
             new ArrayListIterator<>(tempArray, size),
             begin);
    }

    /**
     * Merges two sorted ranges [begin1, end1) and [begin2, end2) into [result, result.advance(n + m)).
     * @param begin1 Iterator pointing to the start of the first range.
     * @param end1 Iterator pointing to the end of the first range.
     * @param begin2 Iterator pointing to the start of the second range.
     * @param end2 Iterator pointing to the end of the second range.
     * @param result Iterator pointing to the start of the result range.
     * @return Iterator pointing to the end of the merged result.
     */
    public static <E extends Comparable<? super E>> Iterator<E> merge(Iterator<E> begin1, Iterator<E> end1,
                                                                      Iterator<E> begin2, Iterator<E> end2,
                                                                      Iterator<E> result) {
        Iterator<E> resIter = result.clone();

        // Merge elements from both sorted halves
        while (!begin1.equals(end1) && !begin2.equals(end2)) {
            if (begin1.get().compareTo(begin2.get()) <= 0) {
                resIter.set(begin1.get());
                begin1.advance();
            } else {
                resIter.set(begin2.get());
                begin2.advance();
            }
            resIter.advance();
        }

        // Copy remaining elements from first half
        while (!begin1.equals(end1)) {
            resIter.set(begin1.get());
            begin1.advance();
            resIter.advance();
        }

        // Copy remaining elements from second half
        while (!begin2.equals(end2)) {
            resIter.set(begin2.get());
            begin2.advance();
            resIter.advance();
        }

        return resIter;
    }

    /**
     * Returns the number of elements in the range [begin, end).
     */
    public static <E> int distance(Iterator<E> begin, Iterator<E> end) {
        int n = 0;
        for (Iterator<E> i = begin.clone(); !i.equals(end); i.advance()) {
            ++n;
        }
        return n;
    }

    /**
     * Copies the elements from the range [source_begin, source_end) to [destination_begin, destination_end).
     * Ensures safe copying within valid bounds.
     */
    public static <E> Iterator<E> copy(Iterator<E> source_begin, Iterator<E> source_end, Iterator<E> destination_begin) {
        Iterator<E> destination = destination_begin.clone();
        for (Iterator<E> i = source_begin.clone(); !i.equals(source_end); i.advance()) {
            destination.set(i.get());
            destination.advance();
        }
        return destination;
    }

    /**
     * Creates an ArrayList of the specified size, initialized to null.
     */
    public static <E> ArrayList<E> make_array(int size) {
        ArrayList<E> array = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            array.add(null);
        }
        return array;
    }
}
