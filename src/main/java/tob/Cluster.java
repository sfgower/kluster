package tob;

import java.util.*;

/**
 * Created by Stefan on 2/8/2015.
 */
public class Cluster {

    private List<Node> nodes;
    private int occurrences;
    private Node first;
    private Node last;
    private Cluster parent;
    private Set<Cluster> children = new HashSet<Cluster>();

    public Cluster()
    {
     nodes = Collections.EMPTY_LIST;
     first = null;
     last = null;
     occurrences=0;
    }

    public Cluster(List<Node> nodes) {
        this.nodes = nodes;
        int len = nodes.size();
        first = nodes.get(0);
        last = nodes.get(len-1);
        occurrences = last.getOccurrences();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int size()
    {
     return nodes.size();
    }

    public int getOccurrences()
    {
     return occurrences;
    }

    public boolean match(long[] items)
    {
     int len = items.length;
     if (len != nodes.size())
       return false;
     int position = 0;
     Iterator<Node> scan = nodes.iterator();
     while (scan.hasNext())
     {
      if (scan.next().getItemId()!= items[position++])
          return false;
     }
     return true;
    }

    public Node getFirst()
    {
     return first;
    }

    public Node getLast()
    {
     return last;
    }

    public Cluster getParent() {
        return parent;
    }

    public void setParent(Cluster parent) {
        this.parent = parent;
    }

    public void addChild(Cluster c)
    {
     children.add(c);
    }

    public Set<Cluster> getChildren()
    {
     return children;
    }

    public boolean isRoot()
    {
     return occurrences==0;
    }

    public Node getLastNodeOfParentCluster()
    {
     Node hook = null;

     for (Node n: this.getNodes())
     {
      if (n.IsEndPoint())
      {
       if (n != last)
         hook=n;
      }

     }
     return hook;
    }

    public int getNumberOfChildren()
    {
     return children.size();
    }

}
