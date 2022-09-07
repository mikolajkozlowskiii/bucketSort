package bucket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
/**
 * Class <code>Filter</code> initialize the array of <code>Film</code> class objects.
 * This class implements <code>downloadMovies</code> method.
 * */

public class Filter {
    /**
     * This method is used to download and filter the movies from csv file which row can be
     * cast to <code>Film</> objects class.
     * Specifically file "projekt.csv", because this method uses the mechanism which probably will works only
     * in this file for example <code>if((!Objects.equals(values[1], "movie")...)</code> which means not to add to array
     * the first row of "projekt.csv" with the names of columns. <b>It is highly recommended to use this class only for
     * csv files which rows can be cast Film objects class and also the first or other row in this file
     * has not got any names for columns or the names should be the same like in "projekt.csv"</b>
     * The BufferedReader object due to readLine() method writes the rows from file in String object variable. This
     * variable is splitted for the array of Strings which contains all of the Film class object fields.
     * This method need to filter this array because of some mistakes it would have: array would have the names of
     * columns this file, the rows with empty field.
     * @param path          String of file path
     * @param splitter      String which represents the separating char
     * @param sizeOfArray   int which specify the capacity and the number of Film objects array
     * @return Film[]       This returns the Film class objects array
     * */
    public static Film[] downloadMovies(String path, String splitter, int sizeOfArray){
        Film[] arr = new Film[sizeOfArray];
        int numOfElements = 0;
        while(numOfElements<sizeOfArray){
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while (((line = br.readLine()) != null) & (numOfElements<sizeOfArray)) {
                    //System.out.println(numOfElements);
                    String[] values = line.split(splitter);
                    //System.out.println(values[1]);
                    boolean cautionOfToLittleArray = false;
                    boolean cautionOfArrLenght = false;
                    int penultimateNumberOfArray = values.length - 1;
                    if (values.length < 3 || values[penultimateNumberOfArray].contains("\""))
                        cautionOfToLittleArray = true;
                    if ((values.length > 2) & (values[penultimateNumberOfArray - 1].contains("\"")))
                        cautionOfArrLenght = true;
                    if (values.length > 2) {
                        if ((!Objects.equals(values[1], "movie")) & (!values[0].isEmpty()) & (!values[1].isEmpty())
                                & (!values[2].isEmpty()) & (!cautionOfToLittleArray)) {
                            try {
                                int number0 = Integer.parseInt(values[0]);
                                if(!cautionOfArrLenght){
                                    double number2 = Double.parseDouble(values[2]);
                                    arr[numOfElements] = new Film(number0, values[1], number2);
                                }
                                else{
                                    double number2 = Double.parseDouble(values[penultimateNumberOfArray]);
                                    StringBuilder number1 = new StringBuilder();
                                    for(int i = 1; i<values.length-1; i++){
                                        number1.append(values[i]);
                                    }
                                    arr[numOfElements] = new Film(number0, number1.toString(), number2);
                                }
                            } catch (NumberFormatException ex) {
                                ex.printStackTrace();
                            }
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
}