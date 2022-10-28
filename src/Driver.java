import bucket.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is a driver code for application.
 * The goal of this class is to compare 3 methods of sorting array. Methods from
 * BucketSort class and Arrays class from package java.util.
 * It uses bucket sorting algorithms from instance of <code>BucketSort</code>
 * class for movies in csv file (specifically file "projekt.csv") sorting problem.
 * Also, it provides statistics about sorted array of <code>Film</code> class
 * objects like number of movies, median and average number of movies' rating and
 * average time which program needed to sort.
 *
 * @author Mikolaj Kozlowski
 * @version 1.3
 * @since 2022-10-22
 * */

public class Driver {

    /**
     * This field defines name of file with movies to sort.
     * <bThis path can be changed only if structure of other csv file will be the same (columns).></b>
     * Otherwise, it is high probability that the program may not work correctly.
     * */
    final static String PATH = "projekt.csv";
    final static String SORT_WITH_LOOSING = "BucketSort.sortWithLoosing()";
    final static String SORT_WITHOUT_LOOSING = "BucketSort.sortWithoutLoosing()";

    enum TypeOfSort{
        SORT_WITH_LOOSING,
        SORT_WITHOUT_LOOSING,
        ARRAYS_SORT
    }
    /**
     * This main method sorts the arrays of <code>Film</code> instances
     * with different capacities. After sorting it is possible It uses methods from
     * BucketSort class and methods from java.util.Arrays class. The path of the file
     * with movies is specified in a <code>path</code> field and can be changed. The
     * number of movies in array demanding sort is specified in a <code>capacity</code>
     * variable. In a <code>accuracyOfSortingTime</code> variable is defined value how
     * many times operation of sorting is repeated, to achieve more reliable results.
     * The movies array is initializing by using <code>Filter</code> class. The main
     * method due to <code>Stats</code> class could display in a terminal the statistics
     * of movies array. It includes time needed to sort, median and average value of movies
     * in array and checking is sort algorithm method does task correct.
     * After the sorting is done, it is possible for the user to draw conclusions about which method
     * performed best and at the same time to determine whether, for this particular input data
     * (Array of Film instances from rows in "projekt.csv" file), the implementation of the bucket
     * sorting algorithm was worth using.
     * @param args  unused.
     * */
    public static void main(String[] args){
        final int[] capacity = {10000, 100000, 500000, 1000000, 1500000};
        final int accuracyOfSortingTime = 1000;
        System.out.println("Bucket sort movies from \"projekt.csv\"\n" +
                "The accuracy");
        for (int j : capacity) {
            for (int i = 0; i<3; i++){
                final Film[] films = Filter.downloadMovies(PATH, ",", j);
                String typeOfSort;
                List<Long> listOfTimesOfSortings;
                double averageTimeOfSortings;

                if (i == 0){
                    typeOfSort = SORT_WITH_LOOSING;
                    listOfTimesOfSortings = getListOfTimesOfSorting(films,
                            accuracyOfSortingTime, typeOfSort);
                    averageTimeOfSortings = getAverageTimeOfSorting(listOfTimesOfSortings);
                }
                else if (i == 1){
                    typeOfSort = SORT_WITHOUT_LOOSING;
                    listOfTimesOfSortings = getListOfTimesOfSorting(films,
                            accuracyOfSortingTime, typeOfSort);
                    averageTimeOfSortings = getAverageTimeOfSorting(listOfTimesOfSortings);
                }
                else{
                    typeOfSort = "Arrays.sort()";
                    listOfTimesOfSortings = getListOfTimesOfSorting(films,
                            accuracyOfSortingTime, typeOfSort);
                    averageTimeOfSortings = getAverageTimeOfSorting(listOfTimesOfSortings);
                }
                
                if (i==0) {
                    showStatsMovies(films);
                }
                showStatsSorting(typeOfSort,averageTimeOfSortings);
            }

        }
    }

    /**
     * This method is responsible for execute sorting methods. It calls methods from
     * BucketSort class or method from java.util.array depending on tested in main method algorithm.
     * Increasing the value of the parameter will increase the accuracy of the actual time needed
     * to perform the sorting but will also increase the time to wait for the results.
     * to be sure, after each sorting, the Checksort class, by means of its method, checks whether
     * it is sure that each successive element in the array forms an ascending sequence - that is,
     * whether the sorting was done correctly
     * @param films                   array of unsorted Film instances.
     * @param accuracyOfSortingTime   a value of how many times the loop performing the sorting is called.
     * @param typeOfSort              String that tells which method should execute.
     * */
    private static List<Long> getListOfTimesOfSorting(Film[] films, int accuracyOfSortingTime, String typeOfSort){
        List<Long> listOfAllTimes = new ArrayList<>();
        long start, stop;
        for (int i = 0; i < accuracyOfSortingTime; i++) {
            Film[] filmsToSort = films.clone();
            if (typeOfSort.equals(SORT_WITH_LOOSING)){
                start = System.currentTimeMillis();
                BucketSort.sortWithLossing(filmsToSort);
                stop = System.currentTimeMillis();
            }
            else if (typeOfSort.equals(SORT_WITHOUT_LOOSING)){
                start = System.currentTimeMillis();
                BucketSort.sortWithoutLoosing(filmsToSort);
                stop = System.currentTimeMillis();
            }
            else{
                start = System.currentTimeMillis();
                Arrays.sort(filmsToSort);
                stop = System.currentTimeMillis();
            }

            try {
                new CheckSort().checkIsSorted(filmsToSort);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }

            listOfAllTimes.add(stop-start);
        }
        return listOfAllTimes;
    }

    /**
     * This method is used for getting average value from all values in a List.
     * If list would be empty it returns zero value.
     * @param list       list with all times needed to sort array.
     * */
    private static double getAverageTimeOfSorting(List<Long> list){
        return list
                .stream()
                .mapToLong(s->s)
                .average()
                .orElse(0.0);
    }

    /**
     * This method represents statistics about time of sorting.
     * @param typeOfSort String information about which sorting method was using.
     * @param time       long value of sorting time.
     * */
    private static void showStatsSorting(String typeOfSort, double time){
        System.out.printf("method: %s \ntime=%.2fms%n",typeOfSort, time);
    }

    /**
     * This method represents statistics about the movies array.
     * @param arr        array of sorted <code>Film</code> instances.
     * */
    private static void showStatsMovies(Film[] arr){
        System.out.printf("\nStats{capacity=%d, average=%.2f, median=%.1f}%n"
                ,arr.length, Stats.getAverage(arr), Stats.getMedian(arr));
    }

}