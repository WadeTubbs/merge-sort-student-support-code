import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.Arrays;
import java.util.Random;

public class StudentTest {

    @Test
    public void smallMergeSortArray() {
        Integer[] A_orig = {2, 8, 7, 1, 3, 5, 6, 4};
        Integer[] A_sorted = A_orig.clone();
        Arrays.sort(A_sorted);

        Integer[] A = A_orig.clone();
        ArrayIterator<Integer> begin = new ArrayIterator<>(A, 0);
        ArrayIterator<Integer> end = new ArrayIterator<>(A, A.length);
        MergeSort.sort(begin, end);
        
        assertArrayEquals(A_sorted, A);
    }

    @Test
    public void largeMergeSortArray() {
        Random rand = new Random();
        Integer[] A = new Integer[1000];
        for (int i = 0; i < A.length; i++) {
            A[i] = rand.nextInt(10000);
        }
        Integer[] A_sorted = A.clone();
        Arrays.sort(A_sorted);

        ArrayIterator<Integer> begin = new ArrayIterator<>(A, 0);
        ArrayIterator<Integer> end = new ArrayIterator<>(A, A.length);
        MergeSort.sort(begin, end);
        
        assertArrayEquals(A_sorted, A);
    }

    @Test
    public void testMerge() {
        Integer[] A1 = {1, 3, 5, 7};
        Integer[] A2 = {2, 4, 6, 8};
        Integer[] expectedMerged = {1, 2, 3, 4, 5, 6, 7, 8};

        Integer[] result = new Integer[A1.length + A2.length];
        ArrayIterator<Integer> begin1 = new ArrayIterator<>(A1, 0);
        ArrayIterator<Integer> end1 = new ArrayIterator<>(A1, A1.length);
        ArrayIterator<Integer> begin2 = new ArrayIterator<>(A2, 0);
        ArrayIterator<Integer> end2 = new ArrayIterator<>(A2, A2.length);
        ArrayIterator<Integer> resultIter = new ArrayIterator<>(result, 0);

        MergeSort.merge(begin1, end1, begin2, end2, resultIter);

        assertArrayEquals(expectedMerged, result);
    }
}
