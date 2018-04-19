package tob;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by TRINITY on 3/19/18.
 */

public class Filter {

    Stack<Node> stack = new Stack<>();
    Predicate predicate = null;

    public Filter(Node node,Predicate predicate)
    {
     stack.push(node);
     this.predicate = predicate;
    }

    public List<Node> apply()
    {
     List<Node> results = new ArrayList<>();
     Node current = null;
     while (! stack.isEmpty())
     {
      current = stack.pop();
      if (predicate.filter(current))
      {
       results.add(current);
      }
      for (Node child: current.getChildren())
      {
       stack.push(child);
      }
     }
      return results;
    }


}
