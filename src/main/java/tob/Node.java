package tob;

import java.util.*;

/**
 * Created by Stefan on 2/6/2015.
 */


public class Node {

    private long itemId;
    private int occurrences = 0;
    private Map<Long, Node> children = null;
    private Node parent = null;

    public Node(long itemId) {
        this.itemId = itemId;

    }

    public Node getChild(long itemId) {
        if (children == null)
            return null;
        else
            return children.get(itemId);
    }

    public Collection<Node> getChildren() {
        if (children == null)
            return Collections.EMPTY_SET;
        else
            return children.values();
    }

    /**
     * Add a child to this node.
     * Returns the current child if such
     * a node already exists.
     * @param itemId
     * @return Node
     */

    public Node addChild(long itemId)
    {
     return addChild(itemId,null);
    }

    /**
     * This method takes a set so that
     * a client, like GTree, can add children
     * but also keep track of any nodes created.
     * @param itemId
     * @param nodeSet
     * @return Node
     */

    public Node addChild(long itemId,Set<Node> nodeSet) {
        if (children == null)
            children = new HashMap<Long, Node>();
        Node n = children.get(itemId);
        if (n == null) {
            n = new Node(itemId);
            n.setParent(this);
            children.put(itemId, n);
            if (nodeSet!=null)
                nodeSet.add(n);
        }
        n.occurrences++;
        return n;

    }

    public long getItemId() {
        return itemId;
    }

    public int getOccurrences() {
        return occurrences;
    }


    /**
     * Note: this method is probably the hardest to
     * understand. It is also critical if one wants
     * to understand how clusters are created, and then
     * separated from other clusters.
     * </P>
     * A node can only be an endpoint if and only if:
     * <ul>
     *   <li>the node has no children</li>
     *   <li>the node has more than one child</li>
     *   <li>the node has a child, but the number of occurrences of
     *       the child differs from the number of occurrence of the parent</li>
     * </ul>
     * Endpoints are important as they define the data that goes
     * into a cluster. In particular, the entire path from the root to the endpoing goes into
     * a cluser, excluding the root itself
     * </P>
     * Note: clusters can also be filtered by the number of their occurrences.
     *
     * @return boolean
     */

    public boolean IsEndPoint() {
        if (children == null)
            return true;
        int numberOfChildren = children.size();
        if (numberOfChildren >= 2)
            return true;
        if (numberOfChildren == 1) {
            if (this.occurrences != children.values().iterator().next().getOccurrences())
                return true;
        }

        return false;

    }

    public Node getParent() {
        return parent;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public List<Node> getPath() {
        List<Node> path = new LinkedList<Node>();
        Node n = this;
        while (n.hasParent()) {
            path.add(n);
            n = n.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }
}



