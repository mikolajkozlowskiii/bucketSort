package bucket;

import java.util.Objects;
import java.util.function.DoubleSupplier;

/**
 * This class represents all the data about movies.
 * */

public class Film implements Comparable<Film>, DoubleSupplier {
    /**
     * This field is sequenced number of movie in a list (1 column in projekt.csv).
     * */
    private final int number;
    /**
     * This field is a name of a movie (2 column in projekt.csv).
     * */
    private final String name;
    /**
     * This field is a rating of a movie (3 column in projekt.csv).
     * */
    private final double rating;


    public Film(int number, String name, double rating) {
        this.number = number;
        this.name = name;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return number == film.number && Double.compare(film.rating, rating) == 0 && Objects.equals(name, film.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, rating);
    }

    /**
     * This method compares two instances of Film by them ratings field.
     * @param    f Film instance to compare.
     * @return   a value which describes is compared object bigger, smaller or equal.
     * */
    @Override
    public int compareTo(Film f) {
        return Double.compare(rating,f.rating);
    }

    @Override
    public double getAsDouble() {
        return getRating();
    }
}