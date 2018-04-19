package tob;

import java.util.Iterator;

/**
 * Created by Stefan on 2/8/2015.
 */

public class BasketTreeUtil {

    public static final String DELIMITER = ",";
    public static final String SPACE = " ";

    public static void print(Cluster cluster)
    {
     BasketTreeUtil.print(cluster, 0);


    }
    public static void print(Cluster cluster,int indentLen)
    {
     String clusterString = BasketTreeUtil.toString(cluster);
     indent(indentLen);
     System.out.println(clusterString);
     for (Cluster c: cluster.getChildren())
         print(c,indentLen+1);


    }

    public static String toString(Cluster c) {

        StringBuffer sb = new StringBuffer();
        Iterator<Node> scan = c.getNodes().iterator();
        while (scan.hasNext()) {
            sb.append(scan.next().getItemId());
            if (scan.hasNext())
                sb.append(DELIMITER);
        }
        return sb.toString();
    }

    public static void indent(int indentLen)
    {
     while (indentLen-- > 0)
         System.out.print(SPACE);
    }
}
