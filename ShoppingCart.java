import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> cart;

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public boolean removeProduct(int productId) {
        for (Product product : cart) {
            if (product.getId() == productId) {
                cart.remove(product);
                return true;
            }
        }
        return false;
    }

    public void clearCart() {
        cart.clear();
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            for (Product product : cart) {
                System.out.println(product);
            }
        }
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items to checkout.");
        } else {
            double total = 0;
            System.out.println("Checkout Summary:");
            for (Product product : cart) {
                System.out.println(product);
                total += product.getPrice();
            }
            System.out.println("Total Amount: " + total);
            cart.clear();
            System.out.println("Thank you for your purchase!");
        }
    }
}
