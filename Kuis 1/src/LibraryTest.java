import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for the Library class
 * Achieves 100% code coverage including all methods, branches, and edge cases
 */
@DisplayName("Library Class Tests")
public class LibraryTest {
    
    private Library library;
    
    @BeforeEach
    void setUp() {
        library = new Library();
    }
    
    @Test
    @DisplayName("Test Adding Books to Library")
    void testAddBook() {
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Python Guide", "Jane Smith", "978-0987654321");
        
        // Test adding valid books
        library.addBook(book1);
        assertEquals(1, library.getBookCount(), "Book count should be 1 after adding one book");
        assertTrue(library.getBooks().contains(book1), "Library should contain the added book");
        
        library.addBook(book2);
        assertEquals(2, library.getBookCount(), "Book count should be 2 after adding two books");
        assertTrue(library.getBooks().contains(book2), "Library should contain the second book");
        
        // Test adding null book
        library.addBook(null);
        assertEquals(2, library.getBookCount(), "Book count should remain 2 after adding null");
    }
    
    @Test
    @DisplayName("Test Adding Members to Library")
    void testAddMember() {
        Member member1 = new Member("Alice Johnson", "M001");
        Member member2 = new Member("Bob Wilson", "M002");
        
        // Test adding valid members
        library.addMember(member1);
        assertEquals(1, library.getMemberCount(), "Member count should be 1 after adding one member");
        assertTrue(library.getMembers().contains(member1), "Library should contain the added member");
        
        library.addMember(member2);
        assertEquals(2, library.getMemberCount(), "Member count should be 2 after adding two members");
        assertTrue(library.getMembers().contains(member2), "Library should contain the second member");
        
        // Test adding null member
        library.addMember(null);
        assertEquals(2, library.getMemberCount(), "Member count should remain 2 after adding null");
    }
    
    @Test
    @DisplayName("Test Finding Books by ISBN")
    void testFindBookByIsbn() {
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Python Guide", "Jane Smith", "978-0987654321");
        
        library.addBook(book1);
        library.addBook(book2);
        
        // Test finding existing book
        Book foundBook = library.findBookByIsbn("978-1234567890");
        assertEquals(book1, foundBook, "Should find the correct book by ISBN");
        
        // Test finding second book
        Book foundBook2 = library.findBookByIsbn("978-0987654321");
        assertEquals(book2, foundBook2, "Should find the second book by ISBN");
        
        // Test finding non-existing book
        Book notFound = library.findBookByIsbn("999-9999999999");
        assertNull(notFound, "Should return null for non-existing ISBN");
        
        // Test with null ISBN
        Book nullResult = library.findBookByIsbn(null);
        assertNull(nullResult, "Should return null for null ISBN");
        
        // Test with empty ISBN
        Book emptyResult = library.findBookByIsbn("");
        assertNull(emptyResult, "Should return null for empty ISBN");
    }
    
    @Test
    @DisplayName("Test Finding Members by ID")
    void testFindMemberById() {
        Member member1 = new Member("Alice Johnson", "M001");
        Member member2 = new Member("Bob Wilson", "M002");
        
        library.addMember(member1);
        library.addMember(member2);
        
        // Test finding existing member
        Member foundMember = library.findMemberById("M001");
        assertEquals(member1, foundMember, "Should find the correct member by ID");
        
        // Test finding second member
        Member foundMember2 = library.findMemberById("M002");
        assertEquals(member2, foundMember2, "Should find the second member by ID");
        
        // Test finding non-existing member
        Member notFound = library.findMemberById("M999");
        assertNull(notFound, "Should return null for non-existing member ID");
        
        // Test with null member ID
        Member nullResult = library.findMemberById(null);
        assertNull(nullResult, "Should return null for null member ID");
        
        // Test with empty member ID
        Member emptyResult = library.findMemberById("");
        assertNull(emptyResult, "Should return null for empty member ID");
    }
    
    @Test
    @DisplayName("Test Borrowing Books")
    void testBorrowBook() {
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Python Guide", "Jane Smith", "978-0987654321");
        Member member1 = new Member("Alice Johnson", "M001");
        Member member2 = new Member("Bob Wilson", "M002");
        
        library.addBook(book1);
        library.addBook(book2);
        library.addMember(member1);
        library.addMember(member2);
        
        // Test successful borrowing
        boolean success1 = library.borrowBook("M001", "978-1234567890");
        assertTrue(success1, "Should successfully borrow available book");
        assertFalse(book1.isAvailable(), "Book should be unavailable after borrowing");
        assertTrue(member1.getBorrowedBooks().contains(book1), "Member should have the borrowed book");
        
        // Test borrowing unavailable book
        boolean success2 = library.borrowBook("M002", "978-1234567890");
        assertFalse(success2, "Should fail to borrow unavailable book");
        
        // Test borrowing with non-existing member
        boolean success3 = library.borrowBook("M999", "978-0987654321");
        assertFalse(success3, "Should fail with non-existing member");
        
        // Test borrowing with non-existing book
        boolean success4 = library.borrowBook("M002", "999-9999999999");
        assertFalse(success4, "Should fail with non-existing book");
        
        // Test borrowing with null member ID
        boolean success5 = library.borrowBook(null, "978-0987654321");
        assertFalse(success5, "Should fail with null member ID");
        
        // Test borrowing with null ISBN
        boolean success6 = library.borrowBook("M002", null);
        assertFalse(success6, "Should fail with null ISBN");
    }
    
    @Test
    @DisplayName("Test Returning Books")
    void testReturnBook() {
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Python Guide", "Jane Smith", "978-0987654321");
        Member member1 = new Member("Alice Johnson", "M001");
        Member member2 = new Member("Bob Wilson", "M002");
        
        library.addBook(book1);
        library.addBook(book2);
        library.addMember(member1);
        library.addMember(member2);
        
        // First borrow a book
        library.borrowBook("M001", "978-1234567890");
        
        // Test successful return
        boolean return1 = library.returnBook("M001", "978-1234567890");
        assertTrue(return1, "Should successfully return borrowed book");
        assertTrue(book1.isAvailable(), "Book should be available after returning");
        assertFalse(member1.getBorrowedBooks().contains(book1), "Member should not have the returned book");
        
        // Test returning book not borrowed by member
        boolean return2 = library.returnBook("M002", "978-1234567890");
        assertFalse(return2, "Should fail to return book not borrowed by member");
        
        // Test returning with non-existing member
        boolean return3 = library.returnBook("M999", "978-1234567890");
        assertFalse(return3, "Should fail with non-existing member");
        
        // Test returning with non-existing book
        boolean return4 = library.returnBook("M001", "999-9999999999");
        assertFalse(return4, "Should fail with non-existing book");
        
        // Test returning with null member ID
        boolean return5 = library.returnBook(null, "978-1234567890");
        assertFalse(return5, "Should fail with null member ID");
        
        // Test returning with null ISBN
        boolean return6 = library.returnBook("M001", null);
        assertFalse(return6, "Should fail with null ISBN");
    }
    
    @Test
    @DisplayName("Test Display Available Books")
    void testDisplayAvailableBooks() {
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Python Guide", "Jane Smith", "978-0987654321");
        Member member1 = new Member("Alice Johnson", "M001");
        
        library.addBook(book1);
        library.addBook(book2);
        library.addMember(member1);
        
        // Test display with all books available
        library.displayAvailableBooks();
        
        // Borrow one book and test display
        library.borrowBook("M001", "978-1234567890");
        library.displayAvailableBooks();
        
        // Test with empty library
        Library emptyLibrary = new Library();
        emptyLibrary.displayAvailableBooks();
    }
    
    @Test
    @DisplayName("Test Library Getter Methods")
    void testGetterMethods() {
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Member member1 = new Member("Alice Johnson", "M001");
        
        // Test getters with empty library
        assertEquals(0, library.getBooks().size(), "getBooks should return empty list initially");
        assertEquals(0, library.getMembers().size(), "getMembers should return empty list initially");
        assertEquals(0, library.getBookCount(), "getBookCount should return 0 initially");
        assertEquals(0, library.getMemberCount(), "getMemberCount should return 0 initially");
        
        // Add items and test getters
        library.addBook(book1);
        library.addMember(member1);
        
        assertEquals(1, library.getBooks().size(), "getBooks should return list with 1 book");
        assertEquals(1, library.getMembers().size(), "getMembers should return list with 1 member");
        assertEquals(1, library.getBookCount(), "getBookCount should return 1");
        assertEquals(1, library.getMemberCount(), "getMemberCount should return 1");
        
        // Test that getters return copies (encapsulation)
        library.getBooks().clear(); // This should not affect the library's internal list
        assertEquals(1, library.getBookCount(), "getBooks should return a copy, clearing it shouldn't affect library");
        
        library.getMembers().clear(); // This should not affect the library's internal list
        assertEquals(1, library.getMemberCount(), "getMembers should return a copy, clearing it shouldn't affect library");
    }
    
    @Test
    @DisplayName("Test Edge Cases and Integration Scenarios")
    void testEdgeCases() {
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Member member1 = new Member("Alice Johnson", "M001");
        
        library.addBook(book1);
        library.addMember(member1);
        
        // Test borrowing and returning the same book multiple times
        assertTrue(library.borrowBook("M001", "978-1234567890"), "First borrow should succeed");
        assertFalse(library.borrowBook("M001", "978-1234567890"), "Second borrow of same book should fail");
        assertTrue(library.returnBook("M001", "978-1234567890"), "First return should succeed");
        assertFalse(library.returnBook("M001", "978-1234567890"), "Second return of same book should fail");
        
        // Test with books having same ISBN (edge case for equals method)
        Book duplicateIsbnBook = new Book("Different Title", "Different Author", "978-1234567890");
        library.addBook(duplicateIsbnBook);
        
        // When searching, should find the first book with that ISBN
        Book found = library.findBookByIsbn("978-1234567890");
        assertEquals(book1, found, "Should find the first book with the ISBN");
    }
}
