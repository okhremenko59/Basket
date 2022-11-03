import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        Product[] product = {new Product("Молоко", 50),
                new Product("Хлеб", 14),
                new Product("Каша гречневая", 80)};

        Basket cart = new Basket(product);
        File file = new File("basket.bin");

        if (file.isFile()) {
            System.out.println("Найдена ваша корзина покупок: ");
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
            cart.printBasket();

            System.out.println("Выберите товар и количество или введите 'end' ");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }

            String[] s = input.split(" ");
            int productNumber = Integer.parseInt(s[0]) - 1;
            int productCount = Integer.parseInt(s[1]);
            cart.addToCart(productNumber, productCount);

            cart.saveBin(file);
        }

        cart.printCart();
        cart.saveBin(file);
    }
}
