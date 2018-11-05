package lesson1;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class Sorts {

    public static int compare(String first, String second){      // for comparing by two fields(word,number);
        try {
            if (first.split(" ")[0].compareTo(second.split(" ")[0]) > 0) return 1;
            else if (first.split(" ")[0].compareTo(second.split(" ")[0]) == 0) {
                if (Integer.parseInt(first.split(" ")[1]) > Integer.parseInt(second.split(" ")[1])) return 1;
                else return 0;
            } else return -1;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return first.compareTo(second);
    }

    public static <T extends Comparable<T>> void insertionSort(T[] elements) {
        for (int i = 1; i < elements.length; i++) {
            T current = elements[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (elements[j].compareTo(current) > 0) elements[j+1] = elements[j];
                else break;
            }
            elements[j+1] = current;
        }
    }


    public static void insertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int current = elements[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (elements[j] > current) elements[j+1] = elements[j];
                else break;
            }
            elements[j+1] = current;
        }
    }

    private static void merge(int[] elements, int begin, int middle, int end) {
        int[] left = Arrays.copyOfRange(elements, begin, middle);
        int[] right = Arrays.copyOfRange(elements, middle, end);
        int li = 0, ri = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li] <= right[ri])) {
                elements[i] = left[li++];
            }
            else {
                elements[i] = right[ri++];
            }
        }
    }

    private static void mergeSort(int[] elements, int begin, int end) {
        if (end - begin <= 1) return;
        int middle = (begin + end) / 2;
        mergeSort(elements, begin, middle);
        mergeSort(elements, middle, end);
        merge(elements, begin, middle, end);
    }

    public static void mergeSort(int[] elements) {
        mergeSort(elements, 0, elements.length);
    }

    private static void heapify(int[] elements, int start, int length) {
        int left = 2 * start + 1;
        int right = left + 1;
        int max = start;
        if (left < length && elements[left] > elements[max]) {
            max = left;
        }
        if (right < length && elements[right] > elements[max]) {
            max = right;
        }
        if (max != start) {
            int temp = elements[max];
            elements[max] = elements[start];
            elements[start] = temp;
            heapify(elements, max, length);
        }
    }

    private static void buildHeap(int[] elements) {
        for (int start = elements.length / 2 - 1; start >= 0; start--) {
            heapify(elements, start, elements.length);
        }
    }

    public static void heapSort(int[] elements) {
        buildHeap(elements);
        for (int j = elements.length - 1; j >= 1; j--) {
            int temp = elements[0];
            elements[0] = elements[j];
            elements[j] = temp;
            heapify(elements, 0, j);
        }
    }

    private static final Random random = new Random(Calendar.getInstance().getTimeInMillis());

    private static int partition(int[] elements, int min, int max) {
        int x = elements[min + random.nextInt(max - min + 1)];
        int left = min, right = max;
        while (left <= right) {
            while (elements[left] < x) {
                left++;
            }
            while (elements[right] > x) {
                right--;
            }
            if (left <= right) {
                int temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
                left++;
                right--;
            }
        }
        return right;
    }

    private static void quickSort(int[] elements, int min, int max) {
        if (min < max) {
            int border = partition(elements, min, max);
            quickSort(elements, min, border);
            quickSort(elements, border + 1, max);
        }
    }

    public static void quickSort(int[] elements) {
        quickSort(elements, 0, elements.length - 1);
    }

    public static int[] countingSort(int[] elements, int limit) {
        int[] count = new int[limit + 1];
        for (int element: elements) {
            count[element]++;
        }
        for (int j = 1; j <= limit; j++) {
            count[j] += count[j - 1];
        }
        int[] out = new int[elements.length];
        for (int j = elements.length - 1; j >= 0; j--) {
            out[count[elements[j]] - 1] = elements[j];
            count[elements[j]]--;
        }
        return out;
    }



    private static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static int partition(String[] array, int begin, int end,boolean by2Fields) {
        int index = begin + new Random().nextInt(end - begin + 1);
        String pivot = array[index];
        swap(array, index, end);
        for (int i = index = begin; i < end; ++ i) {
            if(by2Fields) {
                if (compare(array[i], pivot) <= 0) {
                    swap(array, index++, i);
                }
            }else {
                if (array[i].compareTo(pivot) <= 0) {
                    swap(array, index++, i);
                }
            }
        }
        swap(array, index, end);
        return (index);
    }

    private static void qsort(String[] array, int begin, int end,boolean by2Fields) {
        if (end > begin) {
            int index = partition(array, begin, end,by2Fields);
            qsort(array, begin, index - 1, by2Fields);
            qsort(array, index + 1,  end, by2Fields);
        }
    }

    public static void sort(String[] array, boolean by2Fields) {
        qsort(array, 0, array.length - 1,by2Fields);
    }

}