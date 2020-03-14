import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDictionaryTest {
    @Test
    public void demo() {
        int testSize = 5;
        ArrayDictionary dict = new ArrayDictionary(testSize);
        assertTrue(dict.add(2, 82));
        assertTrue(dict.add(4, 84));
        assertTrue(dict.add(7, 87));
        System.out.println(dict);
    }

    @Test
    public void remove() {
        int[] setKeys = {0,1,2,4,1,5};

        int[][] testSetsNC = {
                {0,103},
                {1,105}
        };

        int[][] testSetsWC = {
                {0,103},
                {1,105},
                {4,407}
        };

        boolean[] expected = {false,true,false,true,true,false};

        ArrayDictionary empty = new ArrayDictionary(0);

        ArrayDictionary noCollision = new ArrayDictionary(2);
        for (int i = 0; i < testSetsNC.length; i++) {
            noCollision.add(testSetsNC[i][0],testSetsNC[i][1]);
        }

        ArrayDictionary collision = new ArrayDictionary(2);
        for (int i = 0; i < testSetsWC.length; i++) {
            collision.add(testSetsWC[i][0],testSetsWC[i][1]);
        }

        for (int i = 0; i < 6; i++) {
            boolean actual;
            if (i == 0)
                actual = empty.remove(setKeys[i]);
            else if (i == 1 || i == 2)
                actual = noCollision.remove(setKeys[i]);
            else
                actual = collision.remove(setKeys[i]);

            assertEquals(expected[i],actual);
        }

    }

    @Test
    public void contains() {
        int[] capacity = {0,1,2,3,3};

        int[][] setKeys = {
                {-1, 0, 1},
                {2,0},
                {0,1,2,3},
                {3,1},
                {1,4,7,8}
        };

        int[][] testSets = {
                {0,103},
                {1,105},
                {2,206},
                {4,407}
        };

        boolean[][] expected = {
                {false,false,false},
                {false,true},
                {true,true,false,false},
                {false,true},
                {true,true,false,false}
        };

        for (int i = 0; i < 5; i++) {
            ArrayDictionary testDictionary = new ArrayDictionary(capacity[i]);

            for (int j = 0; j < i; j++) {
                testDictionary.add(testSets[j][0], testSets[j][1]);
            }

            for (int j = 0; j < setKeys[i].length; j++) {
                boolean actual = testDictionary.contains(setKeys[i][j]);
                assertEquals(expected[i][j], actual);
            }
        }
    }
}