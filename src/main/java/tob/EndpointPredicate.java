package tob;

/**
 * Created by TRINITY on 3/20/18.
 */
public class EndpointPredicate implements Predicate {

    private int count = 0;

    public EndpointPredicate(int count)
    {
     this.count = count;
    }

    @Override
    public boolean filter(Node node) {
        return node.IsEndPoint() && (node.getOccurrences() >= count);
    }
}
