package heaps;

import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap<T> {
    Node<T> nil = new Node(null, -1);
    Node<T> minNode;
    int numNodes;

    public FibonacciHeap() {
        this.minNode = nil;
        this.numNodes = 0;
    }

    class Node<T> {
        T data;
        int key;
        Node<T> child;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        boolean mark;
        int degree;

        public Node(T data, int key) {
            this.data = data;
            this.key = key;
            left = this;
            right = this;
        }
    }

    /**
     * insert a node with the given key into the heap
     * @param key key of the node to be inserted
     */
    private void insert(int key) {
        Node x = new Node(key, key);
        insertNode(x);
    }

    /**
     * inserts a node into the heap
     * @param x node to be inserted
     */
    private void insertNode(Node<T> x) {
        x.degree = 0;
        x.parent = nil;
        x.child = nil;
        x.mark = false;
        if (this.minNode == nil) {
            minNode = x;
        } else {
            x.left = minNode;
            x.right = minNode.right;
            x.right.left = x;
            minNode.right = x;
            if (x.key < minNode.key) {
                minNode = x;
            }
        }
        numNodes++;
    }

    /*
     * +----+     +----+     +----+
     * |    |--N->|one |--N->|    |
     * |    |<-P--|    |<-P--|    |
     * +----+     +----+     +----+
     *
     *
     * +----+     +----+     +----+
     * |    |--N->|two |--N->|    |
     * |    |<-P--|    |<-P--|    |
     * +----+     +----+     +----+
     *
     * And we want to relink everything to get
     *
     * +----+     +----+     +----+---+
     * |    |--N->|one |     |    |   |
     * |    |<-P--|    |     |    |<+ |
     * +----+     +----+<-\  +----+ | |
     *                  \  P        | |
     *                   N  \       N |
     * +----+     +----+  \->+----+ | |
     * |    |--N->|two |     |    | | |
     * |    |<-P--|    |     |    | | P
     * +----+     +----+     +----+ | |
     *              ^ |             | |
     *              | +-------------+ |
     *              +-----------------+
     * REMEMBER, lists are circular!
*/

    /**
     * merges two heaps into one
     * @param h1 first heap
     * @param h2 second heap
     * @return the union of h1 + h2
     */
    private FibonacciHeap<T> union(FibonacciHeap<T> h1, FibonacciHeap<T> h2) {
        FibonacciHeap<T> h = new FibonacciHeap<>();
        h.minNode = h1.minNode;
            if (h.minNode != nil) {
                if (h2.minNode != nil) {
                    Node temp = h.minNode.right;
                    h.minNode.right = h2.minNode.right;
                    h.minNode.right.left = h.minNode;
                    h2.minNode.right = temp;
                    h2.minNode.right.left = h2.minNode;

                    if (h2.minNode.key < h1.minNode.key) {
                        h.minNode = h2.minNode;
                    }
                }
            } else {
                h.minNode = h2.minNode;
            }

            h.numNodes = h1.numNodes + h2.numNodes;

        return h;
    }

    /**
     * removes the smallest element of the heap
     * @return the smallest element (before removing)
     */
    private Node<T> extractMin() {
        Node<T> z = minNode;
        if (z != nil) {
            int numChildren = z.degree;
            Node<T> x = z.child;
            Node<T> tempRight;
            while (numChildren > 0) {
                tempRight = x.right;

                //remove x from child list
                x.left.right = x.right;
                x.right.left = x.left;

                // add x to the root list
                x.left = minNode;
                x.right = minNode.right;
                minNode.right = x;
                x.right.left = x;

                // set parent
                x.parent = nil;
                x = tempRight;
                numChildren--;
            }

            // remove z from root list
            z.left.right = z.right;
            z.right.left = z.left;

            if (z == z.right) {
                minNode = nil;
            } else {
                minNode = z.right;
                consolidate();
            }
            numNodes--;
        }
        return z;
    }

    /**
     * modifies the heap so that each node in the root list has a unique degree
     */
    private void consolidate() {
        List<Node<T>> A = new ArrayList<>();
        for (int i=0; i<numNodes+1; i++) {
            A.add(nil);
        }

        int numRoots = 0;
        Node<T> x = minNode;
        if (x != nil) {
            numRoots = 1;
            x = x.right;
        }
        while (x != minNode) {
            numRoots++;
            x = x.right;
        }
        Node<T> tempRight;
        while (numRoots > 0) {
            tempRight = x.right;
            int d = x.degree;
            while (A.get(d) != nil) {
                Node<T> y = A.get(d); // another node with the same degree as x
                if (x.key > y.key) {
                    Node<T> temp = y;
                    y = x;
                    x = temp;
                }
                link(y, x);
                A.set(d, nil);
                d++;
            }
            // Save this node for later when we might encounter another of equal degree
            A.set(d, x);

            // move forward through the lists
            x = tempRight;
            numRoots--;
        }
        minNode = nil;
        for (int i=0; i<numNodes+1; i++) {
            Node<T> y = A.get(i);
            if (y != nil) {
                if (minNode == nil) {
                    minNode = y;
                } else {
                    y.left = minNode;
                    y.right = minNode.right;
                    minNode.right = y;
                    y.right.left = y;
                    if (y.key < minNode.key) {
                        minNode = y;
                    }
                }
            }
        }
    }

    /**
     * Make y a child of node x
     * @param y node to become child
     * @param x node to become parent
     */
    private void link(Node<T> y, Node<T> x) {
        // remove y from root list
        y.left.right = y.right;
        y.right.left = y.left;

        // make y a child of x
        y.parent = x;
        if (x.child == nil) {
            x.child = y;
            y.right = y;
            y.left = y;
        } else {
            y.left = x.child;
            y.right = x.child.right;
            x.child.right = y;
            y.right.left = y;
        }

        x.degree++;
        y.mark = false;
    }

    /**
     * Decrease the key of a node in the heap
     * @param x node whose key will decreases
     * @param key new (decreased) value of x.key
     */
    private void decreaseKey(Node<T> x, int key) {
        if (key > x.key) {
            System.out.println("key should be no greater than current key");
        }
        x.key = key;
        Node<T> y = x.parent;
        if (y != nil && x.key < y.key) {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.key < minNode.key) {
            minNode = x;
        }
    }

    /**
     * reverse of the link operation: removes x from the child list of y and adds x to the root list
     * @param x child of y to be removed from y's child list
     * @param y parent of x about to lose a child
     */
    private void cut(Node<T> x, Node<T> y) {
        // remove x from the child list of y and decrement y.degree
        x.left.right = x.right;
        x.right.left = x.left;
        y.degree--;

        if (y.child == x) {
            y.child = x.right;
        }

        if (y.degree == 0) {
            y.child = nil;
        }

        // add x to the root list
        x.left = minNode;
        x.right = minNode.right;
        minNode.right = x;
        x.right.left = x;

        x.parent = nil;
        x.mark = false;
    }

    /**
     * This cuts y from its parent and then does the same for y's parent and so on up the tree
     * @param y node to perform the cascading cut on
     */
    private void cascadingCut(Node<T> y) {
        Node<T> z = y.parent;
        if (z != nil) {
            if (!y.mark) {
                y.mark = true;
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    /**
     * removes a node from the heap
     * @param x node to be removed
     */
    private void deleteNode(Node<T> x) {
        decreaseKey(x, Integer.MIN_VALUE);
        extractMin();
    }
}
