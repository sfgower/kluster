package tob;

import java.util.Comparator;

/**
 * Created by Stefan on 2/6/2015.
 */
public class Item {

    public static Comparator<Item> ITEM_COMPARATOR_BY_COUNT = new Comparator<Item>() {
        public int compare(Item x, Item y) {
            return Integer.compare(y.getCount(),x.getCount());
        }

    };

    private long itemId;
    int count;

    public Item(long itemId,int count)
    {
     this.itemId = itemId;
     this.count = count;

    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
