package bucket;
/**
 * <h1>Bucket Sort</h1>
 * The BucketSortTest class implements bucket sorting algorithm
 * for movies in csv file (specifically file "projekt.csv") sorting problem.
 * Also it provides statistics about sorting and movies collection like
 * number of movies, median and average number of movies' rating and time which
 * program needed to sort.
 * @author Mikolaj Kozlowski
 * @version 1.0
 * @since 2022-09-07
 * */

import java.util.*;


public class BucketSortTest {
    /**
     * This is the main method which sorts the arrays of <code>Film</code> class objects with different capacities by
     * using <code>bucketSort</code> method.
     * The path of the csv file with movies is specified in a "path" variable. The number of movies in array
     * demanding sort is specified in a "capacity" variable. The array of movies is initializing by
     * using <code>Filter</code> class.
     * The main method by the <code>Stats</code> class displays in terminal the statistics of movies collection
     * which include time needed to sort, median and average number
     * of movies' array and checking is sort algorithm method do the task correct.
     * @param args unused
     * */

    public static void main(String[] args){
        final String path = "projekt.csv";
        final int[] capacity = {10000, 100000, 500000, 1000000, 15000000};
        System.out.println("BUCKET SORT");
        for (int j : capacity) {
            Film[] films = Filter.downloadMovies(path, ",", j);

            long start = System.currentTimeMillis();
            bucketSort(films);
            long stop = System.currentTimeMillis();

            try {
                new CheckSort().checkIsSorted(films);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(new Stats(films, stop - start));
        }
    }

    /**
     * This method sorts in bucket style array of <code>Film</code> class objects.
     * Sort is comparing in Film object <code>rating</code> field.
     * All movies have ratings double value, but they are really integers numbers
     * so the problem of bucket sorting (assigning the appropriate bucket) is made easy.
     * @param  arr     the array of Film which we want to sort
     * */
    public static void bucketSort(Film[] arr) {
        final int[] minMax = getMinMax(arr);
        final int min = minMax[0];
        final int max = minMax[1];
        final int numOfBuckets = max-min+1;

        ArrayList<Film>[] arrayOfList = new ArrayList[numOfBuckets+1];
        //Arrays.fill(arrayOfList, new ArrayList<Film>());

        for(int i = 0; i<numOfBuckets; i++){
            arrayOfList[i] = new ArrayList<>();
        }

        for (Film film : arr) {
            int bucket = (int) film.getRating();
            arrayOfList[bucket - min].add(film);
        }

        int x = 0;
        for(int i = 0; i < numOfBuckets; i++){
            for(int j = 0; j < arrayOfList[i].size(); j++){
                arr[x] = arrayOfList[i].get(j);
                x++;
            }
        }
    }

    /**
     * This method is used to help <code>bucketSort</code> method
     * to find the min and max value from all the <code>Film</code> class objects in array.
     * @param arr    the array of Film collection
     * @return int[] the array of int with min and max value of the movies
     * */
    private static int[] getMinMax(Film[] arr) {
        int[] minMax = new int[2];
        Arrays.fill(minMax,0);
        for (Film film : arr) {
            if (minMax[0] > (int) film.getRating())
                minMax[0] = (int) film.getRating();
            else if (minMax[1] < (int) film.getRating())
                minMax[1] = (int) film.getRating();
        }
        return minMax;
    }
}
