package edu.psu.ist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrdererTests {

    // Tests for ArrayImpl
    @Test
    void testAddAndRemove_ArrayImpl() {
        Orderer<Integer> orderer = new ArrayImpl<>();
        orderer.add(3); //Adding items that are not in order
        orderer.add(2);
        orderer.add(1);
        orderer.crank();
        assertEquals(1, orderer.remove()); // Items should come out in sorted order (1,2,3)
        assertEquals(2, orderer.remove());
        assertEquals(3, orderer.remove());
        assertTrue(orderer.isEmpty());
    }

    @Test
    void testAddInRemovalStateThrowsException_ArrayImpl() {
        Orderer<Integer> orderer = new ArrayImpl<>();
        orderer.crank();
        assertThrows(IllegalStateException.class, () -> orderer.add(10));
    }

    @Test
    void testRemoveInAcceptingStateThrowsException_ArrayImpl() {
        Orderer<Integer> orderer = new ArrayImpl<>();
        assertThrows(IllegalStateException.class, () -> orderer.remove());
    }

    @Test
    void testCrankAndIsAccepting_ArrayImpl() {
        Orderer<Integer> orderer = new ArrayImpl<>();
        assertTrue(orderer.isAccepting());
        orderer.crank(); // Switch to removal mode
        assertFalse(orderer.isAccepting());
        orderer.crank(); // Switch to accepting mode
        assertTrue(orderer.isAccepting());
    }

    // Tests for LinkedImpl
    @Test
    void testAddAndRemove_LinkedImpl() {
        Orderer<Integer> orderer = new LinkedImpl<>();
        orderer.add(3); //Adding items that are not in order
        orderer.add(1);
        orderer.add(2);
        orderer.crank(); //Switch to removal mode
        assertEquals(1, orderer.remove()); // Items should come out in sorted order
        assertEquals(2, orderer.remove());
        assertEquals(3, orderer.remove());
        assertTrue(orderer.isEmpty());
    }

    @Test
    void testAddInRemovalStateThrowsException_LinkedImpl() {
        Orderer<Integer> orderer = new LinkedImpl<>();
        orderer.crank(); // Switch to removal state
        assertThrows(IllegalStateException.class, () -> orderer.add(10));
    }

    // Tests for removal from LinkedImpl
    @Test
    void testRemoveInAcceptingStateThrowsException_LinkedImpl() {
        Orderer<Integer> orderer = new LinkedImpl<>();
        assertThrows(IllegalStateException.class, () -> orderer.remove());
    }

    @Test
    void testCrankAndIsAccepting_LinkedImpl() {
        Orderer<Integer> orderer = new LinkedImpl<>();
        assertTrue(orderer.isAccepting()); // Testing if it is accepting which should be default
        orderer.crank(); // Switch to removal mode
        assertFalse(orderer.isAccepting()); // Checking when switched if accepting is false
        orderer.crank(); // Switch back to accepting mode
        assertTrue(orderer.isAccepting()); // Switching back and checking if accepting is true again
    }
}
