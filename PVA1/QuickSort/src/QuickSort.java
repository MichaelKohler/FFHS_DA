import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class QuickSort {

    public static void main(String[] args) {
        final int MIN = 0;
        final int MAX = 1000;

        int[] rndArray = new int[MAX];

        for (int i = 0; i < MAX; i++) {
            rndArray[i] = (int) (Math.random() * MAX) + 1;
        }
        HashMap<Long, Integer> map = new HashMap<>();
        for (int M = 0; M < MAX; M++) {
            int[] tempArray = rndArray.clone();
            long startTime = System.nanoTime();
            quickSort(tempArray, M, MIN, MAX - 1);
            long stopTime = System.nanoTime();

            sortCheck(tempArray);
            System.out.println("M = " + M + ": " + (stopTime - startTime)
                    + " ns");

            map.put(stopTime - startTime, M);
        }

        Iterator<Long> iter = map.keySet().iterator();
        Long min = Long.MAX_VALUE, tmp;
        while (iter.hasNext()) {
            tmp = iter.next();
            if (min > tmp)
                min = tmp;
        }

        System.out.println("MINIMUM: " + map.get(min) + ", " + min + " ns");

    }

    private static void quickSort(int[] rndArray, int M, int left, int right) {

        if ((left + right) / 2 <= M) {
            insertionSort(rndArray, left, right + 1);
            return;
        }

        int i = left, j = right;
        int pivot = rndArray[left + (right - left) / 2];

        while (i <= j) {
            while (rndArray[i] < pivot) {
                i++;
            }
            while (rndArray[j] > pivot) {
                j--;
            }

            if (i <= j) {
                exchange(rndArray, i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSort(rndArray, M, left, j);
        if (i < right)
            quickSort(rndArray, M, i, right);

    }

    private static void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private static void insertionSort(int[] rndArray, int left, int right) {
        int temp;
        for (int i = left; i < right; i++) {
            temp = rndArray[i];
            int j = i;
            while (j > 0 && rndArray[j - 1] > temp) {
                rndArray[j] = rndArray[j - 1];
                j--;
            }
            rndArray[j] = temp;
        }

    }

    private static void sortCheck(int[] rndArray) {
        List<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < rndArray.length; index++) {
            intList.add(rndArray[index]);
        }

        System.out.println("sorted: " + isSorted(intList));
    }

    public static <T extends Comparable> boolean isSorted(List<T> listOfT) {
        T previous = null;
        for (T t : listOfT) {
            if (previous != null && t.compareTo(previous) < 0)
                return false;
            previous = t;
        }
        return true;
    }

}
// END OF CODE