public class RunLibraryProcedural {
    
    // Arrays to store book information
    private static String[] bookTitles = new String[100];
    private static String[] bookAuthors = new String[100];
    private static String[] bookIsbns = new String[100];
    private static boolean[] bookAvailability = new boolean[100];
    private static int bookCount = 0;
    
    // Arrays to store member information
    private static String[] memberNames = new String[50];
    private static String[] memberIds = new String[50];
    private static String[][] memberBorrowedBooks = new String[50][10]; // Each member can borrow max 10 books
    private static int[] memberBorrowedCount = new int[50];
    private static int memberCount = 0;
    
    public static void main(String[] args) {
        // Add books
        addBook("Java Programming", "John Doe", "978-1234567890");
        addBook("Data Structures", "Jane Smith", "978-0987654321");
        
        // Add members
        addMember("Alice Johnson", "M001");
        addMember("Bob Wilson", "M002");
        
        // Test borrowing
        System.out.println("=== Initial State ===");
        displayAvailableBooks();
        
        System.out.println("\n=== Borrowing Books ===");
        boolean success1 = borrowBook("M001", "978-1234567890");
        System.out.println("Alice borrows Java Programming: " + success1);
        
        boolean success2 = borrowBook("M002", "978-1234567890");
        System.out.println("Bob tries to borrow same book: " + success2);
        
        displayAvailableBooks();
        
        System.out.println("\n=== Returning Books ===");
        boolean return1 = returnBook("M001", "978-1234567890");
        System.out.println("Alice returns Java Programming: " + return1);
        
        displayAvailableBooks();
    }
    
    // Add a book to the library
    public static void addBook(String title, String author, String isbn) {
        if (bookCount < bookTitles.length) {
            bookTitles[bookCount] = title;
            bookAuthors[bookCount] = author;
            bookIsbns[bookCount] = isbn;
            bookAvailability[bookCount] = true;
            bookCount++;
        } else {
            System.out.println("Library is full, cannot add more books!");
        }
    }
    
    // Add a member to the library
    public static void addMember(String name, String memberId) {
        if (memberCount < memberNames.length) {
            memberNames[memberCount] = name;
            memberIds[memberCount] = memberId;
            memberBorrowedCount[memberCount] = 0;
            // Initialize borrowed books array for this member
            for (int i = 0; i < memberBorrowedBooks[memberCount].length; i++) {
                memberBorrowedBooks[memberCount][i] = null;
            }
            memberCount++;
        } else {
            System.out.println("Cannot add more members!");
        }
    }
    
    // Find book index by ISBN
    public static int findBookByIsbn(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (bookIsbns[i].equals(isbn)) {
                return i;
            }
        }
        return -1; // Not found
    }
    
    // Find member index by ID
    public static int findMemberById(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (memberIds[i].equals(memberId)) {
                return i;
            }
        }
        return -1; // Not found
    }
    
    // Borrow a book
    public static boolean borrowBook(String memberId, String isbn) {
        int memberIndex = findMemberById(memberId);
        int bookIndex = findBookByIsbn(isbn);
        
        // Check if member and book exist
        if (memberIndex == -1 || bookIndex == -1) {
            return false;
        }
        
        // Check if book is available
        if (!bookAvailability[bookIndex]) {
            return false;
        }
        
        // Check if member has space for more books
        if (memberBorrowedCount[memberIndex] >= memberBorrowedBooks[memberIndex].length) {
            return false;
        }
        
        // Add book to member's borrowed list
        for (int i = 0; i < memberBorrowedBooks[memberIndex].length; i++) {
            if (memberBorrowedBooks[memberIndex][i] == null) {
                memberBorrowedBooks[memberIndex][i] = isbn;
                break;
            }
        }
        
        // Update counters and availability
        memberBorrowedCount[memberIndex]++;
        bookAvailability[bookIndex] = false;
        
        return true;
    }
    
    // Return a book
    public static boolean returnBook(String memberId, String isbn) {
        int memberIndex = findMemberById(memberId);
        int bookIndex = findBookByIsbn(isbn);
        
        // Check if member and book exist
        if (memberIndex == -1 || bookIndex == -1) {
            return false;
        }
        
        // Check if member has borrowed this book
        boolean bookFound = false;
        for (int i = 0; i < memberBorrowedBooks[memberIndex].length; i++) {
            if (memberBorrowedBooks[memberIndex][i] != null && 
                memberBorrowedBooks[memberIndex][i].equals(isbn)) {
                memberBorrowedBooks[memberIndex][i] = null;
                bookFound = true;
                break;
            }
        }
        
        if (!bookFound) {
            return false;
        }
        
        // Update counters and availability
        memberBorrowedCount[memberIndex]--;
        bookAvailability[bookIndex] = true;
        
        return true;
    }
    
    // Display all available books
    public static void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (int i = 0; i < bookCount; i++) {
            if (bookAvailability[i]) {
                System.out.println("- Book{title='" + bookTitles[i] + 
                                 "', author='" + bookAuthors[i] + 
                                 "', isbn='" + bookIsbns[i] + 
                                 "', available=" + bookAvailability[i] + "}");
            }
        }
    }
    
    // Utility method to display member information (bonus)
    public static void displayMemberInfo(String memberId) {
        int memberIndex = findMemberById(memberId);
        if (memberIndex == -1) {
            System.out.println("Member not found!");
            return;
        }
        
        System.out.println("Member: " + memberNames[memberIndex] + " (" + memberIds[memberIndex] + ")");
        System.out.println("Borrowed books: " + memberBorrowedCount[memberIndex]);
        for (int i = 0; i < memberBorrowedBooks[memberIndex].length; i++) {
            if (memberBorrowedBooks[memberIndex][i] != null) {
                int bookIndex = findBookByIsbn(memberBorrowedBooks[memberIndex][i]);
                if (bookIndex != -1) {
                    System.out.println("  - " + bookTitles[bookIndex]);
                }
            }
        }
    }
}
