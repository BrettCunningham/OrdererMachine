package edu.psu.ist;

public interface Orderer<T> {
    void add(T item); // Adds an item to the orderer machine
    void crank(); // Switches between accepting and removal modes
    boolean isAccepting(); // Returns true if in adding mode, if not then false
    T remove(); // Removes and returns the next item
    boolean isEmpty(); // Checks if the machine is empty
}

