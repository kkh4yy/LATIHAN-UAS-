import java.util.*;

public class Member implements Cloneable{
    private String name;
    private String memberId;
    private List<Book> borrowedBooks;
    
    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>(); 
    }
    
    public String getName() {
        return name;
    }
    
    public String getMemberId() {
        return memberId;
    }

    public void addBorrowedBooks(Book book){
        borrowedBooks.add(book);
    }

    public boolean removeBorrowedBook(Book book) {
        return borrowedBooks.remove(book);
    }
    
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    @Override
    public String toString() {
        return String.format("Member{name='%s', memberId='%s', borrowedBooks=%d}", 
                           name, memberId, borrowedBooks.size());
    }

    @Override
    public Object clone(){
        return (Object) new Member(name, memberId);
    }

    @Override
    public boolean equals(Object o){
        if (o!=null && o instanceof Member){
            Member otherMember = ((Member) o);
            if(otherMember.getMemberId().equals(memberId)){
                return true;
            }
        }
        return false;
    }
}
