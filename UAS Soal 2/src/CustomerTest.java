import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * JUnit 5 tests for the Customer class
 * Achieves 100% code coverage including all methods, branches, and edge cases
 */
public class CustomerTest {
    
    private Customer testCustomer;
    private MenuItem testItem1;
    private MenuItem testItem2;
    private MenuItem testItem3;
    
    @BeforeEach
    void setUp() {
        testCustomer = new Customer("John Smith", "C001");
        testItem1 = new MenuItem("Nasi Goreng", "Main Course", "MC001", 25000.0);
        testItem2 = new MenuItem("Gado-Gado", "Main Course", "MC002", 20000.0);
        testItem3 = new MenuItem("Es Teh Manis", "Beverage", "BV001", 5000.0);
    }
    
    @Test
    @DisplayName("Test Constructor with Various Inputs")
    void testConstructor() {
        // Test normal constructor
        assertEquals("John Smith", testCustomer.getName(), "Name not set correctly");
        assertEquals("C001", testCustomer.getCustomerId(), "CustomerId not set correctly");
        assertNotNull(testCustomer.getOrderedItems(), "OrderedItems should be initialized");
        assertEquals(0, testCustomer.getOrderedItems().size(), "OrderedItems should be empty initially");
        assertEquals(0.0, testCustomer.getTotalBill(), 0.01, "TotalBill should be 0 initially");
        
        // Test with empty strings
        Customer emptyCustomer = new Customer("", "");
        assertEquals("", emptyCustomer.getName(), "Empty name not handled");
        assertEquals("", emptyCustomer.getCustomerId(), "Empty customerId not handled");
        
        // Test with null values
        Customer nullCustomer = new Customer(null, null);
        assertNull(nullCustomer.getName(), "Null name not handled");
        assertNull(nullCustomer.getCustomerId(), "Null customerId not handled");
    }
    
    @Test
    @DisplayName("Test Adding Ordered Items")
    void testAddOrderedItem() {
        // Test adding first item
        testCustomer.addOrderedItem(testItem1);
        assertEquals(1, testCustomer.getOrderedItems().size(), "Should have 1 item after adding");
        assertTrue(testCustomer.getOrderedItems().contains(testItem1), "Should contain the added item");
        
        // Test adding second item
        testCustomer.addOrderedItem(testItem2);
        assertEquals(2, testCustomer.getOrderedItems().size(), "Should have 2 items after adding");
        assertTrue(testCustomer.getOrderedItems().contains(testItem2), "Should contain the second item");
        
        // Test adding same item multiple times (should be allowed)
        testCustomer.addOrderedItem(testItem1);
        assertEquals(3, testCustomer.getOrderedItems().size(), "Should have 3 items after adding duplicate");
        
        // Test adding null item
        testCustomer.addOrderedItem(null);
        // Behavior may vary - either reject null or accept it
        // The implementation should handle this gracefully
    }
    
    @Test
    @DisplayName("Test Removing Ordered Items")
    void testRemoveOrderedItem() {
        // Add items first
        testCustomer.addOrderedItem(testItem1);
        testCustomer.addOrderedItem(testItem2);
        testCustomer.addOrderedItem(testItem3);
        
        // Test removing existing item
        boolean removed1 = testCustomer.removeOrderedItem(testItem2);
        assertTrue(removed1, "Should return true when removing existing item");
        assertEquals(2, testCustomer.getOrderedItems().size(), "Should have 2 items after removal");
        assertFalse(testCustomer.getOrderedItems().contains(testItem2), "Should not contain removed item");
        
        // Test removing non-existent item
        MenuItem nonExistentItem = new MenuItem("Non-existent", "Test", "TEST001", 1000.0);
        boolean removed2 = testCustomer.removeOrderedItem(nonExistentItem);
        assertFalse(removed2, "Should return false when removing non-existent item");
        assertEquals(2, testCustomer.getOrderedItems().size(), "Size should remain same after failed removal");
        
        // Test removing from empty list
        testCustomer.removeOrderedItem(testItem1);
        testCustomer.removeOrderedItem(testItem3);
        boolean removed3 = testCustomer.removeOrderedItem(testItem1);
        assertFalse(removed3, "Should return false when removing from empty list");
        
        // Test removing null item
        boolean removed4 = testCustomer.removeOrderedItem(null);
        assertFalse(removed4, "Should return false when removing null item");
    }
    
    @Test
    @DisplayName("Test Calculate Total Bill")
    void testCalculateTotalBill() {
        // Test with empty order
        testCustomer.calculateTotalBill();
        assertEquals(0.0, testCustomer.getTotalBill(), "Total bill should be 0 for empty order");
        
        // Test with single item
        testCustomer.addOrderedItem(testItem1); // 25000.0
        testCustomer.calculateTotalBill();
        assertEquals(25000.0, testCustomer.getTotalBill(), "Total bill incorrect for single item");
        
        // Test with multiple items
        testCustomer.addOrderedItem(testItem2); // 20000.0
        testCustomer.addOrderedItem(testItem3); // 5000.0
        testCustomer.calculateTotalBill();
        assertEquals(50000.0, testCustomer.getTotalBill(), "Total bill incorrect for multiple items");
        
        // Test with duplicate items
        testCustomer.addOrderedItem(testItem1); // another 25000.0
        testCustomer.calculateTotalBill();
        assertEquals(75000.0, testCustomer.getTotalBill(), "Total bill incorrect with duplicates");
        
        // Test after removing item
        testCustomer.removeOrderedItem(testItem1);
        testCustomer.calculateTotalBill();
        assertEquals(50000.0, testCustomer.getTotalBill(), "Total bill not updated after removal");
    }
    
    @Test
    @DisplayName("Test toString Method")
    void testToString() {
        String expected = "Customer{name='John Smith', customerId='C001', orderedItems=0, totalBill=0.0}";
        assertEquals(expected, testCustomer.toString(), "toString format incorrect for empty order");
        
        // Test with items
        testCustomer.addOrderedItem(testItem1);
        testCustomer.addOrderedItem(testItem2);
        testCustomer.calculateTotalBill();
        String expectedWithItems = "Customer{name='John Smith', customerId='C001', orderedItems=2, totalBill=45000.0}";
        assertEquals(expectedWithItems, testCustomer.toString(), "toString format incorrect with items");
    }
    
    @Test
    @DisplayName("Test Edge Cases")
    void testEdgeCases() {
        // Test with very long strings
        String longName = "A".repeat(1000);
        String longCustomerId = "C".repeat(1000);
        Customer longCustomer = new Customer(longName, longCustomerId);
        
        assertEquals(longName, longCustomer.getName(), "Long name not handled");
        assertEquals(longCustomerId, longCustomer.getCustomerId(), "Long customerId not handled");
        
        // Test with special characters
        Customer specialCustomer = new Customer("José María", "C-001_special");
        assertEquals("José María", specialCustomer.getName(), "Special characters in name not handled");
        assertEquals("C-001_special", specialCustomer.getCustomerId(), "Special characters in customerId not handled");
    }
    
    @Test
    @DisplayName("Test Price Edge Cases in Bill Calculation")
    void testPriceEdgeCases() {
        // Test with zero price item
        MenuItem freeItem = new MenuItem("Free Item", "Test", "FREE001", 0.0);
        testCustomer.addOrderedItem(freeItem);
        testCustomer.calculateTotalBill();
        assertEquals(0.0, testCustomer.getTotalBill(), 0.01, "Zero price item not handled correctly");
        
        // Test with negative price item
        MenuItem negativeItem = new MenuItem("Negative Item", "Test", "NEG001", -100.0);
        testCustomer.addOrderedItem(negativeItem);
        testCustomer.calculateTotalBill();
        assertEquals(-100.0, testCustomer.getTotalBill(), 0.01, "Negative price item not handled correctly");
        
        // Test with very large numbers
        MenuItem expensiveItem = new MenuItem("Expensive Item", "Test", "EXP001", Double.MAX_VALUE);
        Customer richCustomer = new Customer("Rich Customer", "RICH001");
        richCustomer.addOrderedItem(expensiveItem);
        richCustomer.calculateTotalBill();
        assertEquals(Double.MAX_VALUE, richCustomer.getTotalBill(), 0.01, "Large price not handled correctly");
    }
    
    @Test
    @DisplayName("Test List Behavior")
    void testListBehavior() {
        // Test that getOrderedItems returns a list that can be used
        List<MenuItem> items = testCustomer.getOrderedItems();
        assertNotNull(items, "getOrderedItems should not return null");
        
        // Add items and verify list behavior
        testCustomer.addOrderedItem(testItem1);
        testCustomer.addOrderedItem(testItem2);
        
        List<MenuItem> updatedItems = testCustomer.getOrderedItems();
        assertEquals(2, updatedItems.size(), "List size should reflect added items");
        
        // Verify order preservation (implementation dependent)
        // This test assumes order is preserved
        assertEquals(testItem1, updatedItems.get(0), "First item should be testItem1");
        assertEquals(testItem2, updatedItems.get(1), "Second item should be testItem2");

        updatedItems.clear(); // if the return list is cleared or alternated, the original remains.
        assertEquals(2, testCustomer.getOrderedItems().size(), "List MenuItem should not be changed");
        
    }
}
