package linkedlist;

public class LinkedList {
    ListNode head;

    class ListNode {
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
            prev = null;
            next = null;
        }
    }

    private void insert(int x) {
        ListNode elem = new ListNode(x);
        elem.next = head;
        if (head != null) {
            head.prev = elem;
        }
        head = elem;
        elem.prev = null;
    }

    private void delete(int x) {
        ListNode elem = search(x);
        if (elem == null) {
            return;
        }
        if (elem.prev != null) {
            elem.prev.next = elem.next;
        }
        else {
            head = elem.next;
        }
        if (elem.next != null) {
            elem.next.prev = elem.prev;
        }
    }

    private ListNode search(int x) {
        ListNode current = head;
        while (current != null && current.val != x) {
            current = current.next;
        }
        return current;
    }
}
