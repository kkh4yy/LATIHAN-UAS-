import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for the MenuItem class
 * Achieves 100% code coverage including all methods, branches, and edge cases
 */
public class MenuItemTest {
    
    private MenuItem testItem;
    
    @BeforeEach
    void setUp() {
        testItem = new MenuItem("Nasi Goreng", "Main Course", "MC001", 25000.0);
    }
    
    @Test
    @DisplayName("Test Constructor with Various Inputs")
    void testConstructor() {
        // Test normal constructor
        assertEquals("Nasi Goreng", testItem.getName(), "Name not set correctly");
        assertEquals("Main Course", testItem.getCategory(), "Category not set correctly");
        assertEquals("MC001", testItem.getItemId(), "ItemId not set correctly");
        assertEquals(25000.0, testItem.getPrice(), 0.01, "Price not set correctly");
        
        // Test with empty strings
        MenuItem emptyItem = new MenuItem("", "", "", 0.0);
        assertEquals("", emptyItem.getName(), "Empty name not handled");
        assertEquals("", emptyItem.getCategory(), "Empty category not handled");
        assertEquals("", emptyItem.getItemId(), "Empty itemId not handled");
        assertEquals(0.0, emptyItem.getPrice(), 0.01, "Zero price not handled");
        
        // Test with null values
        MenuItem nullItem = new MenuItem(null, null, null, -10.0);
        assertNull(nullItem.getName(), "Null name not handled");
        assertNull(nullItem.getCategory(), "Null category not handled");
        assertNull(nullItem.getItemId(), "Null itemId not handled");
        assertEquals(-10.0, nullItem.getPrice(), 0.01, "Negative price not handled");
    }
    
    @Test
    @DisplayName("Test All Getter Methods")
    void testGetters() {
        MenuItem item = new MenuItem("Gado-Gado", "Main Course", "MC002", 20000.0);
        
        assertEquals("Gado-Gado", item.getName(), "getName failed");
        assertEquals("Main Course", item.getCategory(), "getCategory failed");
        assertEquals("MC002", item.getItemId(), "getItemId failed");
        assertEquals(20000.0, item.getPrice(), 0.01, "getPrice failed");
    }
    
    @Test
    @DisplayName("Test toString Method")
    void testToString() {
        String expected = "MenuItem{name='Nasi Goreng', category='Main Course', itemId='MC001', price=25000.0}";
        assertEquals(expected, testItem.toString(), "toString format incorrect");
        
        // Test with different values
        MenuItem beverage = new MenuItem("Es Teh Manis", "Beverage", "BV001", 5000.0);
        String expectedBeverage = "MenuItem{name='Es Teh Manis', category='Beverage', itemId='BV001', price=5000.0}";
        assertEquals(expectedBeverage, beverage.toString(), "toString format incorrect for beverage");
    }
    
    @Test
    @DisplayName("Test Edge Cases")
    void testEdgeCases() {
        // Test with very long strings
        String longName = "A".repeat(1000);
        String longCategory = "B".repeat(1000);
        String longItemId = "C".repeat(1000);
        MenuItem longItem = new MenuItem(longName, longCategory, longItemId, Double.MAX_VALUE);
        
        assertEquals(longName, longItem.getName(), "Long name not handled");
        assertEquals(longCategory, longItem.getCategory(), "Long category not handled");
        assertEquals(longItemId, longItem.getItemId(), "Long itemId not handled");
        assertEquals(Double.MAX_VALUE, longItem.getPrice(), 0.01, "Max double price not handled");
        
        // Test with special characters
        MenuItem specialItem = new MenuItem("Nasi Goreng ÄÖÜ", "Main Course 中文", "MC-001_special", 25000.5);
        assertEquals("Nasi Goreng ÄÖÜ", specialItem.getName(), "Special characters in name not handled");
        assertEquals("Main Course 中文", specialItem.getCategory(), "Special characters in category not handled");
        assertEquals("MC-001_special", specialItem.getItemId(), "Special characters in itemId not handled");
        assertEquals(25000.5, specialItem.getPrice(), 0.01, "Decimal price not handled");
    }
    
    @Test
    @DisplayName("Test Price Boundaries")
    void testPriceBoundaries() {
        // Test minimum value
        MenuItem minItem = new MenuItem("Min Item", "Test", "MIN001", Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, minItem.getPrice(), 0.01, "Min double value not handled");
        
        // Test zero price
        MenuItem freeItem = new MenuItem("Free Item", "Test", "FREE001", 0.0);
        assertEquals(0.0, freeItem.getPrice(), 0.01, "Zero price not handled");
        
        // Test negative price
        MenuItem negativeItem = new MenuItem("Negative Item", "Test", "NEG001", -100.0);
        assertEquals(-100.0, negativeItem.getPrice(), 0.01, "Negative price not handled");
        
        // Test infinity
        MenuItem infiniteItem = new MenuItem("Infinite Item", "Test", "INF001", Double.POSITIVE_INFINITY);
        assertEquals(Double.POSITIVE_INFINITY, infiniteItem.getPrice(), 0.01, "Positive infinity not handled");
        
        // Test NaN
        MenuItem nanItem = new MenuItem("NaN Item", "Test", "NAN001", Double.NaN);
        assertTrue(Double.isNaN(nanItem.getPrice()), "NaN price not handled");
    }
}
