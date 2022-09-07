package bucket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class Filter {
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