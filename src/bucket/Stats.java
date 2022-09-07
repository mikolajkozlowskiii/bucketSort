package  bucket;

import java.util.Arrays;

public class Stats {
    private final Film[] arrayOfMovies;
    private final double average;
    private final double median;
    private final long time;

    public Stats(Film[] arrayOfMovies, long time) {
        this.arrayOfMovies = arrayOfMovies;
        this.average = getAverage();
        this.median = getMedian();
        this.time = time;
    }

    public double getAverage(){
        return Arrays
                .stream(arrayOfMovies)
                .mapToDouble(Film::getRating)
                .average()
                .orElse(Double.NaN);
    }

    public double getMedian(){
        return arrayOfMovies.length % 2 == 0 ?
                Arrays
                        .stream(arrayOfMovies)
                        .skip(arrayOfMovies.length/2 -1)
                        .mapToDouble(Film::getRating)
                        .limit(2)
                        .average()
                        .orElse(0.0) :
                Arrays
                        .stream(arrayOfMovies)
                        .skip(arrayOfMovies.length/2)
                        .mapToDouble(Film::getRating)
                        .findFirst()
                        .orElse(0.0);
    }

    @Override
    public String toString() {
        return "Stats{" +
                "capacity=" + arrayOfMovies.length +
                ", average=" + String.format("%.2f",average)+
                ", median=" + median +
                ", time=" + time + "ms"+
                '}';
    }
}
