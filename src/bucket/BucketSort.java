package bucket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * This class implements bucket sorting algorithm that operates exclusively of
 * <code>Film</code>arrays. It consists only static methods. There are 2 types
 * of sorting methods, which are differs in the way the bucket data is received and the
 * accuracy of the bucket data. Due to that, depending on Film types instances in array,
 * it is sometimes better to call one method and sometimes the other. Because of that
 * there are different times of sorting, which are usually seen on bigger capacity of array.
 * Class should be used for movies in csv file (specifically file "projekt.csv" because of its structure).
 * */

public class BucketSort {

    /**
     * This method sorts in a bucket style array of <code>Film</code> instances faster.
     * If you are not sure is whole array has Films instances with ratings in integers
     * casts to double, better not to use this method. In that case this method via the
     * <code>getMinMaxWithLoosing</code> method throws exception, because this sort may not work
     * correctly. Sort algorithm is comparing in Film object <code>rating</code> field, which
     * can be cast to integer with loosing information after dot, for example 7.0 would
     * be converted to 7. All movies in specified "projekt.csv" file have ratings in double
     * value, but they are really integers numbers converted to double, so the problem of
     * bucket sorting (assigning the appropriate bucket) is made easy. This method uses
     * <code>getMinMax</code> method which provides array of integers with information about
     * buckets.
     * @param  arr      an array of Film to sort.
     * */
    public static void sortWithLossing(Film[] arr) {
        final int[] minMax = getMinMaxWithLoosing(arr);
        final int min = minMax[0];
        final int max = minMax[1];
        final int numOfBuckets = max-min+1;

        ArrayList<Film>[] arrayOfList = new ArrayList[numOfBuckets];

        for(int i = 0; i<numOfBuckets; i++){
            arrayOfList[i] = new ArrayList<>();
        }

        for (Film film : arr) {
            int bucket = (int) film.getRating();
            arrayOfList[bucket - min].add(film);
        }

        int index = 0;
        for(int i = 0; i < numOfBuckets; i++){
            for(int j = 0; j < arrayOfList[i].size(); j++){
                arr[index] = arrayOfList[i].get(j);
                index++;
            }
        }
    }

    /**
     * This method is used to help <code>bucketSort</code> method.
     * It is used to find the minimum and maximum value from all the <code>Film</code>
     * instances in array.
     * @param arr                       an array of Film needed to get min and max value.
     * @return int[]                    an array of int with min and max value of the movies.
     * @throws IllegalArgumentException if only one element is not integer.
     * */
    private static int[] getMinMaxWithLoosing(Film[] arr) {
        int[] minMax = new int[2];
        Arrays.fill(minMax,0);
        for (Film film : arr) {
            double currentNumber = film.getRating();
            if(!(currentNumber%1==0)){
                throw new IllegalArgumentException("Ratings should be integers!");
            }
            int currentNumberCastedToInt = (int)currentNumber;
            if (minMax[0] > currentNumberCastedToInt)
                minMax[0] = currentNumberCastedToInt;
            else if (minMax[1] < currentNumberCastedToInt)
                minMax[1] = currentNumberCastedToInt;
        }
        return minMax;
    }

    /**
     * This method sorts in a bucket style array of <code>Film</code> class instances safer.
     * It is alternative version of previous <code>bucketSort</code> method. If you are sure
     * is whole array has Films with ratings in integers casts to double, better not to use
     * this method. This method is much slower than second method in bigger capacities.This
     * algorithm is comparing in Film instance <code>rating</code> field, which can not
     * be cast to integer without loosing important information after dot, for example
     * 5.2 would be converted to 5. This method uses <code>getMinMax2</code> method which
     * provides HashMap with whole information about all buckets.
     * @param  arr     an array of Film to sort.
     * */
    public static void sortWithoutLoosing(Film[] arr) {
        HashMap<Double,Integer> map = getMinMaxWithoutLoosing(arr);
        final int numOfBuckets = map.size();
        ArrayList<Film>[] arrayOfList = new ArrayList[numOfBuckets];
        for(int i = 0; i<numOfBuckets; i++){
            arrayOfList[i] = new ArrayList<>();
        }

        for (Film film : arr) {
            arrayOfList[map.get(film.getRating())].add(film);
        }

        int index = 0;
        for(int i = 0; i < numOfBuckets; i++){
            for(int j = 0; j < arrayOfList[i].size(); j++){
                arr[index] = arrayOfList[i].get(j);
                index++;
            }
        }
    }

    /**
     * This method is used to help <code>bucketSort2</code> method.
     * It provides information about which bucket is assigned to
     * the value of <code>rating</code> field in <code>Film</code> array.
     * @param arr                       an array of Film needed to get information about buckets to sort.
     * @return HashMap<Double, Integer> a HashMap with information about buckets.
     * */
    private static HashMap<Double, Integer> getMinMaxWithoutLoosing(Film[] arr){
        TreeSet<Double> set = new TreeSet<>();
        for(Film f : arr){
            set.add(f.getRating());
        }
        HashMap<Double, Integer> map = new HashMap<>();
        int numOfBucket = 0;
        while(!set.isEmpty()){
            map.put(set.pollFirst(),numOfBucket);
            numOfBucket++;
        }
        return map;
    }
}