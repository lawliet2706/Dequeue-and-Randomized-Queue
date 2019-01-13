import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
        assert check();
    }                           // construct an empty deque

    public boolean isEmpty() {
        return first == null;
    }                 // is the deque empty?

    public int size() {
        return size;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("addfirst");
        if (!isEmpty()) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            size++;
        }
        else {
            first = new Node();
            first.item = item;
            first.next = null;
            last = first;
            size++;
        }

        assert check();
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("addlast");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
        assert check();
    }          // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        first = first.next;
        size--;
        if (size == 0) first = null;
        if (isEmpty()) last = null;
        assert check();
        return item;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Node newlast = new Node();
        Item item = last.item;
        for (Node x = first; x.next != null; x = x.next) {
            newlast = x;
        }
        last = newlast;
        size--;
        if (size == 0) first = null;
        if (isEmpty()) last = null;
        assert check();
        return item;

    }                // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item item = current.item;
            if (item == null) throw new NoSuchElementException("No next");
            current = current.next;
            return item;
        }
    }

    private boolean check() {
        if (size < 0) {
            return false;
        }
        if (size == 0) {
            if (first != null) return false;
        }
        else if (size == 1) {
            if (first == null) return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null) return false;
            if (first.next == null) return false;
        }
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= size; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != size) return false;

        return true;
    }
    // return an iterator over items in order from front to end
}
