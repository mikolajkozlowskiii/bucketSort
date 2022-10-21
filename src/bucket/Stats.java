package  bucket;

import java.util.Arrays;

/**
 * This class represents statistics about array of <code>Film</code> instances and sorting.
 * */

public class Stats {

    /**
     * Gets the average of values in array of <code>Film</code> instances.
     * @param   arrayOfMovies array with movies to checks statistics.
     * @return  double  median value, if array is empty returns 0.0
     * */
    public static double getAverage(Film[] arrayOfMovies){
        return Arrays
                .stream(arrayOfMovies)
                .mapToDouble(Film::getRating)
                .average()
                .orElse(Double.NaN);
    }

    /**
     * Gets the median of values in array of <code>Film</code> instances.
     * @param   arrayOfMovies array with movies checks statistics.
     * @return  double  median value, if array is empty returns 0.0
     * */
    public static double getMedian(Film[] arrayOfMovies){
        return arrayOfMovies.length % 2 == 0 ?
                Arrays
                        .stream(arrayOfMovies)
                        .skip(arrayOfMovies.length/2 -1)
                        .mapToDouble(Film::getRating)
                        .limit(2)
                        .average()
                        .orElse(Double.NaN) :
                Arrays
                        .stream(arrayOfMovies)
                        .skip(arrayOfMovies.length/2)
                        .mapToDouble(Film::getRating)
                        .findFirst()
                        .orElse(Double.NaN);
    }
}