public class MenuItem { 
    private String name;
    private String category;
    private String itemId;
    private double price;
    
    public MenuItem(String name, String category, String itemId, double price) {
        this.name = name;
        this.category = category;
        this.itemId = itemId;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getItemId() {
        return itemId;
    }
    
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return String.format("MenuItem{name='%s', category='%s', itemId='%s', price=%.1f}", 
                           name, category, itemId, price);
    }

}
