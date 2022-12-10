package org.example;

public class ExampleB implements Sorting{


    @Override
    public int[] sort(int[] unsorted) {
        if (unsorted==null) {
            throw new NullPointerException("Array is null");
        }

        for (int i = 0; i < unsorted.length; i++) {
            int pos = i;
            int min = unsorted[i];
            // цикл выбора наименьшего элемента
            for (int j = i + 1; j < unsorted.length; j++) {
                if (unsorted[j] < min) {
                    pos = j;
                    min = unsorted[j];
                }
            }
            unsorted[pos] = unsorted[i];
            unsorted[i] = min;
        }
        return unsorted;
    }
}
