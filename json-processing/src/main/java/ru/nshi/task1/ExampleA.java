package ru.nshi.task1;

public class ExampleA implements Sorting {
    @Override
    public int[] sort(int[] unsorted) {
        if (unsorted==null) {
            throw new NullPointerException("Array is null");
        }

        for (int i = 0; i < unsorted.length-1; i++) {
            for (int j = 0; j < unsorted.length - i - 1; j++) {
                if (unsorted[j]>unsorted[j+1]){
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j+1];
                    unsorted[j+1] = temp;
                }
            }
        }
        return unsorted;
    }
}
