package tob;

import java.util.*;

/**
 * Created by TRINITY on 3/19/18.
 */
public class FrequentItems {

    public static final String COMMA = ",";
    private int count = 0;
    private List<Node> items = new ArrayList<>();
    private long[] path = null;

    public FrequentItems(Node endpoint) {
        this.count = endpoint.getOccurrences();
        Node current = endpoint;
        while (current != null) {
            if (current.getItemId() == 0) {
                break;
            }
            addItem(current);
            current = current.getParent();
            if (current == null) {
                break;
            }

        }
        Collections.reverse(items);
        path = computePath();
    }

    public long[] getPath()
    {
     return path;
    }

    /**
     * Provides a briefer representation of
     * the information found in 'items'.
     * @return array of item IDs representing the set of frequent items
     */

    public long[] computePath()
    {
     long path[] = new long[items.size()];
     int k = 0;
     Iterator<Node> scan = items.iterator();
     while (scan.hasNext())
     {
      path[k]=scan.next().getItemId();
      k++;
     }
     return path;

    }

    public void addItem(Node node) {
        items.add(node);
    }

    public int getCount() {
        return count;
    }

    public List<Node> getItems() {
        return items;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Iterator<Node> scan = items.iterator();
        Node current;
        while (scan.hasNext()) {
            current = scan.next();
            sb.append(current.getItemId());
            if (scan.hasNext()) {
                sb.append(COMMA);
            }
        }
        return sb.toString();
    }
}
