package disjointsets;

public class SetList<E> {
    ListNode<E> head;
    ListNode<E> tail;

    static class ListNode<E> {
        E val;
        SetList<E> prev;
        ListNode<E> next;
        public ListNode(E val) {
            this.val = val;
        }
    }

    public SetList<E> makeSet(E x) {
        ListNode<E> temp = new ListNode<>(x);
        return makeSet(temp);
    }

    public SetList<E> makeSet(ListNode<E> x) {
        SetList<E> set = new SetList<>();
        x.prev = set;
        set.head = x;
        set.tail = x;
        return set;
    }

    public ListNode<E> findSet(ListNode<E> x) {
        if (x.prev != null) {
            return x.prev.head;
        }
        return null;
    }

    // assumes x and y are in different sets
    public  SetList<E> union(ListNode<E> x, ListNode<E> y) {
        if (x == null || x.prev == null) {
            return y.prev;
        } else if (y == null || y.prev == null) {
            return x.prev;
        }

        x.prev.tail.next = y.prev.head;
        x.prev.tail = y.prev.tail;
        ListNode<E> curr = y.prev.head;
        while (curr != null) {
            curr.prev = x.prev;
            curr = curr.next;
        }
        y.prev = null;
        return x.prev;
    }
}
