package tob;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by TRINITY on 3/20/18.
 */
public class FrequentItemsCollection {

    List<FrequentItems> frequentItemsList = new LinkedList<>();

    public FrequentItemsCollection()
    {

    }

    public void add(FrequentItems frequentItems)
    {
     frequentItemsList.add(frequentItems);
    }

    public Iterator<FrequentItems> scan()
    {
     return frequentItemsList.iterator();
    }

    public List<FrequentItems> getFrequentItemsList() {
        return frequentItemsList;
    }

    public boolean contains(long[] path)
    {
     return this.getFrequentItemsByPath(path) != null;
    }

    public boolean contains(long[] path,int exactCount)
    {
     FrequentItems frequentItems = getFrequentItemsByPath(path);
     return frequentItems == null ? false : frequentItems.getCount() >= exactCount;
    }

    public FrequentItems getFrequentItemsByPath(long[] path)
    {
        for (FrequentItems frequentItems: this.frequentItemsList)
        {
            if (Arrays.equals(path,frequentItems.getPath()))
            {
                return frequentItems;
            }
        }
        return null;
    }



    public int size()
    {
     return frequentItemsList.size();
    }
}
