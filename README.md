# kluster

OVERVIEW

This clustering algorithm is a variant of the FP-Growth algorithm. One paper that describes that algorithm well
can be found at http://www.borgelt.net/papers/fpgrowth.pdf.

The clustering algorithm implemented strongly follows the first phase of the FP-Growth algorithm. 
There are, however, some differences.

The first phase of the FP-Growth algorithm does some very simple but brilliant things.

  - It takes some set of baskets with each basket is composed of items
  - It orders the items in the baskets by their frequency
  - It orders the baskets themselves by the frequency of the items in the basket.
    For example, a basket where the first item occurs 100 times will precede a basket
    whose first item occurs 81 times. And so on.
  - In order, the items in each basket are added to a trie-like structure
    thus forming a graph; however, each node in the graph maintains a count of the number of items for that node
    
This particular implementation gathers all clusters simply by returning a cluster from the root of the trie to
each and every salient node.  A node is salient if it is a leaf node, or if has more than two children, or if it
a node with a weight is greater than its parent and less than its child.

This very simple approach delivers quite good clustering and it is quite fast for what is. 
Of course, to scale to big data an algorithm such as thing would be need to re-implemented, say in map-reduce. 

One problem with FP-Growth is that FP-Growth can generate some very low-frequency clusters that I call
leftovers. A savvy reader might want to try finding those leftover clusters, and then feeding their data into 
an indepenent run of this algorithm. I have found that some pretty attractive clusters can be found in that
leftover data. It is possible to find 2X to 3X the number of clusters as the first phase of the
FP-Growth algorithm.  Please note, however, that this implementation does not do this. 

SETUP

   This project was built using Maven. So you will need maven to build this project and run its tests.
   Clone the repository
   Do 'mvn test' and that will build everything and run the tests
   
CLUSTER HIERARCHY

The savvy reader will realize, if they look at the augment trie-like structure I use here, that it
implicitly contains clusters that involve sub-clusters and super-clusters. That is, one cluster
may extend another another; conversely, one cluster may have extensions of it that form sub-clusters.

It is possible to harvest this hierarchy from this data structure, and some folks may find that quite useful.
But at present, especially since I coded all of this in about a day, I didn't do that. I opted for
a simpler approach, where I simply harvest all the clusters from the core data structure.

HOW TO USE THIS IMPLEMENTATION FOR A MUCH LARGER NUMBER OF ITEMS

It is also noteworthy that this algorithm treats every item as something with a unique numeric identifier.
What does this mean in respect to a practical usage of this code?

So if you have 10,000 or more items, where each item has a unique name, it is not so hard to associate a numeric
identifier with each such unique name; however, if you have say, 100 million such items, things are not so easy.
You will run out of memory.

Of course, one could use a database - SQL or NoSQL to address this problem.  But if you want a lighter weight, faster
solution things are trickier. Unfortunately, as far as I know, there are no good ways to assign 
unique long IDs to strings so that very little memory is used. 

Well... I do have code that does this, and may open source
that code at some point. If you have this problem, let me know and I will see what it can do. That code is far more
complex that this code, but using very minimal main-memory data, tt can assign (or retrieve) a long ID for a string very
quickly, with average access time involving 1.7 very fast operations for reads. With optimizations, that accss speed could
be reduced significantly. Paired with persistent data, such as Avro, this code could also, given any identifier for a string, 
return that string, and do this very quickly, simply by fetching the n-th item from the n-th Avro file, thus retrieving
the string associated with a long ID. Of course, that would be faster with a caching layer too....










    
    
