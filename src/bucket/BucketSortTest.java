package bucket;

/**
 * This class is a driver code for application.
 * It uses bucket sorting algorithm from instance of <code>BucketSort</code>
 * class for movies in csv file (specifically file "projekt.csv") sorting problem.
 * Also, it provides statistics about sorting array of <code>Film</code> class
 * objects like number of movies, median and average number of movies' rating and
 * time which program needed to sort.
 *
 * @author Mikolaj Kozlowski
 * @version 1.1
 * @since 2022-09-07
 * */

public class BucketSortTest {

    /**
     * This field defines name of file with movies to sort.
     * <bThis path can be changed only if structure of other csv file will be the same (columns).></b>
     * Otherwise, it is high probability that the program may not work correctly.
     * */
    final static String path = "projekt.csv";

    /**
     * This main method sorts the arrays of <code>Film</code> instances
     * with different capacities.   It uses <code>bucketSort</code> methods. The path
     * of the file with movies is specified in a <code>path</code> field and can be
     * changed. The number of movies in array demanding sort is specified in a
     * <code>capacity</code> variable. The movies array is initializing by using
     * <code>Filter</code> class. The main method by the <code>Stats</code> class
     * displays in a terminal the statistics of movies array. It includes time needed
     * to sort, median and average value of movies in array and checking is sort algorithm
     * method does task correct.
     * @param args  unused.
     * */
    public static void main(String[] args){
        final int[] capacity = {10000, 100000, 500000, 1000000, 1500000};
        System.out.println("Bucket sort movies from projekt.csv");
        for (int j : capacity) {
            for(int i = 0; i<2; i++){
                Film[] films = Filter.downloadMovies(path, ",", j);

                long start = System.currentTimeMillis();
                if(i==0) BucketSort.sortWithLossing(films);
                else BucketSort.sortWithoutLoosing(films);
                long stop = System.currentTimeMillis();

                try {
                    new CheckSort().checkIsSorted(films);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }

                if(i==0) showStatsMovies(films);
                showStatsSorting(i==0 ? "sortWithLoosing method" : "sortWithoutLoosing method"
                        , stop-start);
            }
        }
    }

    /**
     * This method represents statistics about time of sorting.
     * @param typeOfSort String information about which sorting method was using.
     * @param time       long value of sorting time.
     * */
    public static void showStatsSorting(String typeOfSort, long time){
        System.out.println("method: "+typeOfSort+
                ", time=" + time + "ms");
    }

    /**
     * This method represents statistics about the movies array.
     * @param arr        array of sorted <code>Film</code> instances.
     * */
    public static void showStatsMovies(Film[] arr){
        System.out.println("Stats{" +
                "capacity=" + arr.length +
                ", average=" + String.format("%.2f",Stats.getAverage(arr))+
                ", median=" + Stats.getMedian(arr)+
                '}');
    }
}
