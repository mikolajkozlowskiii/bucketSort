package bucket;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Film[] arr = new Film[7];
        arr[0] = new Film(1,"Mortal",5);
        arr[1] = new Film(2,"Mortal2",9);
        arr[2] = new Film(3,"Mortal3",1);

        BucketSort.sortWithoutLoosing(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
    }
}
