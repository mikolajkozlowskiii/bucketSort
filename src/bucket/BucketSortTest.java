package bucket;


import java.util.*;


public class BucketSortTest {
    public static void main(String[] args){
        final String path = "projekt.csv";
        final int[] capacity = {10000, 100000, 500000, 1000000, 15000000};
        System.out.println("BUCKET SORT");
        for (int j : capacity) {
            Film[] films = Filter.downloadMovies(path, ",", j);

            long start = System.currentTimeMillis();
            bucketSort(films);
            long stop = System.currentTimeMillis();

            try {
                new CheckSort().checkIsSorted(films);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(new Stats(films, stop - start));
        }
    }

    public static void bucketSort(Film[] arr) {
        final int[] minMax = getMinMax(arr);
        final int min = minMax[0];
        final int max = minMax[1];
        final int numOfBuckets = max-min+1;

        ArrayList<Film>[] arrayOfList = new ArrayList[numOfBuckets+1];
        //Arrays.fill(arrayOfList, new ArrayList<Film>());

        for(int i = 0; i<numOfBuckets; i++){
            arrayOfList[i] = new ArrayList<>();
        }

        for (Film film : arr) {
            int bucket = (int) film.getRating();
            arrayOfList[bucket - min].add(film);
        }

        int x = 0;
        for(int i = 0; i < numOfBuckets; i++){
            for(int j = 0; j < arrayOfList[i].size(); j++){
                arr[x] = arrayOfList[i].get(j);
                x++;
            }
        }
    }

    private static int[] getMinMax(Film[] arr) {
        int[] minMax = new int[2];
        Arrays.fill(minMax,0);
        for (Film film : arr) {
            if (minMax[0] > (int) film.getRating())
                minMax[0] = (int) film.getRating();
            else if (minMax[1] < (int) film.getRating())
                minMax[1] = (int) film.getRating();
        }
        return minMax;
    }
}
