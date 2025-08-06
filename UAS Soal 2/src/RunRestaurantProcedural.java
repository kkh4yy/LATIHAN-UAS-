public class RunRestaurantProcedural {
    
    // Arrays to store menu item information
    private static String[] itemNames = new String[100];
    private static String[] itemCategories = new String[100];
    private static String[] itemIds = new String[100];
    private static double[] itemPrices = new double[100];
    private static boolean[] itemAvailability = new boolean[100];
    private static int[] itemQuantities = new int[100]; // New: quantity tracking
    private static int menuItemCount = 0;
    
    // Arrays to store customer information
    private static String[] customerNames = new String[50];
    private static String[] customerIds = new String[50];
    private static String[][] customerOrderedItems = new String[50][20]; // Each customer can order max 20 items
    private static int[] customerOrderedCount = new int[50];
    private static double[] customerTotalBills = new double[50];
    private static int customerCount = 0;
    
    public static void main(String[] args) {
        // Add menu items with specific quantities
        addMenuItem("Nasi Goreng", "Main Course", "MC001", 25000.0, 3);
        addMenuItem("Gado-Gado", "Main Course", "MC002", 20000.0, 5);
        addMenuItem("Es Teh Manis", "Beverage", "BV001", 5000.0, 2);
        
        // Add customers
        addCustomer("John Smith", "C001");
        addCustomer("Jane Doe", "C002");
        addCustomer("Bob Wilson", "C003");
        
        // Test ordering
        System.out.println("=== Initial State ===");
        displayAvailableMenu();
        
        System.out.println("\n=== Ordering Items ===");
        boolean success1 = orderItem("C001", "MC001");
        System.out.println("John orders Nasi Goreng: " + success1);
        
        boolean success2 = orderItem("C002", "MC001");
        System.out.println("Jane orders Nasi Goreng: " + success2);
        
        boolean success3 = orderItem("C003", "INVALID");
        System.out.println("Bob tries to order non-existent item: " + success3);
        
        System.out.println("\nMenu after ordering:");
        displayAvailableMenu();
        
        // Try to order more than available
        System.out.println("\n=== Testing Quantity Limits ===");
        boolean success4 = orderItem("C003", "MC001");
        System.out.println("Bob orders Nasi Goreng: " + success4);
        
        boolean success5 = orderItem("C001", "MC001");
        System.out.println("John tries to order another Nasi Goreng: " + success5);
        
        System.out.println("\nMenu after quantity testing:");
        displayAvailableMenu();
        
        System.out.println("\n=== Customer Bills ===");
        calculateCustomerBill("C001");
        calculateCustomerBill("C002");
        System.out.println("John's total bill: Rp " + getCustomerTotalBill("C001"));
        System.out.println("Jane's total bill: Rp " + getCustomerTotalBill("C002"));
        
        System.out.println("\n=== Canceling Orders ===");
        boolean cancel1 = cancelOrderItem("C001", "MC001");
        System.out.println("John cancels Nasi Goreng: " + cancel1);
        calculateCustomerBill("C001");
        System.out.println("John's total bill after cancellation: Rp " + getCustomerTotalBill("C001"));
        
        System.out.println("\nFinal menu state:");
        displayAvailableMenu();
    }
    
    // Add a menu item to the restaurant
    public static void addMenuItem(String name, String category, String itemId, double price, int quantity) {
        if (menuItemCount < itemNames.length) {
            itemNames[menuItemCount] = name;
            itemCategories[menuItemCount] = category;
            itemIds[menuItemCount] = itemId;
            itemPrices[menuItemCount] = price;
            itemAvailability[menuItemCount] = true;
            itemQuantities[menuItemCount] = quantity;
            menuItemCount++;
        }
    }
    
    // Add a customer to the restaurant
    public static void addCustomer(String name, String customerId) {
        if (customerCount < customerNames.length) {
            customerNames[customerCount] = name;
            customerIds[customerCount] = customerId;
            customerOrderedCount[customerCount] = 0;
            customerTotalBills[customerCount] = 0.0;
            customerCount++;
        }
    }
    
    // Find menu item index by itemId
    public static int findMenuItemIndex(String itemId) {
        for (int i = 0; i < menuItemCount; i++) {
            if (itemIds[i] != null && itemIds[i].equals(itemId)) {
                return i;
            }
        }
        return -1;
    }
    
    // Find customer index by customerId
    public static int findCustomerIndex(String customerId) {
        for (int i = 0; i < customerCount; i++) {
            if (customerIds[i] != null && customerIds[i].equals(customerId)) {
                return i;
            }
        }
        return -1;
    }
    
    // Order an item for a customer
    public static boolean orderItem(String customerId, String itemId) {
        int customerIndex = findCustomerIndex(customerId);
        int itemIndex = findMenuItemIndex(itemId);
        
        if (customerIndex == -1 || itemIndex == -1) {
            return false;
        }
        
        if (!itemAvailability[itemIndex] || itemQuantities[itemIndex] <= 0) {
            return false;
        }
        
        // Add item to customer's order and decrease quantity
        if (customerOrderedCount[customerIndex] < customerOrderedItems[customerIndex].length) {
            customerOrderedItems[customerIndex][customerOrderedCount[customerIndex]] = itemId;
            customerOrderedCount[customerIndex]++;
            itemQuantities[itemIndex]--; // Decrease quantity
            return true;
        }
        
        return false;
    }
    
    // Cancel an order item for a customer
    public static boolean cancelOrderItem(String customerId, String itemId) {
        int customerIndex = findCustomerIndex(customerId);
        
        if (customerIndex == -1) {
            return false;
        }
        
        // Find and remove the item from customer's orders
        for (int i = 0; i < customerOrderedCount[customerIndex]; i++) {
            if (customerOrderedItems[customerIndex][i] != null && 
                customerOrderedItems[customerIndex][i].equals(itemId)) {
                
                // Shift remaining items
                for (int j = i; j < customerOrderedCount[customerIndex] - 1; j++) {
                    customerOrderedItems[customerIndex][j] = customerOrderedItems[customerIndex][j + 1];
                }
                customerOrderedCount[customerIndex]--;
                customerOrderedItems[customerIndex][customerOrderedCount[customerIndex]] = null;
                
                // Restore quantity
                int itemIndex = findMenuItemIndex(itemId);
                if (itemIndex != -1) {
                    itemQuantities[itemIndex]++;
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    // Calculate total bill for a customer
    public static void calculateCustomerBill(String customerId) {
        int customerIndex = findCustomerIndex(customerId);
        
        if (customerIndex == -1) {
            return;
        }
        
        double total = 0.0;
        for (int i = 0; i < customerOrderedCount[customerIndex]; i++) {
            String orderedItemId = customerOrderedItems[customerIndex][i];
            int itemIndex = findMenuItemIndex(orderedItemId);
            if (itemIndex != -1) {
                total += itemPrices[itemIndex];
            }
        }
        
        customerTotalBills[customerIndex] = total;
    }
    
    // Get customer's total bill
    public static double getCustomerTotalBill(String customerId) {
        int customerIndex = findCustomerIndex(customerId);
        if (customerIndex != -1) {
            return customerTotalBills[customerIndex];
        }
        return 0.0;
    }
    
    // Display all available menu items
    public static void displayAvailableMenu() {
        System.out.println("Available Menu:");
        for (int i = 0; i < menuItemCount; i++) {
            if (itemAvailability[i] && itemQuantities[i] > 0) {
                System.out.printf("- MenuItem{name='%s', category='%s', itemId='%s', price=%.1f, available=%s} (Quantity: %d)%n",
                    itemNames[i], itemCategories[i], itemIds[i], itemPrices[i], itemAvailability[i], itemQuantities[i]);
            }
        }
    }
}
