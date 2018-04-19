package tob.test;
import tob.Basket;

import static org.junit.Assert.*;

import tob.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 2/6/2015.
 */

/**
 * These tests mainly
 * relate to how items in a basket are
 * sorted.
 */

public class BasketTest {


    /**
     * If all the items has the same count,
     * we want to ensure a canonical ordering.
     * Specifically, we want to ensure that
     * when two items have the same count,
     * the sort order is then resolved
     * by taking the smaller of the two
     * item IDs.
     */

    @Test
        public void whatShouldHappenWhenAllItemsHaveTheSameCount()
    {
        Basket c = new Basket();
        c.addItems(4,1);
        assertEquals(1,c.getCount(4));
        c.addItems(3,1);
        assertEquals(1,c.getCount(3));
        c.addItems(2,1);
        assertEquals(1,c.getCount(2));
        c.addItems(1,1);
        assertEquals(1,c.getCount(1));
        c.sort();
        List<Item> items = c.getItems();

        assertEquals(1,items.get(0).getItemId());
        assertEquals(1,items.get(0).getCount());

        assertEquals(2,items.get(1).getItemId());
        assertEquals(1,items.get(1).getCount());

        assertEquals(3,items.get(2).getItemId());
        assertEquals(1,items.get(2).getCount());

        assertEquals(4,items.get(3).getItemId());
        assertEquals(1,items.get(3).getCount());
    }

    /**
     * This test is trivial yet important.
     * We want to impose a standard ranking
     * on the order of items, even if the count of
     * each item is the same.
     * </p>
     * So in the previous test, we added items
     * into a basket in a descending order.
     * Here we do the same thing in the reverse order
     * But the results should be the same.
     */

    @Test
    public void whatShouldHappenWhenAllItemsHaveTheSameCount2()
    {
        Basket c = new Basket();

        c.addItems(1,1);
        c.addItems(2,1);
        c.addItems(3,1);
        c.addItems(4,1);

        c.sort();
        List<Item> items = c.getItems();

        assertEquals(1,items.get(0).getItemId());
        assertEquals(1,items.get(0).getCount());

        assertEquals(2,items.get(1).getItemId());
        assertEquals(1,items.get(1).getCount());

        assertEquals(3,items.get(2).getItemId());
        assertEquals(1,items.get(2).getCount());

        assertEquals(4,items.get(3).getItemId());
        assertEquals(1,items.get(3).getCount());
    }



    @Test
    public void basketTest()
    {
     Basket c = new Basket();
     c.addItems(1,2);
     assertEquals(2,c.getCount(1));
     c.addItems(1,3);
     assertEquals(5,c.getCount(1));
     c.addItems(1,-10);
     assertEquals(5,c.getCount(1));
     c.addItems(2,4);
     c.addItems(2,10);
     assertEquals(14,c.getCount(2));
     c.addItems(3,1);
     assertEquals(1,c.getCount(3));
    }





    @Test
    public void sortbasketTest()
    {
        Basket c = new Basket();
        c.addItems(1,2);
        assertEquals(2, c.getCount(1));
        c.addItems(1, 3);
        assertEquals(5, c.getCount(1));
        c.addItems(1, -10);
        assertEquals(5, c.getCount(1));
        c.addItems(2, 4);
        c.addItems(2,10);
        assertEquals(14, c.getCount(2));
        c.addItems(3, 1);
        assertEquals(1, c.getCount(3));
        c.sort();
        List<Item> sortedItems = c.getItems();
        assertEquals(3,sortedItems.size());
        assertEquals(14,sortedItems.get(0).getCount());
        assertEquals(5,sortedItems.get(1).getCount());
        assertEquals(1,sortedItems.get(2).getCount());
    }

    /**
     * Sort multiple baskets per the first phase
     * of the FP-Growth clustering algorithm.
     */

    @Test
    public void sortbasketsTest()
    {
        Basket c1 = new Basket();
        c1.setBasketId(1);
        c1.addItems(1, 2);
        c1.addItems(2,5);
        c1.addItems(3, 14);

        Basket c2 = new Basket();
        c2.setBasketId(2);
        c2.addItems(4,50);
        c2.addItems(2, 5);
        c2.addItems(3,14);

        Basket c3 = new Basket();
        c3.setBasketId(3);
        c3.addItems(6,49);
        c3.addItems(2,5);
        c3.addItems(3, 14);

        Basket c4 = new Basket();
        c4.setBasketId(4);
        c4.addItems(6,49);
        c4.addItems(2,5);
        c4.addItems(3,14);
        c4.addItems(7, 1);

        List<Basket> baskets = new ArrayList<Basket>();
        baskets.add(c1);
        baskets.add(c2);
        baskets.add(c3);
        baskets.add(c4);

        Basket.sortbaskets(baskets);

        assertEquals(2, baskets.get(0).getBasketId());
        assertEquals(3, baskets.get(1).getBasketId());
        assertEquals(4, baskets.get(2).getBasketId());
        assertEquals(1, baskets.get(3).getBasketId());





    }

}
