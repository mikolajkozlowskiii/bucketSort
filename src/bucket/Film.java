package bucket;

import java.util.Objects;

/**
 * Class <code>Film</code> represents all the data
 * about movies from project.csv file
 * */
public class Film implements Comparable<Film>, ProduceIntiger{
    private final int number;
    private final String name;
    private final double rating;
/**
 * Constructor to create Film object
 * */
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

    @Override
    public int compareTo(Film f) {
        return Double.compare(rating,f.rating);
    }

    @Override
    public int produce() {
        return (int) rating;
    }
}
