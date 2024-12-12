package edu.psu.ist;

import java.util.LinkedList;

public class LinkedImpl<T extends Comparable<T>> implements Orderer<T> {
    private LinkedList<T> contents; // Hold the contents/items
    private boolean accepting; // If accepting then in the add state

    // Constructor
    public LinkedImpl() {
        this.contents = new LinkedList<>();
        this.accepting = true; // Starting in accepting state
    }

    @Override
    public void add(T item) {
        if (!accepting) {
            throw new IllegalStateException("Machine is not in the accepting mode");
        }
        // Find the correct position to insert the item to keep the list sorted
        int index = 0;
        while (index < contents.size() && contents.get(index).compareTo(item) < 0) {
            index++;
        }
        contents.add(index, item); // Insert the item in the correct position
    }

    @Override
    public void crank() {
        accepting = !accepting; // Changing the machine mode
    }

    @Override
    public boolean isAccepting() {
        return accepting;
    }

    @Override
    public T remove() {
        if (accepting) {
            throw new IllegalStateException("Machine is in accepting mode and therefore cannot remove.");
        }
        if (contents.isEmpty()) {
            throw new IllegalStateException("No items to remove. It's empty.");
        }
        return contents.removeFirst(); // Removing the first item, which is the smallest
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }
}


