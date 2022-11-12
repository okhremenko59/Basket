import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {
    protected String[] productName;
    protected int[] prices;
    protected int[] count;
    protected int sumBasket;

    public Basket(String[] productName, int[] prices) {
        this.productName = productName;
        this.prices = prices;
        this.count = new int[prices.length];
    }

    public Basket(String[] productName, int[] prices, int[] count) {
        this.productName = productName;
        this.prices = prices;
        this.count = count;
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
        System.out.println("Сумма покупок: " + sumBasket);
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

    public void saveTxt(File file) throws IOException {
        try (PrintWriter out = new PrintWriter(file)) {
            for (String productName : productName) {
                out.print(productName + " ");
            }
            out.println();
            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();
            for (int count : count) {
                out.print(count + " ");
            }
            out.println();
        }
    }

    public static Basket loadFromTxtFile(File file) throws IOException {
        try (Scanner in = new Scanner(new FileInputStream(file))) {
            String[] productName = in.nextLine().trim().split(" ");
            int[] prices = Arrays.stream(in.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] count = Arrays.stream(in.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return new Basket(productName, prices, count);
        }
    }
}