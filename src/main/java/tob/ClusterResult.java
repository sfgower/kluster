package tob;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stefan on 2/8/2015.
 */
public class ClusterResult {

    private List<Cluster> clusterList = new LinkedList<Cluster>();
    private Cluster root = null;

    public ClusterResult(Cluster root, List<Cluster> clusterList) {
        this.root = root;
        this.clusterList = clusterList;
    }

    public List<Cluster> getClusterList() {
        return clusterList;
    }

    public Cluster getRoot() {
        return root;
    }

    public boolean contains(long... args) {
        long path[] = new long[args.length];
        int j = 0;
        for (long x : args)
            path[j++] = x;
        for (Cluster cluster : clusterList) {
            if (cluster.match(path))
                return true;
        }
        return false;
    }

    public int size() {
        return clusterList.size();
    }

    public Cluster find(long... args) {

        long path[] = new long[args.length];
        int j = 0;
        for (long x : args)
            path[j++] = x;
        for (Cluster cluster : clusterList) {
            if (cluster.match(path))
                return cluster;
        }
        return null;
    }
}
