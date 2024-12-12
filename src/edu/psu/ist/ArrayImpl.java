package edu.psu.ist;

import java.util.ArrayList;

public class ArrayImpl<T extends Comparable<T>> implements Orderer<T> {
    private ArrayList<T> contents; // Holds the content/items
    private boolean accepting; // Tracks whether the machine is in "add" or "remove" state

    public ArrayImpl() {
        this.contents = new ArrayList<>();
        this.accepting = true; // Starting in accepting state
    }

    @Override
    public void add(T item) {
        if (!accepting) {
            throw new IllegalStateException("Machine is not in accepting state!");
        }
        contents.add(item); // Add item to the list
    }

    @Override
    public void crank() {
        accepting = !accepting; // Changing the machine mode
        if (!accepting) {
            // Sorting the list using the orignial ordering which is defined by Comparable
            contents.sort(null);
        }
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
        return contents.remove(0); // Removing the first item in the sorted list
    }

    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }


}


