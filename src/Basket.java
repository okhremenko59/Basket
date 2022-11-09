import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    protected Product[] product;
    protected String [] productName;
    protected int [] prices;
    protected int [] count;

    public Basket(String[] productName, int[] prices, int[] count) {
        this.productName = productName;
        this.prices = prices;
        this.count = new int[prices.length];
    }

    public void printProduct() {
        System.out.println("Список доступных продуктов к покупке: ");
        for (int i = 0; i < product.length; i++) {
            int cnt = i + 1;
            System.out.println(cnt + ". " + product[i]);
        }
    }

    public Basket(Product[] product) {
        this.product = product;
        count = new int[prices.length];
    }

    public Basket(String[] productName, int[] prices) {
        this.productName = productName;
        this.prices = prices;
    }

    public void addToCart(int productNumber, int amount) {
        count[productNumber] += amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина: ");
        for (int i=0; i< productName.length; i++) {
            System.out.println(productName[i] + " " + count[i] + " шт. " +
                    "В сумме: " + (count[i]*prices[i]) + " руб.");
        }
    }

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
            }
        }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
           Basket basket = (Basket) in.readObject();
            System.out.println(basket);
           return basket;
        }
    }

    @Override
    public String toString() {
        return "Basket{" +
                "productName=" + Arrays.toString(productName) +
                ", prices=" + Arrays.toString(prices) +
                ", count=" + Arrays.toString(count) +
                '}';
    }
}

