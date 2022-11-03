public class Product {
    public String productName;
    public int price;
    public int total;

    public Product(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    public String toString() {
        return productName + " " + price + " руб/шт";
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getTotal() {
        return total;
    }
}
