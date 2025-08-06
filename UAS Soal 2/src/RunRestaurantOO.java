public class RunRestaurantOO {
    public static void main(String[] args) {
        // Create restaurant
        Restaurant restaurant = new Restaurant();
        
        // Add menu items with specific quantities
        restaurant.addMenuItem(new MenuItem("Nasi Goreng", "Main Course", "MC001", 25000.0), 3);
        restaurant.addMenuItem(new MenuItem("Gado-Gado", "Main Course", "MC002", 20000.0), 5);
        restaurant.addMenuItem(new MenuItem("Es Teh Manis", "Beverage", "BV001", 5000.0), 2);
        
        // Add customers
        restaurant.addCustomer(new Customer("John Smith", "C001"));
        restaurant.addCustomer(new Customer("Jane Doe", "C002"));
        restaurant.addCustomer(new Customer("Bob Wilson", "C003"));
        
        // Test ordering
        System.out.println("=== Initial State ===");
        restaurant.displayAvailableMenu();
        
        System.out.println("\n=== Ordering Items ===");
        boolean success1 = restaurant.orderItem("C001", "MC001");
        System.out.println("John orders Nasi Goreng: " + success1);
        
        boolean success2 = restaurant.orderItem("C002", "MC001");
        System.out.println("Jane orders Nasi Goreng: " + success2);
        
        boolean success3 = restaurant.orderItem("C003", "INVALID");
        System.out.println("Bob tries to order non-existent item: " + success3);
        
        System.out.println("\nMenu after ordering:");
        restaurant.displayAvailableMenu();
        
        // Try to order more than available
        System.out.println("\n=== Testing Quantity Limits ===");
        boolean success4 = restaurant.orderItem("C003", "MC001");
        System.out.println("Bob orders Nasi Goreng: " + success4);
        
        boolean success5 = restaurant.orderItem("C001", "MC001");
        System.out.println("John tries to order another Nasi Goreng: " + success5);
        
        System.out.println("\nMenu after quantity testing:");
        restaurant.displayAvailableMenu();
        
        System.out.println("\n=== Customer Bills ===");
        Customer john = restaurant.findCustomerById("C001");
        Customer jane = restaurant.findCustomerById("C002");
        
        if (john != null) {
            john.calculateTotalBill();
            System.out.println("John's total bill: Rp " + john.getTotalBill());
        }
        
        if (jane != null) {
            jane.calculateTotalBill();
            System.out.println("Jane's total bill: Rp " + jane.getTotalBill());
        }
        
        System.out.println("\n=== Canceling Orders ===");
        boolean cancel1 = restaurant.cancelOrderItem("C001", "MC001");
        System.out.println("John cancels Nasi Goreng: " + cancel1);
        
        if (john != null) {
            john.calculateTotalBill();
            System.out.println("John's total bill after cancellation: Rp " + john.getTotalBill());
        }
        
        System.out.println("\nFinal menu state:");
        restaurant.displayAvailableMenu();
    }
}
