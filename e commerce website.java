import java.util.ArrayList;
import java.util.Scanner;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

public class EcommerceWebsite {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<CartItem> cart = new ArrayList<>();

    static void loadProducts() {
        products.add(new Product(1, "Laptop", 55000));
        products.add(new Product(2, "Smartphone", 25000));
        products.add(new Product(3, "Headphones", 2000));
        products.add(new Product(4, "Keyboard", 1500));
        products.add(new Product(5, "Mouse", 800));
    }

    static void displayProducts() {
        System.out.println("\n========== PRODUCTS ==========");

        for (Product p : products) {
            System.out.printf("%d. %-15s Rs. %.2f%n",
                    p.id, p.name, p.price);
        }
    }

    static void addToCart() {
        displayProducts();

        System.out.print("\nEnter Product ID: ");
        int id = sc.nextInt();

        Product selectedProduct = null;

        for (Product p : products) {
            if (p.id == id) {
                selectedProduct = p;
                break;
            }
        }

        if (selectedProduct == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        cart.add(new CartItem(selectedProduct, quantity));

        System.out.println("Product added to cart!");
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        double total = 0;

        System.out.println("\n========== YOUR CART ==========");

        for (CartItem item : cart) {
            double amount = item.getTotal();

            System.out.printf("%-15s x %d = Rs. %.2f%n",
                    item.product.name,
                    item.quantity,
                    amount);

            total += amount;
        }

        System.out.println("-------------------------------");
        System.out.printf("Total: Rs. %.2f%n", total);
    }

    static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double total = 0;

        for (CartItem item : cart) {
            total += item.getTotal();
        }

        System.out.println("\n========== CHECKOUT ==========");
        System.out.printf("Total Amount: Rs. %.2f%n", total);

        System.out.println("1. Cash on Delivery");
        System.out.println("2. Online Payment");

        System.out.print("Select payment method: ");
        int payment = sc.nextInt();

        if (payment == 1) {
            System.out.println("Payment method: Cash on Delivery");
        } else if (payment == 2) {
            System.out.println("Payment method: Online Payment");
        } else {
            System.out.println("Invalid payment method.");
            return;
        }

        System.out.println("Order placed successfully!");
        System.out.println("Thank you for shopping with us.");

        cart.clear();
    }

    public static void main(String[] args) {

        loadProducts();

        while (true) {

            System.out.println("\n================================");
            System.out.println("       E-COMMERCE WEBSITE");
            System.out.println("================================");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayProducts();
                    break;

                case 2:
                    addToCart();
                    break;

                case 3:
                    viewCart();
                    break;

                case 4:
                    checkout();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
