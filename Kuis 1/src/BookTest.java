import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for the Book class
 * Achieves 100% code coverage including all methods, branches, and edge cases
 */
public class BookTest {
    
    private Book testBook;
    
    @BeforeEach
    void setUp() {
        testBook = new Book("Java Programming", "John Doe", "978-1234567890");
    }
    
    
    @Test
    @DisplayName("Test Constructor with Various Inputs")
    void testConstructor() {
        // Test normal constructor
        assertEquals("Java Programming", testBook.getTitle(), "Title not set correctly");
        assertEquals("John Doe", testBook.getAuthor(), "Author not set correctly");
        assertEquals("978-1234567890", testBook.getIsbn(), "ISBN not set correctly");
        assertTrue(testBook.isAvailable(), "Book should be available by default");
        
        // Test with empty strings
        Book emptyBook = new Book("", "", "");
        assertEquals("", emptyBook.getTitle(), "Empty title not handled");
        assertEquals("", emptyBook.getAuthor(), "Empty author not handled");
        assertEquals("", emptyBook.getIsbn(), "Empty ISBN not handled");
        
        // Test with null values
        Book nullBook = new Book(null, null, null);
        assertNull(nullBook.getTitle(), "Null title not handled");
        assertNull(nullBook.getAuthor(), "Null author not handled");
        assertNull(nullBook.getIsbn(), "Null ISBN not handled");
    }
    
    
    @Test
    @DisplayName("Test All Getter Methods")
    void testGetters() {
        Book book = new Book("Data Structures", "Jane Smith", "978-0987654321");
        
        assertEquals("Data Structures", book.getTitle(), "getTitle failed");
        assertEquals("Jane Smith", book.getAuthor(), "getAuthor failed");
        assertEquals("978-0987654321", book.getIsbn(), "getIsbn failed");
        assertTrue(book.isAvailable(), "isAvailable should be true initially");
    }
    
    @Test
    @DisplayName("Test setAvailable Method")
    void testSetAvailable() {
        // Test setting to false
        testBook.setAvailable(false);
        assertFalse(testBook.isAvailable(), "setAvailable(false) failed");
        
        // Test setting back to true
        testBook.setAvailable(true);
        assertTrue(testBook.isAvailable(), "setAvailable(true) failed");
        
        // Test multiple toggles
        testBook.setAvailable(false);
        assertFalse(testBook.isAvailable(), "Second setAvailable(false) failed");
        testBook.setAvailable(true);
        assertTrue(testBook.isAvailable(), "Second setAvailable(true) failed");
    }
    
    @Test
    @DisplayName("Test toString Method")
    void testToString() {
        // Test with available book
        Book availableBook = new Book("Java Guide", "Author Name", "111-222-333");
        String expectedAvailable = "Book{title='Java Guide', author='Author Name', isbn='111-222-333', available=true}";
        assertEquals(expectedAvailable, availableBook.toString(), "toString for available book failed");
        
        // Test with unavailable book
        Book unavailableBook = new Book("Python Guide", "Another Author", "444-555-666");
        unavailableBook.setAvailable(false);
        String expectedUnavailable = "Book{title='Python Guide', author='Another Author', isbn='444-555-666', available=false}";
        assertEquals(expectedUnavailable, unavailableBook.toString(), "toString for unavailable book failed");
        
        // Test with empty strings
        Book emptyBook = new Book("", "", "");
        String expectedEmpty = "Book{title='', author='', isbn='', available=true}";
        assertEquals(expectedEmpty, emptyBook.toString(), "toString for empty strings failed");
    }
    
    @Test
    @DisplayName("Test equals Method with All Cases")
    void testEquals() {
        Book book1 = new Book("Title1", "Author1", "ISBN1");
        Book book2 = new Book("Title1", "Author1", "ISBN1"); // Same ISBN
        Book book3 = new Book("Title2", "Author2", "ISBN2"); // Different ISBN
        Book book4 = new Book("Title1", "Author1", "ISBN1"); // Identical
        
        // Test reflexivity (book equals itself)
        assertTrue(book1.equals(book1), "Book should equal itself");
        
        // Test books with same ISBN (should be equal)
        assertTrue(book1.equals(book2), "Books with same ISBN should be equal");
        assertTrue(book2.equals(book1), "Equals should be symmetric");
        
        // Test books with different ISBN (should not be equal)
        assertFalse(book1.equals(book3), "Books with different ISBN should not be equal");
        assertFalse(book3.equals(book1), "Equals should be symmetric for different books");
        
        // Test identical books
        assertTrue(book1.equals(book4), "Identical books should be equal");
        
        // Test null comparison
        assertFalse(book1.equals(null), "Book should not equal null");
        
        // Test different class comparison
        String notABook = "Not a book";
        assertFalse(book1.equals(notABook), "Book should not equal different class");
        
        // Test with null ISBN
        Book nullIsbnBook1 = new Book("Title", "Author", null);
        Book nullIsbnBook2 = new Book("Title", "Author", null);
        Book nonNullIsbnBook = new Book("Title", "Author", "ISBN");
        
        assertTrue(nullIsbnBook1.equals(nullIsbnBook2), "Books with null ISBN should be equal");
        assertFalse(nullIsbnBook1.equals(nonNullIsbnBook), "Null ISBN book should not equal non-null ISBN book");
        assertFalse(nonNullIsbnBook.equals(nullIsbnBook1), "Non-null ISBN book should not equal null ISBN book");
    }
    
}
