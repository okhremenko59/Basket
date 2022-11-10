import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        String[] productName = {"Молоко", "Хлеб", "Каша гречневая"};
        int[] prices = {50, 14, 80};

        Basket basket = new Basket(productName, prices);
        File file = new File("basket.bin");

        if (file.isFile() && file.length() != 0) {
            Basket.loadFromBinFile(file);
            System.out.println();
        } else System.out.println("Ваша корзина пуста начните покупки: ");
        try {
            if (file.createNewFile()) {
                System.out.println();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }

        while (true) {
            basket.printProduct();
            System.out.println("Выберите товар и количество или введите 'end' ");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }

            String[] s = input.split(" ");
            int productNumber = Integer.parseInt(s[0]) - 1;
            int amount = Integer.parseInt(s[1]);
            basket.addToCart(productNumber, amount);
        }

        basket.saveBin(file);
        //     basket.loadFromBinFile(file);
        basket.printBasket();
    }
}
