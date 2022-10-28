public class Product {
    public String productName;
    public int price;
    public int sum;
    public int total;

    public Product(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    public String toString() {

        return productName + " " + price + " руб/шт";
    }
}
