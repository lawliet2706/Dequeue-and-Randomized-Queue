import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int size;

    public RandomizedQueue() {
        size = 0;
        a = (Item[]) new Object[2];
    }               // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return size;
    }                       // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item is null");
        if (size == a.length) resize(2 * a.length);    // double size of a if necessary
        a[size++] = item;                            // add item
        StdRandom.shuffle(a, 0, size);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = a[size - 1];
        a[size - 1] = null;
        size--;
        if (size > 0 && size == a.length / 4) resize(a.length / 2);
        return item;
    }                    // remove and return a random item

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException(" ");
        int rindex = StdRandom.uniform(0, a.length);
        Item item = a[rindex];
        return item;
    }                     // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {

            i = size - 1;
            StdRandom.shuffle(a, 0, size);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = a[i];
        }
        a = temp;
    } // return an independent iterator over items in random order

}
