import java.io.*;

public class Basket implements Serializable {
    private Product[] product;
    private Product[] cart;
    int sumBasket = 0;

    public void printBasket() {
        System.out.println("Список доступных продуктов к покупке: ");
        for (int i = 0; i < product.length; i++) {
            int cnt = i + 1;
            System.out.println(cnt + ". " + product[i]);
        }
    }

    //конструктор, принимающий массив цен и названий продуктов;
    public Basket(Product[] product) {
        this.product = product;
        cart = new Product[product.length];
    }

    //метод добавления amount штук продукта и номер productNum в корзину;
    public void addToCart(int productNumber, int amount) {
        cart[productNumber] = product[productNumber];
        cart[productNumber].total += product[productNumber].price * amount;

    }

    // метод вывода на экран покупательской корзины.
    public void printCart() {
        System.out.println("Ваша корзина: ");
        for (Product value : cart) {
            if (value == null) {
                break;
            } else if (value.total > 0) {
                System.out.println(value.productName + " " + value.total / value.price + " шт. " +
                        "В сумме: " + value.total + " руб.");
                sumBasket += value.total;
            }
        }
        System.out.println("Итого: " + sumBasket + " руб.");
    }

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("basket.bin"))) {
            for (int i = 0; i < cart.length; i++) {
                Product value = cart[i];
                if (value == null) {
                    break;
                } else
                    out.writeObject(file);
            }
        }
    }

    static void loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("basket.bin"))) {
            File InFile = (File) in.readObject();
            in.close();
            System.out.println(InFile);
        }
    }
}

