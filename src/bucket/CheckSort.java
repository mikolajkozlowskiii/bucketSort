package bucket;
/**
 * Class <code>CheckSort</code> uses for check is array sorted.
 * */
public class CheckSort {
    /**
     * This method checks in O(n) time is array sorted.
     * All elements in the array must be comparable.
     * @param   array that is checked is sorted.
     * @throws IllegalArgumentException if only one element is not in ordering sequence.
     * */
    public <T extends Comparable<T>> void checkIsSorted(T[] array) throws IllegalArgumentException{
        for(int i = 1; i < array.length; i++)
            if(array[i].compareTo(array[i-1])<0) throw new IllegalArgumentException("Sorting went wrong.");
    }
}
