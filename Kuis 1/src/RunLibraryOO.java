public class RunLibraryOO {
    public static void main(String[] args) {
        // Create library
        Library library = new Library();
        
        // Add books
        Book book1 = new Book("Java Programming", "John Doe", "978-1234567890");
        Book book2 = new Book("Data Structures", "Jane Smith", "978-0987654321");
        library.addBook(book1);
        library.addBook(book2);
        
        // Add members
        Member member1 = new Member("Alice Johnson", "M001");
        Member member2 = new Member("Bob Wilson", "M002");
        library.addMember(member1);
        library.addMember(member2);
        
        // Test borrowing
        System.out.println("=== Initial State ===");
        library.displayAvailableBooks();
        
        System.out.println("\n=== Borrowing Books ===");
        boolean success1 = library.borrowBook("M001", "978-1234567890");
        System.out.println("Alice borrows Java Programming: " + success1);
        
        boolean success2 = library.borrowBook("M002", "978-1234567890");
        System.out.println("Bob tries to borrow same book: " + success2);
        
        library.displayAvailableBooks();
        
        System.out.println("\n=== Returning Books ===");
        boolean return1 = library.returnBook("M001", "978-1234567890");
        System.out.println("Alice returns Java Programming: " + return1);
        
        library.displayAvailableBooks();
    }
}
