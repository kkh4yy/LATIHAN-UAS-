import java.util.*;

public class Library {
    private List<Book> books;
    private List<Member> members;
    
    public Library() {
        this.books = new ArrayList<Book>(); 
        this.members = new ArrayList<Member>(); 
    }
    
    public int getBookCount(){
        return -1; // Remove this line and implement it correctly
    }

    public void addBook(Book book) {
        books.add(book);
    }

    // TODO: Implement getMembers method
    // this method should return a new (different List) than the List in this object.
    // Make sure you copy the values to a new List before return it.
    public List<Book> getBooks(){
        return books; // Remove this line and implement it correctly
    }

    public int getMemberCount(){
        return members.size(); // Remove this line and implement it correctly
    }

    public void addMember(Member member) {
        members.add(member);
    }

    // TODO: Implement getMembers method
    // this method should return a new (different List) than the List in this object.
    // Make sure you copy the values to a new List before return it.
    public List<Member> getMembers(){
        return members; // Remove this line and implement the method
    }
    
    // TODO: Implement findBookByIsbn method
    public Book findBookByIsbn(String isbn) {
        // Your code here - use loop to find book with matching ISBN
        return books.get(getBookCount()); // Remove this line and implement the method
    }
    
    // TODO: Implement findMemberById method
    public Member findMemberById(String memberId) {
        // Your code here
        
        return members.get(getBookCount()); // Remove this line and implement the method
    }
    
    // TODO: Implement borrowBook method
    // Find member and book, then call member's borrowBook method
    public boolean borrowBook(String memberId, String isbn) {
        // Your code here
        return borrowBook(memberId, isbn); // Remove this line and implement the method
    }
    
    // TODO: Implement returnBook method
    // Find member and book, then call member's returnBook method
    public boolean returnBook(String memberId, String isbn) {
        // Your code here
        return false; // Remove this line and implement the method
    }
    
    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("- " + book);
            }
        }
    }
}
