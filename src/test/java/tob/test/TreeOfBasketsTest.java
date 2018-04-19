package tob.test;

import org.junit.Ignore;
import org.junit.Test;
import tob.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Stefan on 2/6/2015.
 */

public class TreeOfBasketsTest {


    @Test
    public void noFrequentItemsTest() {


        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(2, 10);
        c1.addItems(3, 25);


        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(1, 5);
        c2.addItems(2, 10);
        c2.addItems(3, 25);


        List<Basket> baskets = new ArrayList<Basket>();

        baskets.add(c1);
        baskets.add(c2);


        BasketTree tree = new BasketTree();
        tree.grow(baskets);




        // Asking for frequent items greater than 100 but there are none.

        FrequentItemsCollection frequentItemsCollection = tree.extractFrequentItems(100);
        assertEquals(0, frequentItemsCollection.size());


    }

    /**
     * We have the exact same basket three times, with each item occurring only once,
     * so we should get a basket of these two items
     * with a count equal to three for frequent item set.
     */

    @Test
    public void threeEqualBaskets() {


        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(2, 1);
        c1.addItems(3, 1);

        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(2, 1);
        c2.addItems(3, 1);

        Basket c3 = new Basket();
        c3.setBasketId(3);
        c3.addItems(2, 1);
        c3.addItems(3, 1);


        List<Basket> baskets = new ArrayList<Basket>();

        baskets.add(c1);
        baskets.add(c2);
        baskets.add(c3);


        BasketTree tree = new BasketTree();
        tree.grow(baskets);

        FrequentItemsCollection collection = tree.extractFrequentItems(1);

        assertTrue(collection.contains(new long[]{2,3},3));
        assertEquals(1,collection.size());

    }


    @Test
    public void trivialClusterTest() {


        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(2, 10);
        c1.addItems(3, 25);


        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(1, 5);
        c2.addItems(2, 10);
        c2.addItems(3, 25);


        List<Basket> baskets = new ArrayList<Basket>();

        baskets.add(c1);
        baskets.add(c2);


        BasketTree tree = new BasketTree();
        tree.grow(baskets);


        FrequentItemsCollection collection = tree.extractFrequentItems(1);

        assertTrue(collection.contains(new long[]{3},50));
        assertTrue(collection.contains(new long[]{3,2},20));
        assertTrue(collection.contains(new long[]{3,2,1},5));
        assertEquals(3,collection.size());

    }


    @Test
    public void trivialClusterTest2() {


        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(2, 10);
        c1.addItems(3, 25);


        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(1, 5);
        c2.addItems(2, 10);
        c2.addItems(3, 25);

        Basket c3 = new Basket();
        c3.setBasketId(3);
        c3.addItems(4, 5);
        c3.addItems(2, 10);
        c3.addItems(3, 25);

        List<Basket> baskets = new ArrayList<Basket>();

        baskets.add(c1);
        baskets.add(c2);
        baskets.add(c3);

        BasketTree tree = new BasketTree();
        tree.grow(baskets);


        FrequentItemsCollection collection = tree.extractFrequentItems(1);

        assertTrue(collection.contains(new long[]{3},75));
        assertTrue(collection.contains(new long[]{3,2},30));
        assertTrue(collection.contains(new long[]{3,2,4},5));
        assertTrue(collection.contains(new long[]{3,2,1},5));
        assertEquals(4,collection.size());



    }


    @Test
    public void trivialClusterTest3() {


        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(2, 10);
        c1.addItems(3, 25);

        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(1, 5);
        c2.addItems(2, 10);
        c2.addItems(3, 25);

        Basket c3 = new Basket();
        c3.setBasketId(3);
        c3.addItems(7, 2);
        c3.addItems(1, 5);
        c3.addItems(2, 10);
        c3.addItems(3, 25);


        List<Basket> baskets = new ArrayList<Basket>();

        baskets.add(c1);
        baskets.add(c2);
        baskets.add(c3);


        BasketTree tree = new BasketTree();
        tree.grow(baskets);

        FrequentItemsCollection collection = tree.extractFrequentItems(1);
        assertTrue(collection.contains(new long[]{3},75));
        assertTrue(collection.contains(new long[]{3,2},30));
        assertTrue(collection.contains(new long[]{3,2,1},10));
        assertTrue(collection.contains(new long[]{3,2,1,7},2));
        assertEquals(4,collection.size());

    }





    @Test
    public void firstGrowthTest() {
        Basket c1 = new Basket();
        c1.setBasketId(1);
        assertEquals(0, c1.size());
        c1.addItems(1, 2);
        assertTrue(c1.size() == 1);

        c1.addItems(2, 5);
        assertEquals(2, c1.size());
        c1.addItems(3, 14);
        assertEquals(3, c1.size());

        // Should be {3,2,1}

        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(4, 50);
        c2.addItems(2, 5);
        c2.addItems(3, 14);

        // Should be { 4,3,2}

        Basket c3 = new Basket();
        c3.setBasketId(3);
        c3.addItems(6, 49);
        c3.addItems(2, 5);
        c3.addItems(3, 14);
//
        Basket c4 = new Basket();
        c4.setBasketId(4);
        c4.addItems(6, 49);
        c4.addItems(2, 5);
        c4.addItems(3, 14);
        c4.addItems(7, 1);

        Basket c5 = new Basket();
        c5.setBasketId(5);
        c5.addItems(6, 49);
        c5.addItems(2, 5);
        c5.addItems(3, 14);
        c5.addItems(8, 1);

        Basket c6 = new Basket();
        c6.setBasketId(6);
        c6.addItems(6, 49);
        c6.addItems(2, 5);
        c6.addItems(3, 14);
        c6.addItems(9, 1);

        Basket c7 = new Basket();
        c7.setBasketId(7);
        c7.addItems(6, 100);
        c7.addItems(2, 5);
        c7.addItems(4, 14);
        c7.addItems(9, 13);

        Basket c8 = new Basket();
        c8.setBasketId(8);
        c8.addItems(6, 100);
        c8.addItems(2, 5);
        c8.addItems(4, 14);
        c8.addItems(8, 13);

        Basket c9 = new Basket();
        c9.setBasketId(9);
        c9.addItems(6, 100);
        c9.addItems(2, 5);
        c9.addItems(4, 14);
        c9.addItems(8, 13);
        c9.addItems(101, 1);

        Basket c10 = new Basket();
        c10.setBasketId(10);
        c10.addItems(13, 33);

        Basket c11 = new Basket();
        c11.setBasketId(9);
        c11.addItems(15, 100);
        c11.addItems(2, 5);
        c11.addItems(8, 13);

        Basket c12 = new Basket();
        c12.setBasketId(9);
        c12.addItems(15, 100);
        c12.addItems(2, 5);
        c12.addItems(8, 13);

        Basket c13 = new Basket();
        c13.setBasketId(9);
        c13.addItems(15, 100);
        c13.addItems(2, 5);
        c13.addItems(8, 13);
        c13.addItems(99, 1);

        Basket c14 = new Basket();
        c14.setBasketId(9);
        c14.addItems(15, 100);
        c14.addItems(2, 5);
        c14.addItems(8, 13);
        c14.addItems(99, 1);


        List<Basket> baskets = new ArrayList<Basket>();
        baskets.add(c1);
        baskets.add(c2);
        baskets.add(c3);
        baskets.add(c4);
        baskets.add(c5);
        baskets.add(c6);
        baskets.add(c7);
        baskets.add(c8);
        baskets.add(c9);
        baskets.add(c10);
        baskets.add(c11);
        baskets.add(c12);
        baskets.add(c13);
        baskets.add(c14);

        int k = 10;
        while (k-- > 0) {
            Basket basket = new Basket();
            basket.addItems(15, 2);
            basket.addItems(16, 3);
            basket.addItems(17, 14);
            baskets.add(basket);
        }


        BasketTree tree = new BasketTree();
        tree.grow(baskets);

        long path1[] = new long[]{3, 2, 1};
        assertTrue(tree.checkPath(path1));
        long path2[] = new long[]{4, 3, 2};
        assertTrue(tree.checkPath(path2));
        long path3[] = new long[]{6, 3, 2, 7};
        assertTrue(tree.checkPath(path3));
        long path4[] = new long[]{6, 3, 2, 8};
        assertTrue(tree.checkPath(path4));
        long path5[] = new long[]{6, 3, 2, 9};
        assertTrue(tree.checkPath(path5));
        long path6[] = new long[]{6, 4, 9, 2};
        assertTrue(tree.checkPath(path6));
        long path7[] = new long[]{6, 4, 8, 2};
        assertTrue(tree.checkPath(path7));
        long path8[] = new long[]{6, 4, 8, 2, 101};
        assertTrue(tree.checkPath(path8));
        long path9[] = new long[]{13};


        FrequentItemsCollection collection = tree.extractFrequentItems(1);

        assertTrue(collection.contains(new long[]{15}, 400));
        assertTrue(collection.contains(new long[]{15, 8}, 52));
        assertTrue(collection.contains(new long[]{15, 8, 2}, 20));
        assertTrue(collection.contains(new long[]{15, 8, 2, 99}, 2));
        assertTrue(collection.contains(new long[]{13}, 33));
        assertTrue(collection.contains(new long[]{6}, 496));
        assertTrue(collection.contains(new long[]{6, 4}, 42));
        assertTrue(collection.contains(new long[]{6, 4, 9}, 13));
        assertTrue(collection.contains(new long[]{6, 4, 9, 2}, 5));
        assertTrue(collection.contains(new long[]{6, 4, 8}, 26));
        assertTrue(collection.contains(new long[]{6, 4, 8, 2}, 10));
        assertTrue(collection.contains(new long[]{6, 4, 8, 2, 101}, 1));
        assertTrue(collection.contains(new long[]{6, 3}, 56));
        assertTrue(collection.contains(new long[]{6, 3, 2}, 20));
        assertTrue(collection.contains(new long[]{6, 3, 2, 9}, 1));
        assertTrue(collection.contains(new long[]{6, 3, 2, 8}, 1));
        assertTrue(collection.contains(new long[]{6, 3, 2, 7}, 1));
        assertTrue(collection.contains(new long[]{4}, 50));
        assertTrue(collection.contains(new long[]{4, 3}, 14));
        assertTrue(collection.contains(new long[]{4, 3, 2}, 5));
        assertTrue(collection.contains(new long[]{3}, 14));
        assertTrue(collection.contains(new long[]{3, 2}, 5));
        assertTrue(collection.contains(new long[]{3, 2, 1}, 2));
        assertTrue(collection.contains(new long[]{17}, 140));
        assertTrue(collection.contains(new long[]{17, 16}, 30));
        assertTrue(collection.contains(new long[]{17, 16, 15}, 20));

        assertEquals(26, collection.size());


    }


    @Test
    public void growthTest2() {


        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(2, 10);
        c1.addItems(3, 25);

        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(1, 5);
        c2.addItems(2, 10);
        c2.addItems(3, 25);

        Basket c3 = new Basket();
        c3.setBasketId(3);
        c3.addItems(1, 5);
        c3.addItems(2, 10);
        c3.addItems(3, 25);

        Basket c4 = new Basket();
        c4.setBasketId(4);
        c4.addItems(9, 5);
        c4.addItems(2, 10);
        c4.addItems(3, 25);

        List<Basket> baskets = new ArrayList<Basket>();
        baskets.add(c1);
        baskets.add(c2);
        baskets.add(c3);
        baskets.add(c4);


        BasketTree tree = new BasketTree();
        tree.grow(baskets);

        FrequentItemsCollection collection = tree.extractFrequentItems(1);

        assertTrue(collection.contains(new long[]{3}, 100));
        assertTrue(collection.contains(new long[]{3, 2}, 40));
        assertTrue(collection.contains(new long[]{3, 2, 9}, 5));
        assertTrue(collection.contains(new long[]{3, 2, 1}, 10));
        assertEquals(4, collection.size());


    }



    @Test
    public void basicTest2() {


        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(1, 5);
        c1.addItems(2, 10);
        c1.addItems(3, 25);

        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(1, 25);
        c2.addItems(2, 10);
        c2.addItems(3, 5);

        Basket c3 = new Basket();
        c3.setBasketId(3);
        c3.addItems(7, 1);
        c3.addItems(1, 5);
        c3.addItems(2, 10);
        c3.addItems(3, 25);

        Basket c4 = new Basket();
        c4.setBasketId(4);
        c4.addItems(1, 25);
        c4.addItems(2, 10);
        c4.addItems(3, 5);
        c4.addItems(7, 2);


        List<Basket> baskets = new ArrayList<Basket>();
        baskets.add(c1);
        baskets.add(c2);
        baskets.add(c3);
        baskets.add(c4);


        BasketTree tree = new BasketTree();
        tree.grow(baskets);


        FrequentItemsCollection collection = tree.extractFrequentItems(1);

        assertTrue(collection.contains(new long[]{3},50));
        assertTrue(collection.contains(new long[]{3,2},20));
        assertTrue(collection.contains(new long[]{3,2,1},10));
        assertTrue(collection.contains(new long[]{3,2,1,7},1));
        assertTrue(collection.contains(new long[]{1},50));
        assertTrue(collection.contains(new long[]{1,2},20));
        assertTrue(collection.contains(new long[]{1,2,3},10));
        assertTrue(collection.contains(new long[]{1,2,3,7},2));
        assertEquals(8,collection.size());

        FrequentItemsCollection collection2 = tree.extractFrequentItems(50);
        assertTrue(collection2.contains(new long[]{3},50));
        assertTrue(collection2.contains(new long[]{1},50));
        assertEquals(2,collection2.size());

    }


    private void generateAssertions(FrequentItemsCollection collection)
    {
        for (FrequentItems frequentItems: collection.getFrequentItemsList())
        {
            long count = frequentItems.getCount();
            String s = String.format("assertTrue(collection.contains(new long[]{%s},%d));",frequentItems.toString(),count);
            System.out.println(s);

        }
        System.out.println(String.format("assertEquals(%d,collection.size());",collection.size()));
    }
}



