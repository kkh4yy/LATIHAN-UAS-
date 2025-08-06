import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private Map<MenuItem, Integer> menu; // MenuItem -> Available quantity
    private List<Customer> customers;
    
    // TODO: Complete the constructor
    public Restaurant() {

    }
    
    // TODO: Implement methods to manage menu items
    public int getMenuItemCount() {
        return menu.size();
    }
    
    public void addMenuItem(MenuItem item) {
        addMenuItem(item, 10); // Default quantity of 10
    }
    
    public void addMenuItem(MenuItem item, int quantity) {
        if (item != null && quantity >= 0) {
            menu.put(item, quantity);
        }
    }
    
    public List<MenuItem> getMenu() {
        // Your code here
        return null; // Remove this line 
    }
    
    public int getMenuItemQuantity(MenuItem item) {
        // Your code here
        return null; // Remove this line 
    }
    
    public void updateMenuItemQuantity(MenuItem item, int quantity) {
        if (item != null && menu.containsKey(item)) {
            menu.put(item, Math.max(0, quantity));
        }
    }
    
    // TODO: Implement methods to manage customers
    public int getCustomerCount() {
        // Your code here
        return null; // Remove this line 
    }


    // TODO: Implement methods to manage customers
    public void addCustomer(Customer customer) {
        // Your code here
    }
    
    public List<Customer> getCustomers() {
        // Your code here
        return null; // Remove this line 
    }
    
    // TODO: Implement search methods
    public MenuItem findMenuItemById(String itemId) {
        if (itemId == null) return null;
        
        for (MenuItem item : menu.keySet()) {
            if (itemId.equals(item.getItemId())) {
                return item;
            }
        }
        return null;
    }
    
    public Customer findCustomerById(String customerId) {
        if (customerId == null) return null;
        
        for (Customer customer : customers) {
            if (customerId.equals(customer.getCustomerId())) {
                return customer;
            }
        }
        return null;
    }
    
    // TODO: Implement order management methods
    public boolean orderItem(String customerId, String itemId) {
        // Your code here
        return null; // Remove this line 
    }
    
    public boolean cancelOrderItem(String customerId, String itemId) {
        // Your code here
        return null; // Remove this line 
    }
    
    public void displayAvailableMenu() {
        System.out.println("Available Menu:");
        for (Map.Entry<MenuItem, Integer> entry : menu.entrySet()) {
            MenuItem item = entry.getKey();
            Integer quantity = entry.getValue();
            
            if (quantity > 0) {
                System.out.println("- " + item.toString() + " (Quantity: " + quantity + ")");
            }
        }
    }
}
