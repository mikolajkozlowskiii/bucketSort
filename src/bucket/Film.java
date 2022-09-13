package bucket;

import java.util.Objects;

/**
 * Class <code>Film</code> represents all the data about movies.
 * */
public class Film implements Comparable<Film>, ProduceIntiger{
    /**
     * field <code>number</code> sequence number of movie in a list (1 column in projekt.csv).
     * */
    private final int number;
    /**
     * field <code>name</code> name of a movie (2 column in projekt.csv).
     * */
    private final String name;
    /**
     * field <code>rating</code> rating of a movie (3 column in projekt.csv).
     * */
    private final double rating;
/**
 * Constructor to create Film object.
 * @param number a value of field <code>number</code>.
 * @param name   a String value of field <code>name</code>.
 * @param rating a double value of field <code>rating</code>.
 * */
    public Film(int number, String name, double rating) {
        this.number = number;
        this.name = name;
        this.rating = rating;
    }
    /**
     * Gets a <code>rating</code> field.
     * @return rating of a movie.
     * */

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
     * Method that compares two instances of Film by them ratings field.
     * @param    f object Film to compare.
     * @return   a value which describes is compared object bigger, smaller or equal.
     * */
    @Override
    public int compareTo(Film f) {
        return Double.compare(rating,f.rating);
    }

    @Override
    public int produce() {
        return (int) rating;
    }
}
