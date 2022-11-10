import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    protected String[] productName;
    protected int[] prices;
    protected int[] count;
    protected int sumBasket;

    public Basket(String[] productName, int[] prices) {
        this.productName = productName;
        this.prices = prices;
        this.count = new int[prices.length];
    }

    public void printBasket() {
        System.out.println("Ваша корзина покупок: ");
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                System.out.println(productName[i] + " " + count[i] + " шт. " +
                        "В сумме: " + (count[i] * prices[i]) + " руб.");
                sumBasket += (count[i] * prices[i]);
            }
        }
    }

    public void printProduct() {
        System.out.println("Список доступных продуктов к покупке: ");
        for (int i = 0; i < productName.length; i++) {
            int cnt = i + 1;
            System.out.println(cnt + ". " + productName[i] + " - " + prices[i] + " руб/шт.");
        }
    }

    public void addToCart(int productNumber, int amount) {
        count[productNumber] += amount;
    }

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Basket basket = (Basket) in.readObject();
            basket.printBasket();
            return basket;
        }
    }
}

