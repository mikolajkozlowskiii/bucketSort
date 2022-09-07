package bucket;

public class CheckSort {
    public <T extends Comparable<T>> void checkIsSorted(T[] array) throws Exception{
        for(int i = 1; i < array.length; i++)
            if(array[i].compareTo(array[i-1])<0) throw new Exception("SORTING ERROR");
    }
}
