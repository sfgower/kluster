package tob;

import java.util.*;

/**
 * Created by Stefan on 2/6/2015.
 */



public class Basket {

    public static Integer ZERO = new Integer(0);

    public static Comparator<Basket> BASKET_COMPARATOR = new Comparator<Basket>() {
        public int compare(Basket y, Basket x) {

            Iterator<Item> scan1 = x.getItems().iterator();
            Iterator<Item> scan2 = y.getItems().iterator();
            int comparison;
            while (scan1.hasNext() && scan2.hasNext())
            {
             comparison = Integer.compare(scan1.next().getCount(),scan2.next().getCount());

             if (comparison != 0)
                 return comparison;
            }
            if (scan1.hasNext())
                return -1;
            else
            if (scan2.hasNext())
                return 1;
            else
                return 0;

        }

    };

    private int basketId = 0;
    private HashMap<Long, Integer> map = new HashMap<Long,Integer>();
    private List<Item> items = new ArrayList<Item>();

    /**
     * Create a basket, initially empty.
     */

    public Basket() {

    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    /**
     * Add this number of items to the basket.
     * @param itemId
     * @param count
     */

    public void addItems(long itemId, int count) {
        if (count >= 0) {
            int newCount = map.getOrDefault(itemId, map.getOrDefault(itemId, ZERO)) + count;
            map.put(itemId, newCount);
        }
    }

    /**
     * Get the number of times this item
     * is contained in the basket.
     * @param itemId
     * @return count
     */

    public int getCount(long itemId)
    {
     return map.getOrDefault(itemId,ZERO);
    }

    /**
     * Sort the items in the basket in descending order.
     */

    public void sort()
    {
     items.clear();
     for (Map.Entry<Long,Integer> entry : map.entrySet())
         items.add(new Item(entry.getKey(),entry.getValue()));
     Collections.sort(items, Item.ITEM_COMPARATOR_BY_COUNT);
    }

    public int size()
    {
     return map.size();
    }

    public List<Item> getItems()
    {
     return items;
    }


    public static void sortbaskets(List<Basket> baskets)
    {
     sortItemsWithinEachBasket(baskets);
     Collections.sort(baskets, BASKET_COMPARATOR);

    }

    private static void sortItemsWithinEachBasket(List<Basket> baskets)
    {
        for (Basket c: baskets) {
            c.sort();
        }
    }








}