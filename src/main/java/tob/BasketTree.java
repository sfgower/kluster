package tob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRINITY on 3/19/18.
 */
public class BasketTree {

    private Node root = new Node(0);

    public void grow(List<Basket> basketList) {
        Basket.sortbaskets(basketList);
        addAll(basketList);
    }

    public ClusterResult extractClusters(int size)
    {
        return null;
    }

    public FrequentItemsCollection extractFrequentItems(int size)
    {
     FrequentItemsCollection frequentItemsCollection = new FrequentItemsCollection();

     Predicate predicate = new EndpointPredicate(size);
     Filter filter = new Filter(root,predicate);
     List<Node> endpoints = filter.apply();

     for (Node endpoint: endpoints)
     {
      FrequentItems frequentItems = new FrequentItems(endpoint);
      frequentItemsCollection.add(frequentItems);
     }
     return frequentItemsCollection;
    }



    public Node getNodeByPath(long[] path) {
        Node current = root;
        for (long n : path) {
            current = current.getChild(n);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public boolean checkPath(long[] path) {
        return (getNodeByPath(path) != null);
    }

    private void addAll(List<Basket> baskets) {
        for (Basket basket : baskets) {
            addBasket(basket);
        }
    }

    private void addBasket(Basket basket) {
        Node current = root;
        for (Item item : basket.getItems()) {
            current = current.addChild(item.getItemId());
            int count = current.getOccurrences();
            // todo: fix this hack
            count--;
            count = count + item.getCount();
            current.setOccurrences(count);
        }
    }
}
