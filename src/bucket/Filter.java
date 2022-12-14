package bucket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * This class initializes the array of <code>Film</code> class instances.
 * This class implements  one <code>downloadMovies</code> method. It is
 * responsible for downloading and filtering all movies from file.
 *
 **/

public class Filter {

    /**
     * This method downloads and filters the movies from csv file
     * which row can be cast to <code>Film</code> instances.
     * <b>It is highly recommended to use this class only for csv files
     * which rows can be cast on Film class and also no one row in this
     * file has not got any names for columns or the names should be the
     * same like in "projekt.csv"</b>. The BufferedReader object due to
     * readLine() method writes the rows from file in String object variable.
     * This variable is split for the array of Strings which contains all
     * Film instances fields.
     * This method needs to filter array, because of some mistakes it could have:
     * array could have the names of columns this file, the rows could have empty field.
     * @param path          a String of file path.
     * @param splitter      a String which represents the separating char.
     * @param sizeOfArray   specify the capacity and the number of Film objects array.
     * @return Film[]       the Film class objects array.
     * */
    public static Film[] downloadMovies(String path, String splitter, int sizeOfArray){
        Film[] arr = new Film[sizeOfArray];
        int numOfElements = 0;
        while(numOfElements<sizeOfArray){
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while (((line = br.readLine()) != null) & (numOfElements<sizeOfArray)) {
                    String[] values = line.split(splitter);
                    boolean cautionOfToLittleArray = false;
                    int penultimateNumberOfArray = values.length - 1;
                    if (values.length < 3 || values[penultimateNumberOfArray].contains("\""))
                        cautionOfToLittleArray = true;
                    if (values.length > 2) {
                        if ((!Objects.equals(values[1], "movie")) & (!values[0].isEmpty()) & (!values[1].isEmpty())
                                & (!values[2].isEmpty()) & (!cautionOfToLittleArray)) {
                            convertToFilmArray(arr, numOfElements, values, penultimateNumberOfArray);
                            numOfElements++;
                        }
                    }
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return arr;
    }

    private static void convertToFilmArray(Film[] arr, int numOfElements, String[] values, int penultimateNumberOfArray) {
        try {
            int column0 = Integer.parseInt(values[0]);
            StringBuilder column1 = new StringBuilder();
            for(int i = 1; i< values.length-1; i++){
                column1.append(values[i]);
            }
            double column2 = Double.parseDouble(values[penultimateNumberOfArray]);
            arr[numOfElements] = new Film(column0, column1.toString(), column2);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
}