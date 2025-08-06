import java.util.List;

// TODO: You may need to extend or implement other class or interface
public class Customer {
    private String name;
    private String customerId;
    private List<MenuItem> orderedItems;
    private double totalBill;
    
    // TODO: Complete the constructor
    public Customer(String name, String customerId) {
        // Your code here
    }
    
    // TODO: Implement getter methods
    public String getName() {
        // Your code here
        return null; // Remove this line and return the name
    }
    
    public String getCustomerId() {
        // Your code here
        return null; // Remove this line and return the customerId
    }
    
    public List<MenuItem> getOrderedItems() {
        // Your code here
        return null; // Remove this line and return the orderedItems
    }
    
    public double getTotalBill() {
        // Your code here
        return 0.0; // Remove this line and return the totalBill
    }
    
    // TODO: Implement methods to manage ordered items
    public void addOrderedItem(MenuItem item) {
        // Your code here
    }
    
    public boolean removeOrderedItem(MenuItem item) {
        // Your code here
        return false; // Remove this line and return appropriate boolean
    }
    
    public void calculateTotalBill() {
        // Your code here - calculate total from all ordered items
    }
    
    @Override
    public String toString() {
        return String.format("Customer{name='%s', customerId='%s', orderedItems=%d, totalBill=%.1f}", 
                           name, customerId, orderedItems != null ? orderedItems.size() : 0, totalBill);
    }
    
}
