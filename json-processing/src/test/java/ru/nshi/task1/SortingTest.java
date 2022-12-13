package ru.nshi.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nshi.task1.ExampleA;
import ru.nshi.task1.ExampleB;
import ru.nshi.task1.Sorting;

class SortingTest {

    Sorting sortBubble = new ExampleA();
    @Test
    void sortBubble(){
        Assertions.assertArrayEquals(sortBubble.sort(new int[]{5,3,4,2,1}), new int[]{1,2,3,4,5});
        Assertions.assertArrayEquals(sortBubble.sort(new int[]{}), new int[]{});
        NullPointerException excNull = Assertions.assertThrows(NullPointerException.class, () -> {sortBubble.sort(null);});
        Assertions.assertEquals(excNull.getMessage(),"Array is null" );
    }
    Sorting sortChoice = new ExampleB();
    @Test
    void sortChoice(){
        Assertions.assertArrayEquals(sortChoice.sort(new int[]{5,3,4,2,1}), new int[]{1,2,3,4,5});
        Assertions.assertArrayEquals(sortChoice.sort(new int[]{}), new int[]{});
        NullPointerException excNull = Assertions.assertThrows(NullPointerException.class, () -> {sortChoice.sort(null);});
        Assertions.assertEquals(excNull.getMessage(),"Array is null" );
    }
}